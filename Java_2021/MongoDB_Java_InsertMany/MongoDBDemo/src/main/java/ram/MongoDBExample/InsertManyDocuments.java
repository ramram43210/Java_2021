package ram.MongoDBExample;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class InsertManyDocuments
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

			Document iphoneDocument = new Document("productName", "IPhone")
					.append("description", "IPhone14")
					.append("price", 70000)
					.append("color", "white");	
			
			Document samsungTVDocument = new Document("productName", "Samsung LED TV")
					.append("description", "65 inch LED TV")
					.append("price", 120000)
					.append("Support OTT", "Yes");	
			
			Document alexaDocument = new Document("productName", "Alexa")
					.append("description", "Can Speak")
					.append("price", 5000)
					.append("Support Music", "Yes");
			
			List<Document> documentList = new ArrayList<Document>();
			documentList.add(iphoneDocument);
			documentList.add(samsungTVDocument);
			documentList.add(alexaDocument);
			
			//Inserting the documents
			productCollection.insertMany(documentList);

			System.out.println("Documents inserted successfully");
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}

	}

}
