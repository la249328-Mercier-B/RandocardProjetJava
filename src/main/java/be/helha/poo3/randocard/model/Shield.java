package be.helha.poo3.randocard.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Shield extends Item {
    public Shield() {super();}

    @Override
    public void utiliser(Partie partie) {
        partie.activerBouclier();
    }
}