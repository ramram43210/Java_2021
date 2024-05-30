package ram.MongoDBExample;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class InsertOneDocument
{

	public static void main(String[] args)
	{
		MongoClient mongoClient = null;
		try
		{
			// Create a ConnectionString object with your MongoDB connection string
			ConnectionString connectionString = new ConnectionString(
					"mongodb+srv://root:passPASS@cluster0.hmp3vz5.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");

			// Create MongoClientSettings using the ConnectionString
			MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString)
					.build();

			// Create a MongoClient instance
			mongoClient = MongoClients.create(settings);

			// Accessing the order database
			MongoDatabase orderDatabase = mongoClient.getDatabase("order");
			System.out.println("Database Name = " + orderDatabase.getName());

			// Retrieving a product collection
			MongoCollection<Document> productCollection = orderDatabase.getCollection("product");
			System.out.println("product Collection selected successfully");

			// Creating a iphone document
			Document iphoneDocument = new Document("productName", "IPhone").append("description", "IPhone14")
					.append("price", 70000).append("color", "white");

			// Inserting iphone document into the collection
			productCollection.insertOne(iphoneDocument);
			System.out.println("Document inserted successfully");
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}
		finally
		{
			// Don't forget to close the MongoClient when you're done
			mongoClient.close();
		}

	}

}
