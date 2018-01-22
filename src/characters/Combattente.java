package ittbuonarroti.rpggame.characters;

/**
 * Classe astratta da cui derivano {@link Soldato} e {@link Mercenario}.
 */
public abstract class Combattente extends Personaggio implements IAttaccante {

    protected boolean powUp = false; //Danni raddoppiati
    protected boolean isDef = false; //Mitigazione danni

    public Combattente(int puntiVitaTotali, int attacco, int difesa, int velocita, int puntiStaminaTotali, String nome, char sesso) {
        super(puntiVitaTotali, attacco, difesa, velocita, puntiStaminaTotali, nome, sesso);
    }

    /**
     * Moltiplicatore danno (da applicare durante il calcolo dei danni)
     * @return il valore del danno da moltiplicare
     */
    private int moltiplicatoreAttacco() {
        if (this.powUp)
            return 2;
        else
            return 1;
    }

    /**
     * Override di {@link Personaggio#modificaPuntiVita(int)} che tiene conto della modalità difesa
     */
    public void modificaPuntiVita(int valore) {
        if (this.isDef && valore < 0) {
            // TODO Mitigazione (qui valore verrà modificato)
        }
        super.modificaPuntiVita(valore);
    }

    /**
     * Implementazione di {@link IAttaccante#attacca(Personaggio)}
     */
    // TODO: rivedere implementazione
    public int attacca(Personaggio nemico) {
        return -((this.getAttacco() - nemico.getDifesa()) * moltiplicatoreAttacco());
    }

}
