package ram.MongoDBExample;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
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
			
			System.out.println("Get the Range of documents as a List");
			List<Document> productList = productCollection.find(Filters.gt("price", 10000)).into(new ArrayList<>());
			for (Document productDocument : productList)
			{
				System.out.println(productDocument.toJson());
			}
			
			System.out.println("\nGet the Range of documents using consumer which is a functional interface");
			Consumer<Document> printConsumer = document -> System.out.println(document.toJson());
			productCollection.find(Filters.gt("price", 10000)).forEach(printConsumer);
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}
	}
}
