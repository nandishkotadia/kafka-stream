package com.kafka.stream.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.kafka.stream.util.Constants;

@Component
public class JsonStreamProcessor {

	@Autowired
	private KafkaStreamConfig kafkaStreamConfig;
	
	@Autowired
	private Gson gson ;
	
	@Value("${kafka.topic.json.message}")
	private String KAFKA_TOPIC_JSON_MESSAGE;
	
	@Value("${kafka.topic.text.message}")
	private String KAFKA_TOPIC_TEXT_MESSAGE;
	
	@Value("${kafka.topic.nonpar.json.message}")
	private String KAFKA_TOPIC_NONPAR_JSON_MESSAGE;
	
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
	private static String TZ_DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss.S'Z'";
	private static String INPUT_DATEFORMAT = "yyyy-MM-ddHH:mm:ss";
	
	public void process(String streamEvent, String threadName) {
		
		Properties streamsConfiguration = kafkaStreamConfig.getStreamConfiguration(streamEvent,threadName);
	    Serde<String> stringSerde = Serdes.String();

		StreamsBuilder builder = new StreamsBuilder();
		
		//read the json message topic into stream
		KStream<String, String> jsonStream = builder.stream(KAFKA_TOPIC_TEXT_MESSAGE, Consumed.with(stringSerde, stringSerde));
		
		KStream<String, Payload>[] multistreams = jsonStream.
				mapValues(v -> mapTextValues(v)).filter((k,v) -> (v!=null))
				.branch((k, v) -> "par".equals(v.getBus_seg_id()),
						(k, v) -> "nonpar".equals(v.getBus_seg_id()));
		
		jsonStream = multistreams[0].mapValues(v -> verifyData(v)).filter((k,v) -> (v!=null))
					.flatMapValues(v -> flattenValue(v))
				  .map(new KeyValueMapper<String, Payload, KeyValue<String, String>>() { 
			            @Override 
			            public KeyValue<String, String> apply(String key, Payload value) { 
			                return new KeyValue<>(null, gson.toJson(value));
			       }});
		
		
		KStream<String, String> nonParStream = multistreams[1]
									.map(new KeyValueMapper<String, Payload, KeyValue<String, String>>() { 
									            @Override 
									            public KeyValue<String, String> apply(String key, Payload value) { 
									                return new KeyValue<>(null, gson.toJson(value));
									       }});
		
		nonParStream.to(KAFKA_TOPIC_NONPAR_JSON_MESSAGE);
		
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

	private List<Payload> flattenValue(Payload p){
		List<Payload> payloadList = new ArrayList<Payload>();
		payloadList.add(p);
		payloadList.add(p);
		return payloadList;
	}
	
	private Payload verifyData(Payload m) {
		try {
			String token = getAccessToken();
			logger.info("Token: "+token);
			if(StringUtils.isEmpty(token)) {
				logger.info("Input Msg:"+ gson.toJson(m));
				logger.info("Token is empty");
				return null;
			}
			//m = gson.fromJson(jsonMessage, Payload.class);
			RequestDTO requestDTO = new RequestDTO();
			requestDTO.setBusinessSegment(m.getBus_seg_id());
			requestDTO.setPlatform(m.getClm_adjd_pltfm_id());
			requestDTO.setStartServiceDate(m.getStrt_srvc_dt());
//			requestDTO.setStartServiceDate(getFormattedTZDateFromString(m.getStrt_srvc_dt()));
			requestDTO.setProduct(m.getProduct());
			requestDTO.setProviderId(m.getSrvc_loc_prov_id());
			requestDTO.setSubgroup(m.getSubgroup());
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.add("Authorization", token);
				HttpEntity entity = new HttpEntity<>(requestDTO, headers);
				ResponseDTO response = restTemplate.postForObject(validateUrl, entity, ResponseDTO.class);
				
				Long contractId = response.getData();
				logger.info("ContractId: "+contractId);
				if(contractId == null) {
					logger.info("Input Msg:"+ gson.toJson(m));
					logger.info("API Response Msg: "+ gson.toJson(response));
					return null;
				}
				m.setContractId(response.getData());
				return m;
			}catch(Exception e) {
				logger.info("Input Msg:"+ gson.toJson(m));
				logger.error("Error in verifyData(): ", e);
			}
			return null;
		}catch(Exception e) {
			//logger.error("Error with message: "+jsonMessage);
			logger.info("Input Msg:"+ gson.toJson(m));
			logger.error("Error in verifyData(): ", e);
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
		ResponseEntity<AccessTokenDTO> result = restTemplate.postForEntity(accessTokenUrl, entity, AccessTokenDTO.class);
		AccessTokenDTO accessTokenResponse = result.getBody();
		if(accessTokenResponse != null) {
			accessToken = "Bearer " + accessTokenResponse.getAccess_token();
			Long expires_in = accessTokenResponse.getExpires_in();
			Long expireDateLongValue = currentDate.getTime() + ((expires_in - 300 ) * 1000);
			expiryDate = new Date(expireDateLongValue);
			return accessToken;
		}
		
		return null;
	}
	
	private Payload mapTextValues(String message) {
		if(StringUtils.isEmpty(message)) {
			return null;
		}
		try {
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
		}catch(Exception e) {
			logger.info("Text Msg:"+ message);
			logger.error("Error in mapTextValues(): ", e);
			return null;
		}
	}
	
	private Long getLongValue(String value) {
		return StringUtils.isEmpty(value)?null:Long.parseLong(value);
	}
	
	/*private String getFormattedTZDateFromString(String strt_srvc_dt) throws ParseException {
		Date d = stringToDateFormat(strt_srvc_dt, INPUT_DATEFORMAT);
		return dateToStringFormat(d, TZ_DATEFORMAT);
	}

	public static Date stringToDateFormat(String dateInString, String format) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = dateFormat.parse(dateInString);
		return date;
	}
	
	public static String dateToStringFormat(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String dateStr = dateFormat.format(date);
		return dateStr;
	} */

}
