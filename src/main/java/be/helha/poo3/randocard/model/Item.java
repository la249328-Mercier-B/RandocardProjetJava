package be.helha.poo3.randocard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private int id;
    private String nom;
    private String description;
    private int cout;
    private int stock;

}
