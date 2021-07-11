package com.sourav.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerDemoWithCallBack {

	private static final String FIRST_TOPIC = "first_topic";
	private static final String BOOTSTRAP_SERVER = "localhost:9092";
	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerDemoWithCallBack.class);

	public static void main(String[] args) {

		/* Create The Producer Properties */
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);

		/* Create the producer */
		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

		/* Create the producer Record */
		ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(FIRST_TOPIC,
				"This is a debugging message");

		/* Send Data */
		/* This is asynchoronous call */
		kafkaProducer.send(producerRecord, (metadata, exception) -> {
			// after completion of each request or error thrown

			if (exception == null) {
				LOGGER.debug("Message sent to kafak sucessfully");
				LOGGER.info("Topic is  " + metadata.topic() + "\n" + "partition is {} " + metadata.partition() + "\n"
						+ "offset is " + metadata.offset() + "\n" + "Timestamp is " + metadata.timestamp());
			} else {
				LOGGER.error("An exception occured", exception.getMessage());
			}

		});
		kafkaProducer.flush();
		kafkaProducer.close();
		LOGGER.info("Message is send to kafka topic");

	}

}
