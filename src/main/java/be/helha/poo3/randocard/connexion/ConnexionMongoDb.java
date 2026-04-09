package be.helha.poo3.randocard.connexion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ConnexionMongoDb {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            database = mongoClient.getDatabase("RandocardItems");
        }
        return database;
    }

    public static MongoCollection<Document> getCollection() {
        return getDatabase().getCollection("Items");
    }
}

