package be.helha.poo3.randocard.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Random;

@Data
@Builder
public class Carte {
    private int nbAdeviner;
    private int nbVisible;

    public Carte() {
        this.nbAdeviner = genererNbRandom();
        this.nbVisible = genererNbRandom();
    }

    public int genererNbRandom() {
        Random rand = new Random();
        int nb = rand.nextInt(1,11);
        return nb;
    }
}
