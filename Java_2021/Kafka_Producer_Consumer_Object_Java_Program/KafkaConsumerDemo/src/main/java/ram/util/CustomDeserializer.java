package ram.util;

import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import ram.model.Animal;

public class CustomDeserializer implements Deserializer<Animal>
{
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void configure(Map<String, ?> configs, boolean isKey)
	{
	}

	@Override
	public Animal deserialize(String topic, byte[] data)
	{
		try
		{
			if (data == null)
			{
				System.out.println("Null received at deserializing");
				return null;
			}
			System.out.println("Deserializing...");
			return objectMapper.readValue(new String(data, "UTF-8"), Animal.class);
		}
		catch (Exception e)
		{
			throw new SerializationException("Error when deserializing byte[] to Animal");
		}
	}

	@Override
	public void close()
	{
	}
}