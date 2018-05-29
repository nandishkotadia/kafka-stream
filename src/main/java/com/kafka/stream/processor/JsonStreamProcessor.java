package com.kafka.stream.processor;

import java.util.Date;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.kafka.stream.config.KafkaStreamConfig;
import com.kafka.stream.model.AccessTokenDTO;
import com.kafka.stream.model.Payload;
import com.kafka.stream.model.RequestDTO;
import com.kafka.stream.model.ResponseDTO;
import com.kafka.stream.model.ResultPayload;

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
	
	@Value("${data.accesstoken.url}")
	private String accessTokenUrl;
	
	@Value("${client.id}")
	private String clientId;
	
	@Value("${client.secret}")
	private String clientSecret;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private String accessToken;
	
	private Date expiryDate;
	
	public void process(String streamEvent, String threadName) {
		
		Properties streamsConfiguration = kafkaStreamConfig.getStreamConfiguration(streamEvent,threadName);
	    Serde<String> stringSerde = Serdes.String();

		StreamsBuilder builder = new StreamsBuilder();
		
		//read the json message topic into stream
		KStream<String, String> jsonStream = builder.stream(KAFKA_TOPIC_JSON_MESSAGE, Consumed.with(stringSerde, stringSerde));
		
		jsonStream.mapValues(v -> verifyData(v)).filter((k,v) -> (v!=null)).map(new KeyValueMapper<String, Payload, KeyValue<String, String>>() { 
            @Override 
            public KeyValue<String, String> apply(String key, Payload value) { 
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

	private Payload verifyData(String jsonMessage) {
		String token = getAccessToken();
		Payload m = null;
		try {
			m = gson.fromJson(jsonMessage, Payload.class);
			RequestDTO requestDTO = new RequestDTO();
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.add("Authorization", token);
				HttpEntity entity = new HttpEntity<>(requestDTO, headers);
				ResponseDTO response = restTemplate.postForObject(validateUrl, entity, ResponseDTO.class);
				ResultPayload payload = new ResultPayload();
				BeanUtils.copyProperties(payload, m);
				payload.setContractId(response.getData().getContractId());
				return payload;
			}catch(Exception e) {
				logger.error("Error in verifyData(): ", e);
			}
			return null;
		}catch(Exception e) {
			logger.error("Error with message: "+jsonMessage);
			logger.error("Error in verifyData()",e);
		}
		return null;
	}

	private String getAccessToken() {
		Date currentDate = new Date();
		if(!StringUtils.isEmpty(accessToken) && currentDate.before(expiryDate)) {
			return accessToken;
		}
		MultiValueMap params = new LinkedMultiValueMap<>();
		params.add("client_id", clientId);
		params.add("client_secret", clientSecret);
		params.add("grant_type", "client_credentials");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity entity = new HttpEntity<>(params, headers);
		AccessTokenDTO accessTokenResponse = restTemplate.postForObject(accessTokenUrl, entity, AccessTokenDTO.class);
		if(accessTokenResponse != null) {
			accessToken = "Bearer " + accessTokenResponse.getAccess_token();
			Long expires_in = accessTokenResponse.getExpires_in();
			Long expireDateLongValue = currentDate.getTime() + ((expires_in - 300 ) * 1000);
			expiryDate = new Date(expireDateLongValue);
			return accessToken;
		}
		
		return null;
	}

}
