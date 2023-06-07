package ram.MongoDBExample;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DropCollection
{

	public static void main(String[] args)
	{
		// Creating a Mongo client
		try (MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Connecting to the database
			MongoDatabase orderDatabase = mongoClient.getDatabase("order");
			
			MongoCollection<Document> productCollection = orderDatabase.getCollection("product");
			// Dropping a Collection
			productCollection.drop();
			System.out.println("Collection removed successfully");
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}
	}
}
