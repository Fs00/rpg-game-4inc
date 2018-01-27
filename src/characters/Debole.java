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
}