package be.helha.poo3.randocard.factory;

import be.helha.poo3.randocard.connexion.ConnexionMongoDb;
import be.helha.poo3.randocard.model.Item;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepository {

    private final MongoCollection<Document> collection =
            ConnexionMongoDb.getCollection();

    // Récupère tous les items depuis MongoDB
    public List<Item> findAll() throws Exception {
        List<Item> items = new ArrayList<>();
        for (Document doc : collection.find()) {
            Item item = ItemFactory.recupererItem(doc.getString("nom")); // Item vide
            item.setNom(doc.getString("nom"));
            item.setDescription(doc.getString("description"));
            item.setCout(doc.getInteger("cout"));
            items.add(item);
        }
        return items;
    }

    public Optional<Item> findByNom(String nom) throws Exception {
        Document doc = collection.find(new Document("nom", nom)).first();
        if (doc == null) {
            return Optional.empty();
        }
        Item item = ItemFactory.recupererItem(doc.getString("nom"));
        item.setDescription(doc.getString("description"));
        item.setCout(doc.getInteger("cout"));
        return Optional.of(item);
    }
}