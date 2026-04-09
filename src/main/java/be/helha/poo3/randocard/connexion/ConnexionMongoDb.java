package be.helha.poo3.randocard.connexion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnexionMongoDb {

        private static MongoClient mongoClient;
        private static MongoDatabase database;

        public static MongoDatabase getDatabase() {
            if (mongoClient == null) {
                mongoClient = MongoClients.create("mongodb://localhost:27017");
                database = mongoClient.getDatabase("TestDB");
            }
            return database;
        }
    }

