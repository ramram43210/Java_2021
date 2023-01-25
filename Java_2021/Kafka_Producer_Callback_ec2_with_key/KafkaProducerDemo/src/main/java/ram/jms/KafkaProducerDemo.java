package ram.jms;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * This class is used to send a text message to the Topic.
 */
public class KafkaProducerDemo
{

	private final static String TOPIC_NAME = "AnimalTopic";
	public static Logger logger = LoggerFactory.getLogger(KafkaProducerDemo.class);

	public static void main(String[] argv)
	{
		KafkaProducer<String, String> producer = null;

		try
		{
			// create Producer properties
			Properties properties = new Properties();
			properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "ec2-3-86-232-67.compute-1.amazonaws.com:8082");
			properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
			properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

			// create the producer
			producer = new KafkaProducer<>(properties);

			ArrayList<String> animalList = getAnimalList();

			int i = 0;
			for (String animalName : animalList)
			{
				++i;
				String key = "Id_"+ Integer.toString(i);
				String value = animalName;
				
				// create a producer record
				ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC_NAME,key,value);
				logger.info("key = "+ key+ ", Value = "+ value);
				
				// send data - asynchronous
				producer.send(producerRecord, new Callback()
				{
					public void onCompletion(RecordMetadata recordMetadata, Exception e)
					{
						if (e == null)
						{
							logger.info("Successfully received the details as: \n" 
						              + "Topic = " + recordMetadata.topic()+ "\n"
									  + "Partition = " + recordMetadata.partition() + "\n" 
						              + "Offset = "+ recordMetadata.offset() + "\n" 
									  + "Timestamp = " + recordMetadata.timestamp());
						}
						else
						{
							logger.error("Can't produce,getting error", e);
						}
					}
				}).get(); //Sending Synchronous data forcefully
				System.out.println("Successfully sent the Animal Name = '" + value + "' to the Topic");
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
