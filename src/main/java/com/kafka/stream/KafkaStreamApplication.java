package com.kafka.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.kafka.stream.processor.JsonStreamProcessor;
import com.kafka.stream.processor.TextStreamProcessor;
import com.kafka.stream.util.Constants;

@SpringBootApplication
@ComponentScan(basePackages="com.kafka.stream")
public class KafkaStreamApplication implements CommandLineRunner, Constants{

	@Autowired
	private TextStreamProcessor textStreamProcessor;
	
	@Autowired
	private JsonStreamProcessor jsonStreamProcessor;
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		String streamEvent = args.length>0?args[0]:"json_stream";
		String threadName = args.length>1?args[1]:"common";
		
		jsonStreamProcessor.process(streamEvent, threadName);
		
		/*if(TEXT_STREAM.equals(streamEvent)) {
			textStreamProcessor.process(streamEvent, threadName);
		} else if(JSON_STREAM.equals(streamEvent)) {
			
		}*/
	}
}
