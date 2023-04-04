package ram.MongoDBExample;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;

public class FindOneUpdate
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

			// returns the old version of the document before the update.
			Document oldProductDocument = productCollection.findOneAndUpdate(Filters.eq("productName", "IPhone"),
																		Updates.set("price", 90000));
			System.out.println("old doc = " + oldProductDocument);

			// but I can also request the new version
			FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
			Document newProductDocument = productCollection.findOneAndUpdate(Filters.eq("productName", "Alexa"),
																	Updates.set("price", 10000),optionAfter);
			System.out.println("lastest updated doc = " + newProductDocument);
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}
	}
}
