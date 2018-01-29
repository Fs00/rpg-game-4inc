package ittbuonarroti.rpggame.characters;

import ittbuonarroti.rpggame.engine.GestionePartita;

/**
 * Rappresenta un'istanza del personaggio Soldato
 */
public class Soldato extends Combattente implements IDifesa {

    /**
     * Booleano che indica se il Soldato ha attivato (true)
     * lo scudo che dimezza gli attacchi subiti
     */
    private boolean isDef = false;

    /**
     * Metodo Costruttore<br>
     * Inizializza le statistiche in base ai seguenti parametri (poi randomizzati da {@link Personaggio#assegnaStatistiche}):<br>
     * - puntiVitaTotali: 180<br>
     * - attacco: 55<br>
     * - difesa: 35<br>
     * - velocita: 20<br>
     * - puntiStaminaTotali: 25<br>
     */
    public Soldato(String nome, char sesso) {
        super(180, 55, 35, 20, 25, nome, sesso);
    }

    /**
     * Override di {@link Personaggio#modificaPuntiVita(int)} che tiene conto della modalità difesa
     */
    @Override
    public void modificaPuntiVita(int valore) {
        if (this.isDef && valore < 0) {
            GestionePartita.stampaMessaggio("I danni dell'attacco sono stati dimezzati dallo scudo!");
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

    /**
     * Metodo statico che restituisce una descrizione del tipo di personaggio
     *
     * @return La stringa da stampare a video
     */
    public static String getDescrizione() {
        return "Un veterano dell'esercito. Il grosso scudo e la pesante armatura che porta con sé non lo aiutano certo " +
                "ad essere veloce, ma contribuiscono di molto a renderlo uno dei guerrieri più resistenti in assoluto.";
    }
}
