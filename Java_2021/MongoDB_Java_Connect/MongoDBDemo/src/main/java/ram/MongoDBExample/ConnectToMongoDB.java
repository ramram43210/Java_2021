package ram.MongoDBExample;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;

public class ConnectToMongoDB
{

	public static void main(String[] args)
	{
		// Creating a Mongo client
		try (MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Creating Credentials
			MongoCredential mongoCredential = MongoCredential.createCredential("ram", "order", "root123".toCharArray());
			System.out.println("Connected to the database successfully");

			// Accessing the database
			MongoDatabase mongoDatabase = mongoClient.getDatabase("order");
			System.out.println("mongoCredential = " + mongoCredential);
			System.out.println("Database Name = " + mongoDatabase.getName());

			// Creating a collection
			mongoDatabase.createCollection("product");
			System.out.println("Collection created successfully");
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}

	}

}
