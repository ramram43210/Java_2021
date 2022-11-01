package ram.jms;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/*
 * This class is used to send a text message to the queue.
 */
public class KafkaProducerDemo
{

	private final static String TOPIC_NAME = "AnimalTopic";

	public static void main(String[] argv)
	{
		KafkaProducer<String, String> producer = null;

		try
		{
			String bootstrapServers = "127.0.0.1:9092";

			// create Producer properties
			Properties properties = new Properties();
			properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
			properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
			properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

			// create the producer
			producer = new KafkaProducer<>(properties);

			ArrayList<String> animalList = getAnimalList();

			for (String animalName : animalList)
			{
				// create a producer record
				ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME, animalName);
				// send data - asynchronous
				producer.send(producerRecord);
				System.out.println("Successfully sent the Animal Name = '" + animalName + "' to the Topic");
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
