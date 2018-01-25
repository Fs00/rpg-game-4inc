package ittbuonarroti.rpggame.engine;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe utilizzata per la generazione di numeri casuali
 */
public class RNG {

    /**
     * Genera un numero random da un valore di inizio e di quanti numeri a partire da quest'ultimo aggiungere al range dell'RNG
     *
     * @param start il numero più basso per la generazione casuale (incluso)
     * @param end Il limite massimo per la generazione (incluso)
     * @return Il numero generato
     */
    public static int random(int start, int end) {
        return ThreadLocalRandom.current().nextInt(start, end + 1);
    }

    /**
     * Lancio della moneta
     *
     * @return true se testa, false se croce
     */
    public static boolean lanciaMoneta() {
        return ThreadLocalRandom.current().nextInt(0, 2) == 0;
    }
}
