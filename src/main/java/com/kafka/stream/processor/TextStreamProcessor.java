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
import com.kafka.stream.model.Payload;
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
		textStream = textStream.mapValues(v -> mapTextValues(v)).filter((k,v) -> (v!=null)).map(new KeyValueMapper<String, Payload, KeyValue<String, String>>() { 
            @Override 
            public KeyValue<String, String> apply(String key, Payload value) { 
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

	private Payload mapTextValues(String message) {
		if(StringUtils.isEmpty(message)) {
			return null;
		}
		
		String[] fieldValues = message.split(Constants.FIELD_DELIMITER);
		Payload m = new Payload();
		m.setClm_loc_typ_cd(fieldValues[0]);
		m.setClm_adjd_pltfm_id(fieldValues[1]);
		m.setClm_fl_id(fieldValues[2]);
		m.setStrt_srvc_dt(fieldValues[3]);
		m.setSubgroup(fieldValues[4]);
		m.setMng_hlth_subpln_nm(fieldValues[5]);
		m.setMng_hlth_pln_nm(fieldValues[6]);
		m.setSrvc_loc_prov_id(fieldValues[7]);
		m.setClm_id(fieldValues[8]);
		m.setPtnt_src_sys_id(getLongValue(fieldValues[9]));
		m.setBus_seg_id(fieldValues[10]);
		m.setProduct(fieldValues[11]);
		return m;
	}
	
	private Long getLongValue(String value) {
		return StringUtils.isEmpty(value)?null:Long.parseLong(value);
	}
}
