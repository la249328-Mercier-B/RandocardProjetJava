package be.helha.poo3.randocard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Item.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private String id; // généré par MongoDB
    private String nom;
    private String description;
    private int cout;
    private String imageUrl;

    /**
     * Instantiates a new Item.
     *
     * @param nom         the nom
     * @param description the description
     * @param cout        the cout
     * @param imageUrl    the image url
     */
    public Item(String nom, String description, int cout, String imageUrl) {
        this.nom = nom;
        this.description = description;
        this.cout = cout;
        this.imageUrl = imageUrl;
    }

    /**
     * Utiliser.
     *
     * @param partie the partie
     */
    public void utiliser(Partie partie){}
}
