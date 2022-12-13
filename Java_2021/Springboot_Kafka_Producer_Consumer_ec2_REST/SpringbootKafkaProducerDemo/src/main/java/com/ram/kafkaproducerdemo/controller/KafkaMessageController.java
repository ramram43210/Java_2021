package com.ram.kafkaproducerdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.kafkaproducerdemo.service.KafkaProducerImpl;

@RestController
public class KafkaMessageController
{
   
    @Autowired
	private KafkaProducerImpl kafkaProducerImpl;
 
    @PostMapping("/publish/{animalName}")
    public String publishMessage(@PathVariable("animalName")final String animalName)
    {
    	kafkaProducerImpl.sendMessage(animalName);
		System.out.println("Successfully Published the Animal Name = '" + animalName + "' to the AnimalTopic");
		return "Successfully Published the Animal Name = " + animalName + " to the AnimalTopic";
    }
}
