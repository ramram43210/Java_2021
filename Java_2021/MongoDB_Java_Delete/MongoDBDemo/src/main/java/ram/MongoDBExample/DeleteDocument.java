package ram.MongoDBExample;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

public class DeleteDocument
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

			DeleteResult deleteResult = productCollection.deleteOne(Filters.eq("productName", "Alexa")); 
			System.out.println("Deleted Dcoument count = "+deleteResult.getDeletedCount());  
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}
	}
}
