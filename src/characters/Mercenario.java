package ittbuonarroti.rpggame.characters;

/**
 * Rappresenta un'istanza del personaggio Mercenario
 */
public class Mercenario extends Combattente {

    /**
     * Metodo Costruttore<br>
     * Inizializza le statistiche in base ai seguenti parametri (poi randomizzati da {@link Personaggio#assegnaStatistiche}):<br>
     * - puntiVitaTotali: 200<br>
     * - attacco: 50<br>
     * - difesa: 20<br>
     * - velocita: 30<br>
     * - puntiStaminaTotali: 22<br>
     */
    public Mercenario(String nome, char sesso) {
        super(200, 50, 20, 30, 22, nome, sesso);
    }

    /**
     * Metodo statico che restituisce una descrizione del tipo di personaggio
     *
     * @return La stringa da stampare a video
     */
    public static String getDescrizione() {
        return "Questo bruto muscoloso ha sulle spalle anni di duro allenamento, che lo hanno reso tremendamente forte e resistente. " +
                "Predilige un equipaggiamento leggero, per prevalere sui suoi diretti avversari con l'agilità piuttosto che con la difesa.";
    }
}
