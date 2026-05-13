package be.helha.poo3.randocard.factory;

import be.helha.poo3.randocard.model.Item;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ItemFactoryTest {

    @Test
    void testRecupererMedKit() throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("nom", "MedKit");

        Item item = ItemFactory.recupererItem("MedKit", data);

        assertNotNull(item);
        assertInstanceOf(Item.class, item);
    }

    @Test
    void testRecupererReverseUno() throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("nom", "ReverseUno");

        Item item = ItemFactory.recupererItem("ReverseUno", data);

        assertNotNull(item);
        assertInstanceOf(Item.class, item);
    }

    @Test
    void testRecupererShield() throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("nom", "Shield");

        Item item = ItemFactory.recupererItem("Shield", data);

        assertNotNull(item);
        assertInstanceOf(Item.class, item);
    }

    @Test
    void testRecupererItemClassNotFound() {
        Map<String, Object> data = new HashMap<>();

        // Test avec une classe qui n'existe pas
        assertThrows(ClassNotFoundException.class, () -> {
            ItemFactory.recupererItem("ObjetInexistant", data);
        });
    }

    @Test
    void testRecupererItemNotAnItem() {
        Map<String, Object> data = new HashMap<>();

        // Test avec une classe qui existe (ex: String) mais n'est pas un Item
        assertThrows(Exception.class, () -> {
            ItemFactory.recupererItem("RandocardApplication", data);
        });
    }
}