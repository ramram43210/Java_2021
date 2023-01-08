package com.ram.kafkademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ram.kafkademo.model.Animal;
import com.ram.kafkademo.service.KafkaProducerService;

@RestController
public class KafkaMessageController
{
   
    @Autowired
	private KafkaProducerService kafkaProducerService;
 
    @PostMapping("/publish")
    public String publishMessage(@RequestBody Animal animal)
    {
    	kafkaProducerService.sendMessage(animal);
		System.out.println("Successfully Published the Animal = '" + animal + "' to the AnimalTopic");
		return "Successfully Published the Animal = '" + animal + "' to the AnimalTopic";
    }
}
