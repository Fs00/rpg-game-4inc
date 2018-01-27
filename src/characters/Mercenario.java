package ittbuonarroti.rpggame.characters;

/**
 * Rappresenta un'istanza del personaggio Mercenario
 */
public class Mercenario extends Combattente {

    /**
     * Metodo Costruttore<br>
     * Inizializza le statistiche in base ai seguenti parametri (poi randomizzati da {@link Personaggio#assegnaStatistiche}):<br>
     * - puntiVitaTotali: 200<br>
     * - attacco: 60<br>
     * - difesa: 25<br>
     * - velocita: 30<br>
     * - puntiStaminaTotali: 22<br>
     */
    public Mercenario(String nome, char sesso) {
        super(200, 60, 25, 30, 22, nome, sesso);
    }
}
