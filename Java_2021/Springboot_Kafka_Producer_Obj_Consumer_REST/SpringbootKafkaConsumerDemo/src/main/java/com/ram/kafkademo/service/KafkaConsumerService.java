package com.ram.kafkademo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ram.kafkademo.model.Animal;

@Service
public class KafkaConsumerService
{
	@KafkaListener(topics = "AnimalTopic", groupId = "Group100",containerFactory = "animalListener")	
	public void listen(Animal animal)
	{
		System.out.println("Received '" + animal +"' from the AnimalTopic." );
	}
}
