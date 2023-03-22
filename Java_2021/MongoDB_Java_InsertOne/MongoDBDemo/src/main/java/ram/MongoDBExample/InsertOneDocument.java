package ram.MongoDBExample;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class InsertOneDocument
{

	public static void main(String[] args)
	{
		// Creating a Mongo client
		try (MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Accessing the order database
			MongoDatabase orderDatabase = mongoClient.getDatabase("order");
			System.out.println("Database Name = " + orderDatabase.getName());

			// Retrieving a product collection
			MongoCollection<Document> productCollection = orderDatabase.getCollection("product");
			System.out.println("product Collection selected successfully");

			//Creating a iphone document
			Document iphoneDocument = new Document("productName", "IPhone")
					.append("description", "IPhone14")
					.append("price", 70000)
					.append("color", "white");

			// Inserting iphone document into the collection
			productCollection.insertOne(iphoneDocument);
			System.out.println("Document inserted successfully");
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}

	}

}
