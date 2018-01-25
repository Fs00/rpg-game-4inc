package ittbuonarroti.rpggame.characters;

/**
 * Rappresenta un'istanza del personaggio Soldato.
 */
public class Soldato extends Combattente implements IDifesa {
    private boolean isDef = false;      // indica se il personaggio ha usato il guard

    public Soldato(String nome, char sesso) {
        super(180, 55, 35, 20, 25, nome, sesso);
    }

    /**
     * Override di {@link Personaggio#modificaPuntiVita(int)} che tiene conto della modalità difesa
     */
    @Override
    public void modificaPuntiVita(int valore) {
        if (this.isDef && valore < 0) {
            valore /= 2;
            isDef = false;
        }
        super.modificaPuntiVita(valore);
    }

    /**
     * Implementazione di {@link IDifesa#preparaDifesa()}
     */
    public void preparaDifesa() {
        isDef = true;
    }
}
