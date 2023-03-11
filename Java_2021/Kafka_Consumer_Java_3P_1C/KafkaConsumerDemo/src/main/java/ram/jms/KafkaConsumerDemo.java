package ram.jms;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaConsumerDemo
{
	private final static String TOPIC_NAME = "AnimalTopic";

	public static void main(String[] args)
	{

		// create consumer configs
		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "Group99");
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		// create consumer
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

		// get a reference to the current thread
		final Thread mainThread = Thread.currentThread();

		// adding the shutdown hook
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				System.out.println("Detected a shutdown, let's exit by calling consumer.wakeup()...");
				consumer.wakeup();

				// join the main thread to allow the execution of the
				// code in the main thread
				try
				{
					mainThread.join();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});

		try
		{

			// subscribe consumer to our topic(s)
			consumer.subscribe(Arrays.asList(TOPIC_NAME));

			// poll for new data
			while (true)
			{
				ConsumerRecords<String, String> ConsumerRecords = consumer.poll(Duration.ofMillis(100));

				for (ConsumerRecord<String, String> consumerRecord : ConsumerRecords)
				{
					System.out.print("Key: " + consumerRecord.key() + ", Value: " + consumerRecord.value());
					System.out.println(", Partition: " + consumerRecord.partition() + ", Offset:" + consumerRecord.offset());
				}
			}

		}
		catch (WakeupException e)
		{
			System.out.println("Wake up exception!");
			e.printStackTrace();
			// we ignore this as this is an expected exception when
			// closing a consumer
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			// this will also commit the offsets if need be.
			consumer.close();
			System.out.println("The consumer is now gracefully closed.");
		}

	}
}
