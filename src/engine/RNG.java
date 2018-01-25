package ittbuonarroti.rpggame.engine;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe utilizzata per la generazione di numeri casuali
 */
public class RNG {

    /**
     * Genera un numero casuale compreso tra due valori
     *
     * @param start il numero più basso per la generazione casuale (incluso)
     * @param end Il limite massimo per la generazione (incluso)
     * @return Il numero generato
     */
    public static int randomNumber(int start, int end) {
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
