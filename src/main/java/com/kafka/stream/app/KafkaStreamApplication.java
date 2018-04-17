package com.kafka.stream.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.kafka.stream.processor.StreamProcessor;

@SpringBootApplication
@ComponentScan(basePackages="com.kafka.stream")
public class KafkaStreamApplication implements CommandLineRunner{

	@Autowired
	private StreamProcessor streamProcessor;
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		String streamEvent = args.length>0?args[0]:"text_stream";
		String threadName = args.length>1?args[1]:"common";
		
		streamProcessor.process(streamEvent, threadName);
	}
}
