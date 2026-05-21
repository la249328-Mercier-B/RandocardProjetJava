package be.helha.poo3.randocard.factory;

import be.helha.poo3.randocard.RandocardApplication;
import be.helha.poo3.randocard.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

/**
 * The type Item factory.
 */
public class ItemFactory {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Recuperer item item.
     *
     * @param shortName the short name
     * @param data      the data
     * @return the item
     * @throws Exception the exception
     */
    public static Item recupererItem(String shortName, Map<String, Object> data) throws Exception {
        String base = RandocardApplication.class.getPackageName();
        String fqcn = base + ".model." + shortName;

        try {
            Class<?> clazz = Class.forName(fqcn);

            if (!Item.class.isAssignableFrom(clazz)) {
                throw new IllegalArgumentException(shortName + " n'étend pas Item");
            }

            return (Item) MAPPER.convertValue(data, clazz);

        } catch (Exception e) {
            System.err.println("ERREUR FACTORY pour " + fqcn + " : " + e.getMessage());
            throw e;
        }
    }
}