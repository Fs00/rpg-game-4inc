package ittbuonarroti.rpggame.characters;

/**
 * Rappresenta un'istanza del personaggio Soldato.
 */
public class Soldato extends Combattente implements IDifesa {
    private boolean isDef = false; //Danni raddoppiati

    public Soldato(String nome, char sesso) {
        super(180, 55, 35, 20, 25, nome, sesso);
    }

    /**
     * Override di {@link Personaggio#modificaPuntiVita(int)} che tiene conto della modalità difesa
     */
    public void modificaPuntiVita(int valore) {
        if (this.isDef && valore < 0) {
            valore -= valore * 50 / 100;
            isDef = false;
        }
        super.modificaPuntiVita(valore);
    }

    /**
     * Il personaggio perde un turno per poi subire danni dimezzati nel turno successivo
     * TODO: Far perdere un turno
     */
    @Override
    public void preparaDifesa() {
        isDef = true;
    }
}
