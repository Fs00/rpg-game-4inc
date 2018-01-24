package ittbuonarroti.rpggame.engine;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe utilizzata per la generazione di numeri casuali
 */
public class RNG {

    /**
     * Genera un numero random da un valore di inizio e di quanti numeri a partire da quest'ultimo aggiungere al range dell'RNG
     *
     * @param inizio il numero più basso per la generazione casuale
     * @param numeri il numero dei numeri per il range
     * @return
     */
    public static int random(int inizio, int numeri) {
        return ThreadLocalRandom.current().nextInt(inizio, numeri);
    }

    /**
     * Lancio della moneta
     *
     * @return true se testa, false se croce
     */
    public static boolean flipCoin() {
        return ThreadLocalRandom.current().nextInt(0, 2) == 0;
    }
}
