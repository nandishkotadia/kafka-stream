package com.kafka.stream.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

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
		return new GsonBuilder().disableHtmlEscaping().create();
	}
	
	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(stringHttpMessageConverter());
		messageConverters.add(mappingJackson2HttpMessageConverter());
		
		restTemplate.setMessageConverters(messageConverters);
		return restTemplate;
	}
	
         
	public StringHttpMessageConverter stringHttpMessageConverter(){
		return new StringHttpMessageConverter();
	}
	
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
		List a = new ArrayList();
		a.add(MediaType.ALL);
		MappingJackson2HttpMessageConverter c = new MappingJackson2HttpMessageConverter();
		c.setSupportedMediaTypes(a);
		return c;
	}
	
}
