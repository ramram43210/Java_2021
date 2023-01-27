package ram.jms;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import ram.model.Animal;

public class KafkaProducerDemo
{

	private final static String TOPIC_NAME = "AnimalTopic";

	public static void main(String[] argv)
	{
		KafkaProducer<String, Animal> producer = null;

		try
		{
			// create Producer properties
			Properties properties = new Properties();
			properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
			properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
			properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "ram.util.CustomSerializer");

			// create the producer
			producer = new KafkaProducer<>(properties);

			ArrayList<Animal> animalList = getAnimalList();

			for (Animal animal : animalList)
			{
				// create a producer record
				ProducerRecord<String, Animal> producerRecord = new ProducerRecord<>(TOPIC_NAME, animal);
				// send data - asynchronous
				producer.send(producerRecord);
				System.out.println("Successfully sent the Animal = '" + animal + "' to the Topic");
				Thread.sleep(4000);
			}

		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}
		finally
		{
			if (producer != null)
			{
				// flush data - synchronous
				producer.flush();
				// flush and close producer
				producer.close();
			}
		}

	}

	private static ArrayList<Animal> getAnimalList()
	{
		ArrayList<Animal> animalList = new ArrayList<Animal>();

		Animal lion = new Animal("Lion", "Orange", "Wild Animal");
		Animal cat = new Animal("Cat", "White", "Domestic Animal");

		animalList.add(lion);
		animalList.add(cat);

		return animalList;
	}
}
