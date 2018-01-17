package ittbuonarroti.rpggame;

public class Combattente extends Personaggio implements IAttaccante {

    protected boolean powUp = false; //Danni raddoppiati
    protected boolean isDef = false; //Mitigazione danni

    public Combattente(int puntiVitaTotali, int puntiStaminaTotali, int velocita, String nome, char sesso) {
        super(puntiVitaTotali, puntiStaminaTotali, velocita, nome, sesso);
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
     * Funzione modifica PV (cura o danno)
     * @param valore numero di PV da aggiungere (valore positivo) o da togliere (valore negativo)
     */
    public void modificaPuntiVita(int valore) {
        if (this.isDef && valore < 0) {
            // TODO Mitigazione (qui valore verrà modificato)
        }
        super.modificaPuntiVita(valore);
    }

    /**
     * Calcolo danni (includendo il moltiplicatore)
     * @param foe Istanza del nemico con tutti i valori.
     * @return Il danno calcolato
     */
    public int attacca(Personaggio foe) {
        return -((this.getAttacco() - foe.getDifesa()) * moltiplicatoreAttacco());
    }

}
