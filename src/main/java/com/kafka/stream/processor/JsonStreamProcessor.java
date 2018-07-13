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
	
	@SuppressWarnings("unchecked")
	public void process(String streamEvent, String threadName) {
		
		Properties streamsConfiguration = kafkaStreamConfig.getStreamConfiguration(streamEvent,threadName);
	    Serde<String> stringSerde = Serdes.String();

		StreamsBuilder builder = new StreamsBuilder();
		
		//read the json message topic into stream
		KStream<String, String> jsonStream = builder.stream(KAFKA_TOPIC_TEXT_MESSAGE, Consumed.with(stringSerde, stringSerde));
		
		KStream<String, Payload>[] multistreams = jsonStream.
				mapValues(v -> mapTextValues(v)).filter((k,v) -> (v!=null))
				.branch((k, v) -> v!=null);
		
		jsonStream = multistreams[0].mapValues(v -> verifyData(v)).filter((k,v) -> (v!=null))
					.flatMapValues(v -> flattenValue(v))
				  .map(new KeyValueMapper<String, Object, KeyValue<String, String>>() { 
			            @Override 
			            public KeyValue<String, String> apply(String key, Object value) { 
			                return new KeyValue<>(null, gson.toJson(value));
			       }});
		
		
		/*KStream<String, String> nonParStream = multistreams[1]
									.map(new KeyValueMapper<String, Payload, KeyValue<String, String>>() { 
									            @Override 
									            public KeyValue<String, String> apply(String key, Payload value) { 
									                return new KeyValue<>(null, gson.toJson(value));
									       }});
		nonParStream.to(KAFKA_TOPIC_NONPAR_JSON_MESSAGE);*/
		
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List flattenValue(Payload p){
		List payloadList = new ArrayList();
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
			requestDTO.setBusinessSegment(m.getBusinesssegment());
			requestDTO.setPlatform(m.getPlatform());
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
			m.setBusinesssegment(fieldValues[0]);
			m.setPlatform(fieldValues[1]);
			m.setProduct(fieldValues[2]);
			m.setSubgroup(fieldValues[3]);
			m.setStrt_srvc_dt(fieldValues[4]);
			m.setSrvc_loc_prov_id(fieldValues[5]);
			m.setClm_id(fieldValues[6]);
			m.setPst_dt(fieldValues[7]);
			m.setDx(fieldValues[8]);
			m.setPoa(fieldValues[9]);
			m.setCode_class(fieldValues[10]);
			m.setCharges(fieldValues[11]);
			m.setDate(fieldValues[12]);
			m.setHcpcs(fieldValues[13]);
			m.setMod_1(fieldValues[14]);
			m.setMod_2(fieldValues[15]);
			m.setMod_3(fieldValues[16]);
			m.setMod_4(fieldValues[17]);
			m.setPos(fieldValues[18]);
			m.setRev(fieldValues[19]);
			m.setTot_units(fieldValues[20]);
			m.setOp(fieldValues[21]);
			m.setAdmit_date(fieldValues[22]);
			m.setBilltype(fieldValues[23]);
			m.setBirth_date(fieldValues[24]);
			m.setCondcd(fieldValues[25]);
			m.setDstat(fieldValues[26]);
			m.setFacility(fieldValues[27]);
			m.setFrom_date(fieldValues[28]);
			m.setNpi(fieldValues[29]);
			m.setGdr_typ_id(fieldValues[30]);
			m.setTaxonomy(fieldValues[31]);
			m.setThru_date(fieldValues[32]);
			m.setTot_chg(fieldValues[33]);
			m.setValamt1(fieldValues[34]);
			m.setValamt2(fieldValues[35]);
			m.setValamt3(fieldValues[36]);
			m.setValamt4(fieldValues[37]);
			m.setValamt5(fieldValues[38]);
			m.setValamt6(fieldValues[39]);
			m.setValamt7(fieldValues[40]);
			m.setValamt8(fieldValues[41]);
			m.setValcode1(fieldValues[42]);
			m.setValcode2(fieldValues[43]);
			m.setValcode3(fieldValues[44]);
			m.setValcode4(fieldValues[45]);
			m.setValcode5(fieldValues[46]);
			m.setValcode6(fieldValues[47]);
			m.setValcode7(fieldValues[48]);
			m.setValcode8(fieldValues[49]);
			m.setPattype(fieldValues[50]);
			m.setSe(fieldValues[51]);

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
