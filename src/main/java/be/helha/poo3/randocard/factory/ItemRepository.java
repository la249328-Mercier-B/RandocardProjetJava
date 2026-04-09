package be.helha.poo3.randocard.factory;

import be.helha.poo3.randocard.connexion.ConnexionMongoDb;
import be.helha.poo3.randocard.factory.ItemFactory;
import be.helha.poo3.randocard.model.Item;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepository {

    private final MongoCollection<Document> collection =
            ConnexionMongoDb.getCollection();

    // Récupère tous les items depuis MongoDB
    public List<Item> findAll() throws Exception {
        List<Item> items = new ArrayList<>();
        for (Document doc : collection.find()) {
            Item item = ItemFactory.creerItem(doc.getString("nom"));
            item.setNom(doc.getString("nom"));
            item.setDescription(doc.getString("description"));
            item.setCout(doc.getInteger("cout"));
            items.add(item);
        }
        return items;
    }
}