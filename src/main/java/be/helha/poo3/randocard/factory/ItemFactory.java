package be.helha.poo3.randocard.factory;

import be.helha.poo3.randocard.RandocardApplication;
import be.helha.poo3.randocard.model.Item;

public class ItemFactory {

    public static Item creerItem(String shortName) throws Exception {
        String base = RandocardApplication.class.getPackageName();
        String fqcn = base + ".model." + shortName;

        Class<?> clazz;
        try {
            clazz = Class.forName(fqcn);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("La classe %s n'existe pas".formatted(fqcn), e);
        }

        if (!Item.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("%s n'étend pas Item".formatted(clazz.getName()));
        }

        return (Item) clazz.getDeclaredConstructor().newInstance();
    }
}