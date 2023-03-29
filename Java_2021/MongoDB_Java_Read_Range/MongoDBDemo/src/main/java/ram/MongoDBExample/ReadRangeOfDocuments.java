package ram.MongoDBExample;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class ReadRangeOfDocuments
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

			FindIterable<Document> findIterable = productCollection.find(Filters.gte("price", 10000));
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext())
			{
				System.out.println(mongoCursor.next().toJson());
			}
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}
	}
}
