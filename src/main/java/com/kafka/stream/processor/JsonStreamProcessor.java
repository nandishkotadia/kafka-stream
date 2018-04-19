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
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.kafka.stream.config.KafkaStreamConfig;
import com.kafka.stream.model.Message;

@Component
public class JsonStreamProcessor {

	@Autowired
	private KafkaStreamConfig kafkaStreamConfig;
	
	@Autowired
	private Gson gson ;
	
	@Value("${kafka.topic.json.message}")
	private String KAFKA_TOPIC_JSON_MESSAGE;
	
	private Logger logger = LoggerFactory.getLogger(JsonStreamProcessor.class);
	
	@Value("${data.validate.url}")
	private String validateUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void process(String streamEvent, String threadName) {
		
		Properties streamsConfiguration = kafkaStreamConfig.getStreamConfiguration(streamEvent,threadName);
	    Serde<String> stringSerde = Serdes.String();

		StreamsBuilder builder = new StreamsBuilder();
		
		//read the json message topic into stream
		KStream<String, String> jsonStream = builder.stream(KAFKA_TOPIC_JSON_MESSAGE, Consumed.with(stringSerde, stringSerde));
		
		jsonStream.mapValues(v -> verifyData(v)).filter((k,v) -> (v!=null)).map(new KeyValueMapper<String, Message, KeyValue<String, String>>() { 
            @Override 
            public KeyValue<String, String> apply(String key, Message value) { 
                return new KeyValue<>(null, gson.toJson(value));
            }});
		
		//output the json message in final output topic
		jsonStream.to(KAFKA_TOPIC_JSON_MESSAGE);
		
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

	private Message verifyData(String jsonMessage) {
		Message m = null;
		try {
			m = gson.fromJson(jsonMessage, Message.class);
			Message response = restTemplate.postForObject(validateUrl, m, Message.class);
			return response;
		}catch(Exception e) {
			logger.error("Error with message: "+jsonMessage);
			logger.error("Error in verifyData()",e);
		}
		return null;
	}

}
