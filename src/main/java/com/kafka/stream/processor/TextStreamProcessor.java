package com.kafka.stream.processor;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.kafka.stream.config.KafkaStreamConfig;
import com.kafka.stream.model.Message;
import com.kafka.stream.util.Constants;

@Component
public class TextStreamProcessor {
	
	@Autowired
	private KafkaStreamConfig kafkaStreamConfig;
	
	@Autowired
	private Gson gson ;
	
	@Value("${kafka.topic.text.message}")
	private String KAFKA_TOPIC_TEXT_MESSAGE;
	
	@Value("${kafka.topic.json.message}")
	private String KAFKA_TOPIC_JSON_MESSAGE;
	
	private Logger logger = LoggerFactory.getLogger(TextStreamProcessor.class);
	
	public void process(String streamEvent, String threadName){
		Properties streamsConfiguration = kafkaStreamConfig.getStreamConfiguration(streamEvent,threadName);
	    Serde<String> stringSerde = Serdes.String();

		StreamsBuilder builder = new StreamsBuilder();
		
		//read the text message topic into stream
		KStream<String, String> textStream = builder.stream(KAFKA_TOPIC_TEXT_MESSAGE, Consumed.with(stringSerde, stringSerde));
		
		// Map text values to java object, filter the unwanted messages, convert it into json. 
		textStream = textStream.mapValues(v -> mapTextValues(v)).filter((k,v) -> (v!=null && "POS".equals(v.getAdr_box_nbr()))).map(new KeyValueMapper<String, Message, KeyValue<String, String>>() { 
            @Override 
            public KeyValue<String, String> apply(String key, Message value) { 
                return new KeyValue<>(null, gson.toJson(value));
            }});
		
		//output the json message in json topic
		textStream.to(KAFKA_TOPIC_JSON_MESSAGE);
		
		Topology topology = builder.build();
		KafkaStreams streams = new KafkaStreams(topology, streamsConfiguration);
		streams.cleanUp();
		streams.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				logger.error("Uncaught exception in Thread {} - {}",t,e.getMessage());
			}
		});
		streams.start();
		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
	}

	private Message mapTextValues(String message) {
		if(StringUtils.isEmpty(message)) {
			return null;
		}
		String[] fieldValues = message.split(Constants.FIELD_DELIMITER);
		Message m = new Message();
		m.setAdr_box_nbr(fieldValues[0]);
		return m;
	}
}
