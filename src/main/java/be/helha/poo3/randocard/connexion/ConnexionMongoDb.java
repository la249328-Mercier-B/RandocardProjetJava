package be.helha.poo3.randocard.connexion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.bson.Document;
import org.springframework.stereotype.Component;

/**
 * The type Connexion mongo db.
 */
@Component
public class ConnexionMongoDb {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    @Value("${spring.mongodb.host}")
    private String mongoHost;

    @Value("${spring.mongodb.port}")
    private int mongoPort;

    @Value("${spring.mongodb.database}")
    private String dbName;

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create("mongodb://" + mongoHost + ":" + mongoPort);
            database = mongoClient.getDatabase(dbName); // ← lit depuis les properties
        }
    }

    /**
     * Gets database.
     *
     * @return the database
     */
    public static MongoDatabase getDatabase() {
        return database;
    }

    /**
     * Gets collection.
     *
     * @return the collection
     */
    public static MongoCollection<Document> getCollection() {
        return getDatabase().getCollection("Items");
    }
}

