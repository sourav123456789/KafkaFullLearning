package com.github.sourav.producer.sendmessage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@PostConstruct
	private void sendMessage() {
		kafkaTemplate.send("first_topic", "First message from Spring boot");
	}

}
