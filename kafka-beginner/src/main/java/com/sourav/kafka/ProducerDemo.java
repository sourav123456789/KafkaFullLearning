package com.sourav.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerDemo {

	private static final String FIRST_TOPIC = "first_topic";
	private static final String BOOTSTRAP_SERVER = "localhost:9092";
	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerDemo.class);

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
				"Hello world111111");

		/* Send Data */
		/* This is ana synchoronous call */
		kafkaProducer.send(producerRecord);
		kafkaProducer.flush();
		kafkaProducer.close();
		LOGGER.info("Message is send to kafka topic");

	}

}
