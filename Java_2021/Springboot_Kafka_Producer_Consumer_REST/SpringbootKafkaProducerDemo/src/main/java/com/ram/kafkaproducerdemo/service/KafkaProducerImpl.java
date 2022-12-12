package com.ram.kafkaproducerdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerImpl
{
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	private final static String TOPIC_NAME = "AnimalTopic";

	public void sendMessage(String animalName)
	{
		kafkaTemplate.send(TOPIC_NAME, animalName);
	}
}
