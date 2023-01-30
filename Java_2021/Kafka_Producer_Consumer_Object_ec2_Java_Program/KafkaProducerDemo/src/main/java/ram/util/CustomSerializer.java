package ram.util;

import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import ram.model.Animal;

public class CustomSerializer implements Serializer<Animal>
{
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void configure(Map<String, ?> configs, boolean isKey)
	{
	}

	@Override
	public byte[] serialize(String topic, Animal animal)
	{
		try
		{
			if (animal == null)
			{
				System.out.println("Null received at serializing");
				return null;
			}
			System.out.println("Serializing...");
			return objectMapper.writeValueAsBytes(animal);
		}
		catch (Exception e)
		{
			throw new SerializationException("Error when serializing Animal to byte[]");
		}
	}

	@Override
	public void close()
	{
	}
}
