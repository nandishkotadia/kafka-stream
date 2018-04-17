package com.kafka.stream.config;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaStreamConfig {

	@Value("${stream.commit.interval.ms}")
	private String streamCommitIntervalMs;
	
	@Value("${stream.threads.count}")
	private String numberStreamThreads;
	
	@Value("${app.env}")
	private String appEnv;
	
	@Value("${replication.factor}")
	private Integer replicationFactor;
	
	@Value("${bootstrap.servers}")
	private String bootstrapServers;
	
	public Properties getStreamConfiguration(String applicationId, String threadName) {
		Properties streamsConfiguration = new Properties();
		streamsConfiguration.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId+"-"+appEnv);
	    streamsConfiguration.put(StreamsConfig.CLIENT_ID_CONFIG, applicationId+"-"+appEnv+"-"+threadName);
	    
	    streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers );
	    streamsConfiguration.put(StreamsConfig.REPLICATION_FACTOR_CONFIG, replicationFactor);
	    streamsConfiguration.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
	    streamsConfiguration.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
	    streamsConfiguration.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, streamCommitIntervalMs);
	    streamsConfiguration.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, numberStreamThreads);
	    streamsConfiguration.put(StreamsConfig.TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class);
	    
	    streamsConfiguration.put(StreamsConfig.RECEIVE_BUFFER_CONFIG,"1048576");
	    streamsConfiguration.put(StreamsConfig.SEND_BUFFER_CONFIG,"1048576");
	    
	    streamsConfiguration.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "65000");
	    streamsConfiguration.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "60000");
	    //streamsConfiguration.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "15048576");
	    //streamsConfiguration.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG,Arrays.asList(org.apache.kafka.streams.processor.internals.assignment.StickyTaskAssignor.class));
	    
	    //streamsConfiguration.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG,"15048576");
	    //streamsConfiguration.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,net.media.max.kafka.stream.config.RoundRobinPartitioner.class);
	    //streamsConfiguration.put("rebalance.max.retries", "16");
	    //streamsConfiguration.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE);
	    streamsConfiguration.put(StreamsConfig.STATE_DIR_CONFIG, "/tmp/kafka-streams/"+appEnv+"/"+threadName);
	    streamsConfiguration.put(StreamsConfig.NUM_STANDBY_REPLICAS_CONFIG, "1");
	    return streamsConfiguration;
	}
	
}
