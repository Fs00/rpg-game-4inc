package ittbuonarroti.rpggame.characters;

/**
 * Classe astratta da cui derivano {@link Soldato} e {@link Mercenario}.
 */
public abstract class Combattente extends Personaggio implements IAttaccante {
    private int moltAttacco = 1; //Mitigazione danni

    public Combattente(int puntiVitaTotali, int attacco, int difesa, int velocita, int puntiStaminaTotali, String nome, char sesso) {
        super(puntiVitaTotali, attacco, difesa, velocita, puntiStaminaTotali, nome, sesso);
    }

    /**
     * Implementazione di {@link IAttaccante#attacca(Personaggio)}
     */
    // TODO: rivedere implementazione
    /*public int attacca(Personaggio nemico) {
        return -((this.getAttacco() - nemico.getDifesa()) * moltAttacco);
    }*/

}
