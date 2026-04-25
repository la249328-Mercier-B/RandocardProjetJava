package be.helha.poo3.randocard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String id; // généré par MongoDB
    private String nom;
    private String description;
    private int cout;
    private String imageUrl;

    public Item(String nom, String description, int cout, String imageUrl) {
        this.nom = nom;
        this.description = description;
        this.cout = cout;
        this.imageUrl = imageUrl;
    }

    public void utiliser(Partie partie){}
}
