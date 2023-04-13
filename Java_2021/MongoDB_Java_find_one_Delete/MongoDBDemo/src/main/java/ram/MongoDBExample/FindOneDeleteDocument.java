package ram.MongoDBExample;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class FindOneDeleteDocument
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

			Document document = productCollection.findOneAndDelete(Filters.eq("productName", "Alexa")); 
			System.out.println(document.toJson(JsonWriterSettings.builder().indent(true).build()));  
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}
	}
}
