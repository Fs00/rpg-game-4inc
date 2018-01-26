package ittbuonarroti.rpggame.engine;

import ittbuonarroti.rpggame.characters.Personaggio;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GestionePartita {

    private Personaggio player1, player2;   // giocatori
    private int[] ordineGiocatori;          // array contenente gli indici dei giocatori nell'ordine in cui attaccheranno nel turno corrente
    private int contatoreTurno;             // contatore turno attuale
    private boolean finePartita = false;    // indica se la partita è conclusa o meno
    private int giocatoreFuggito = -1;      // indice giocatore fuggito (-1 nessuno)

    // CODICI MOSSE
    // Nota: le ho riordinate per facilitare i controlli in caso di input errato nel main (@Fs00)
    private final static int MOVE_ATTACK = 1;
    private final static int MOVE_RUN = 2;
    private final static int MOVE_POWER_UP = 3;
    private final static int MOVE_GUARD = 4;
    private final static int MOVE_ITEM = 5;     // unused


    public GestionePartita(Personaggio[] giocatori) {
        this.player1 = giocatori[0];
        this.player2 = giocatori[1];
        ordineGiocatori = new int[2];
        contatoreTurno = 1;
    }

    public static void stampaMessaggio(String messaggio) {
        System.out.println(messaggio);
        // TODO: quando ci sarà la GUI, adattare questo metodo
    }

    /*
     * @deprecated
     * Effettua le dovute preparazioni per il turno di battaglia successivo
     */
    /*public void turnoSuccessivo() {
        contatoreTurno++;

        // Riempi l'array degli indici dei giocatori con i loro indici
        for (int i = 0; i < giocatori.length; i++) {
            ordineGiocatori[i] = i;
        }

        // Riordina gli indici in base alla velocità dei giocatori (l'indice del giocatore più veloce è il primo)
        for (int i = 0; i < ordineGiocatori.length - 1; i++) {
            for (int j = 0; j < ordineGiocatori.length; i++) {
                if (giocatori[i].getVelocita() < giocatori[j].getVelocita()) {
                    int swap = ordineGiocatori[i];
                    ordineGiocatori[i] = ordineGiocatori[j];
                    ordineGiocatori[j] = swap;
                }
            }
        }
    }*/

    /**
     * Metodo che fa effettuare al giocatore passato per parametro (che sarà quello corrente) la mossa richiesta
     *
     * @param indiceGiocatore L'indice del giocatore corrente (1 o 2)
     * @param codiceMossa     Il codice della mossa da effettuare
     */
    public void faiMossa(int indiceGiocatore, int codiceMossa) {

        Personaggio giocatoreCorrente, nemico;

        if (indiceGiocatore == 1) {
            giocatoreCorrente = this.player1;
            nemico = this.player2;
        }
        else {
            giocatoreCorrente = this.player2;
            nemico = this.player1;
        }

        // Nota per @AlibabaSakura: per il guard e il power-up, fai il cast rispettivamente a IDifesa e IAttaccante
        // per poter chiamare le funzioni (vedi la parte del contrattacco in Personaggio.riceviColpo() se non sai come fare)
        switch (codiceMossa) {
            case GestionePartita.MOVE_ATTACK:
                //ATTACCO TODO
                break;
            case GestionePartita.MOVE_GUARD:
                // TODO
                break;
            case GestionePartita.MOVE_POWER_UP:
                // TODO
                break;
            case GestionePartita.MOVE_ITEM:
                throw new NotImplementedException(); //Coming Soon (TM)
            case GestionePartita.MOVE_RUN:
                //FUGA
                if (giocatoreCorrente.ritirata(nemico) == true)
                    this.giocatoreFuggito = indiceGiocatore;
                //TODO Messaggio fuga riuscita o fallita (dovrebbe essere stampato direttamente dalla classe Personaggio)
                break;

        }
    }

    public int[] getOrdineGiocatori() {
        return ordineGiocatori;
    }

    public int getContatoreTurno() {
        return contatoreTurno;
    }

    public boolean controlloPartitaConclusa() {
        //TODO
        return false;
    }

    public int giocatorePiuVeloce() {
        if (player1.getVelocita() > player2.getVelocita())
            return 1;
        else if (player2.getVelocita() > player1.getVelocita())
            return 2;
        else {
            if (RNG.lanciaMoneta() == true)
                return 1;
            else
                return 2;
        }
    }
}
