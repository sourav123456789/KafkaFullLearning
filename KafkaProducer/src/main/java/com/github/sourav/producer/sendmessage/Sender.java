package com.github.sourav.producer.sendmessage;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class Sender {
	
	
	private static final Logger log = LoggerFactory.getLogger(Sender.class);


	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@PostConstruct
	private void sendMessage() {
		ListenableFuture<SendResult<String, Object>> sendFuture = kafkaTemplate.send("first_topic",
				"First message from Spring boot");
		
		sendFuture.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

			@Override
			public void onSuccess(SendResult<String, Object> result) {
				log.info("Topic is: "+result.getRecordMetadata().topic() + "\n"
						 + "PArtition is: "+result.getRecordMetadata().partition() + "\n"
						 + "Offset is: " +result.getRecordMetadata().offset() + "\n" 
						 + "Timestamp is: " + result.getRecordMetadata().timestamp());
				
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("An error occured" + ex.getMessage());
			}
			
		});
	}

}
