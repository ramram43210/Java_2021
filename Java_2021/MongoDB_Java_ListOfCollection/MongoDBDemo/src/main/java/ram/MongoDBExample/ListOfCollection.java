package ram.MongoDBExample;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class ListOfCollection
{

	public static void main(String[] args)
	{
		// Creating a Mongo client
		try (MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Connecting to the database
			MongoDatabase orderDatabase = mongoClient.getDatabase("order");
			
			// Retrieving the list of collections
			MongoIterable<String> listOfCollectionName = orderDatabase.listCollectionNames();
			for (String collectionName : listOfCollectionName)
			{
				System.out.println(collectionName);
			}
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}
	}
}
