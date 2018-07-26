package com.kafka.stream;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.TopologyTestDriver;
import org.apache.kafka.streams.test.ConsumerRecordFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.kafka.stream.config.AutoConfig;
import com.kafka.stream.config.KafkaStreamConfig;
import com.kafka.stream.model.Payload;
import com.kafka.stream.model.ResultPayload;
import com.kafka.stream.processor.JsonStreamProcessor;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = {JsonStreamProcessor.class, KafkaStreamConfig.class, AutoConfig.class})
public class KafkaStreamUnitTest {

	@Autowired
    JsonStreamProcessor processor;
	
	@Autowired
	Gson gson;
	
	@Value("${kafka.topic.json.message}")
	private String KAFKA_TOPIC_JSON_MESSAGE;
	
	@Value("${kafka.topic.text.message}")
	private String KAFKA_TOPIC_TEXT_MESSAGE;
	
	String bestCaseMessage = "M&R|COSMOS|Secure Horizons|OEB:112|2017-10-31 00:00:00|536471|0000012800|2018-05-23 00:00:00|J9622|\\N|0|-114|2017-10-31 00:00:00|80048| | |\\N|\\N|23|0301|1|80048|2017-10-28 00:00:00|131|1935-09-03 00:00:00|\\N|\\N|536471|2017-10-28 00:00:00|1114979663|F| |2017-11-02 00:00:00|-8055.32|\\N|\\N|\\N|\\N|\\N|\\N|\\N|\\N|0|0|0|0|0|0|0|0|02|2";
	String worstCaseMessage = "M&RCOSMOS|Secure Horizons|OEB:112|2017-10-31 00:00:00|536471|0000012800|2018-05-23 00:00:00|J9622|\\N|0|-114|2017-10-31 00:00:00|80048| | |\\N|\\N|23|0301|1|80048|2017-10-28 00:00:00|131|1935-09-03 00:00:00|\\N|\\N|536471|2017-10-28 00:00:00|1114979663|F| |2017-11-02 00:00:00|-8055.32|\\N|\\N|\\N|\\N|\\N|\\N|\\N|\\N|0|0|0|0|0|0|0|0|02|2";
	
	private ConsumerRecordFactory<String, String> recordFactory = new ConsumerRecordFactory<>(new StringSerializer(), new StringSerializer());
	private static TopologyTestDriver driver;
	
	@BeforeClass
	public static void before() {
		System.out.println("Starting the test...");
	}
	
	@Test
	public void testABestCase() {
		driver  = (TopologyTestDriver) processor.config("json_stream", "common", true);
		driver.pipeInput(recordFactory.create(KAFKA_TOPIC_TEXT_MESSAGE, "", bestCaseMessage));
		ProducerRecord<String, String> outputRecord = driver.readOutput(KAFKA_TOPIC_JSON_MESSAGE, new StringDeserializer(), new StringDeserializer());
		assertNotNull(outputRecord);
		if(outputRecord!=null) {
			ResultPayload r = gson.fromJson(outputRecord.value(), ResultPayload.class);
			assertNotNull(r.getCcatPayload().getContractId());
		}
		System.out.println("Output Value: "+ outputRecord);
		
		//assertEquals(outputRecord.value(), "");
	}
	
	@Test
	public void testAWorstCase() {
		driver  = (TopologyTestDriver) processor.config("json_stream", "common", true);
		driver.pipeInput(recordFactory.create("input", "", worstCaseMessage));
		ProducerRecord<String, String> outputRecord = driver.readOutput("output", new StringDeserializer(), new StringDeserializer());
		assertNull(outputRecord);
	}
	
	@Test
	public void testBJunitBestCase() {
		Payload p = processor.mapTextValues(bestCaseMessage);
		assertNotNull(p);
	}
	
	@AfterClass
	public static void after() {
		driver.close();
		System.out.println("Closing the test...");
	}
}
