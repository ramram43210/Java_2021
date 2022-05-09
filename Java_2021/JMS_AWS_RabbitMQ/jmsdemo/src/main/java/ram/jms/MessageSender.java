package ram.jms;

import java.util.Date;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/*
 * This class is used to send a text message to the queue.
 */
public class MessageSender
{

	private final static String QUEUE_NAME = "Message_Queue";

	public static void main(String[] argv) throws Exception
	{
		ConnectionFactory factory = new ConnectionFactory();

		factory.setHost("ec2-44-203-127-17.compute-1.amazonaws.com");
		/*
		 * By default, RabbitMQ will listen on port 5672 on all
		 * available interfaces
		 */
		factory.setPort(5672);
		try (
				Connection connection = factory.newConnection();
				Channel channel = connection.createChannel())

		{
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			String message = "Hello World! - " + new Date();
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
			System.out.println(" [x] Sent '" + message + "'");
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}

	}
}
