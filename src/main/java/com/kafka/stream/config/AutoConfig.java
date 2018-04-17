package com.kafka.stream.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
@PropertySources({
	@PropertySource({"classpath:application.properties"}),
	@PropertySource({"classpath:${app.env}/kafka.properties"})
})
public class AutoConfig{

	@Bean
	public Gson gson(){
		return new GsonBuilder().create();
	}
	
}
