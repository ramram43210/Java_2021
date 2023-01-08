package com.ram.kafkademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ram.kafkademo.model.Animal;

@Service
public class KafkaProducerService
{
	@Autowired
	private KafkaTemplate<String, Animal> kafkaTemplate;
	
	private final static String TOPIC_NAME = "AnimalTopic";

	public void sendMessage(Animal animal)
	{
		kafkaTemplate.send(TOPIC_NAME,animal);
	}
}
