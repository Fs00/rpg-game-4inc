package ittbuonarroti.rpggame.characters;

/**
 * Rappresenta un'istanza del personaggio Soldato.
 */
public class Soldato extends Combattente {

    public Soldato(String nome, char sesso) {
        super(180, 55, 35, 20, 25, nome, sesso);
    }

    public Soldato() {
        super(180, 55, 35, 20, 25, "Soldato", 'M');
    }

}
