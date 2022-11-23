package com.ram.KafkaProducerDemo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ram.KafkaProducerDemo.Producer.KafkaProducerImpl;

@SpringBootApplication
public class KafkaProducerDemoApplication implements ApplicationRunner
{
	@Autowired
	private KafkaProducerImpl kafkaProducerImpl;

	public static void main(String[] args)
	{
		SpringApplication.run(KafkaProducerDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		ArrayList<String> animalList = getAnimalList();

		for (String animalName : animalList)
		{
			kafkaProducerImpl.sendMessage(animalName);
			System.out.println("Successfully sent the Animal Name = '" + animalName + "' to the AnimalTopic");
			Thread.sleep(4000);
		}
	}

	private static ArrayList<String> getAnimalList()
	{
		ArrayList<String> animalList = new ArrayList<String>();
		animalList.add("Dog");
		animalList.add("Lion");
		animalList.add("Tiger");
		animalList.add("Snake");
		animalList.add("Cat");

		return animalList;
	}

}
