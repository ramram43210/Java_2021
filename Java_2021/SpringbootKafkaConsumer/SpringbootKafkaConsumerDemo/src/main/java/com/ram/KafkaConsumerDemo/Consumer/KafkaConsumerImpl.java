package com.ram.KafkaConsumerDemo.Consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerImpl
{
	@KafkaListener(topics = "AnimalTopic", groupId = "Group100")	
	public void listen(String animalName)
	{
		System.out.println("Received '" + animalName +"' from the AnimalTopic." );
	}
}
