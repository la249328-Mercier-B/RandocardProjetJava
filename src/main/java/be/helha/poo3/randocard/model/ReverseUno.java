package be.helha.poo3.randocard.model;

import lombok.Data;

@Data
public class ReverseUno extends Item {
    public ReverseUno() {
        super("ReverseUno", "Change la carte visible", 2);
    }
}