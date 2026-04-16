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

    public Item(String nom, String description, int cout) {
        this.nom = nom;
        this.description = description;
        this.cout = cout;
    }

    public void utiliser(Partie partie){}
}
