package ittbuonarroti.rpggame.characters;

/**
 * Rappresenta un'istanza del personaggio Debole
 */
public class Debole extends Personaggio {

    /**
     * Metodo Costruttore<br>
     * Inizializza le statistiche in base ai seguenti parametri (poi randomizzati da {@link Personaggio#assegnaStatistiche}):<br>
     * - puntiVitaTotali: 50<br>
     * - attacco: 0<br>
     * - difesa: 0<br>
     * - velocita: 40<br>
     * - puntiStaminaTotali: 5<br>
     */
    public Debole (String nome, char sesso) {
        super(50, 0, 0, 40, 5, nome, sesso);
    }

    /**
     * Metodo statico che restituisce una descrizione del tipo di personaggio e le statistiche di base
     *
     * @return La stringa da stampare a video
     */
    public static String getDescrizione() {
        return "Un normale abitante, senza nulla di speciale. È praticamente incapace di combattere, " +
                "tuttavia a svignarsela se la cava tutto sommato non male." +
                "\n Statistiche di base: PV 50, ATK 0, DEF 0, VEL 40, STM 5";
    }
}