package ittbuonarroti.rpggame.engine;

import ittbuonarroti.rpggame.characters.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GestionePartita {

    private Personaggio player1, player2;   // giocatori
    private int contatoreTurno;             // contatore turno attuale
    private int vincitore = -1;             // indica se la partita è conclusa o meno (-1 non terminata, 0 pareggio, il resto indici giocatori)
    private int giocatoreFuggito = -1;      // indice giocatore fuggito (-1 nessuno)

    // CODICI MOSSE
    public final static int MOVE_ATTACK = 1;
    public final static int MOVE_RUN = 2;
    public final static int MOVE_POWER_UP = 3;
    public final static int MOVE_GUARD = 4;
    public final static int MOVE_ITEM = 5;     // unused


    public GestionePartita(Personaggio[] giocatori) {
        this.player1 = giocatori[0];
        this.player2 = giocatori[1];
        contatoreTurno = 1;
    }

    public static void stampaMessaggio(String messaggio) {
        System.out.println(messaggio);
        // TODO: quando ci sarà la GUI, adattare questo metodo
    }

    public int getVincitore() {
        return vincitore;
    }

    public int getContatoreTurno() {
        return contatoreTurno;
    }

    /**
     * Ottiene l'oggetto Personaggio legato al giocatore specificato
     *
     * @param indice Indice del giocatore (1 o 2)
     * @return L'oggetto del giocatore richiesto
     * @throws IllegalArgumentException se l'indice non è 1 o 2
     */
    public Personaggio getPlayerCharacter(int indice) {
        if (indice == 1)
            return player1;
        else if (indice == 2)
            return player2;
        else
            throw new IllegalArgumentException("L'indice del giocatore non è valido.");
    }

    /**
     * Metodo che fa effettuare al giocatore passato per parametro (che sarà quello corrente) la mossa richiesta
     *
     * @param indiceGiocatore L'indice del giocatore corrente (1 o 2)
     * @param codiceMossa     Il codice della mossa da effettuare
     */
    public void faiMossa(int indiceGiocatore, int codiceMossa) {

        Personaggio giocatoreCorrente, nemico;
        boolean mossaCompletata = false;

        if (indiceGiocatore == 1) {
            giocatoreCorrente = player1;
            nemico = player2;
        }
        else {
            giocatoreCorrente = player2;
            nemico = player1;
        }

        // Premessa: attacco, caricaAttacco e preparaDifesa richiedono stamina > 0 per poter essere eseguite
        // La fuga può essere tentata anche con stamina =0, tuttavia causa decremento di stamina qualora fosse >0
        switch (codiceMossa) {
            case GestionePartita.MOVE_ATTACK:
                if (giocatoreCorrente.getPuntiStamina() > 0) {
                    ((IAttaccante) giocatoreCorrente).attacca(nemico);
                    mossaCompletata = true;
                }
                else
                    stampaMessaggio("Non hai abbastanza stamina per effettuare questa mossa!");
                break;
            case GestionePartita.MOVE_GUARD:
                if (giocatoreCorrente.getPuntiStamina() > 0) {
                    ((IDifesa) giocatoreCorrente).preparaDifesa();
                    mossaCompletata = true;
                }
                else
                    stampaMessaggio("Non hai abbastanza stamina per effettuare questa mossa!");
                break;
            case GestionePartita.MOVE_POWER_UP:
                if (giocatoreCorrente.getPuntiStamina() > 0) {
                    ((IAttaccante) giocatoreCorrente).caricaAttacco();
                    mossaCompletata = true;
                }
                else
                    stampaMessaggio("Non hai abbastanza stamina per effettuare questa mossa!");
                break;
            case GestionePartita.MOVE_ITEM:
                throw new NotImplementedException();        // Coming Soon (?)
            case GestionePartita.MOVE_RUN:
                //FUGA
                if (giocatoreCorrente.ritirata(nemico) == true)
                    giocatoreFuggito = indiceGiocatore;
                mossaCompletata = true;
                break;
            default:
                throw new IllegalArgumentException("Mossa inserita errata!");
        }

        if (mossaCompletata)
            giocatoreCorrente.decrementaStamina(1);
    }

    /**
     * Controlla se la partita è conclusa verificando se:
     * - i punti vita di uno dei due giocatori sono a 0
     * - i punti stamina di entrambi i giocatori esauriti
     * - uno dei due giocatori è fuggito
     * Ed inoltre determina l'eventuale vincitore della partita.
     *
     * @return se la partita è conclusa (true) o meno (false)
     */
    public boolean controlloPartitaConclusa() {
        // Punti vita dei giocatori
        if (player1.getPuntiVita() == 0)
            vincitore = 2;
        else if (player2.getPuntiVita() == 0)
            vincitore = 1;
            // Punti stamina di entrambi i giocatori =0 (in tal caso vince chi ha più PV)
        else if (player1.getPuntiStamina() == 0 && player2.getPuntiStamina() == 0) {
            if (player1.getPuntiVita() > player2.getPuntiVita())
                vincitore = 1;
            else if (player2.getPuntiVita() > player1.getPuntiVita())
                vincitore = 2;
            else
                vincitore = 0;
        }
        // Giocatore fuggito
        else if (giocatoreFuggito != -1) {
            switch (giocatoreFuggito) {
                case 1:
                    vincitore = 2;
                    break;
                case 2:
                    vincitore = 1;
                    break;
            }
        }

        // Avvisa il main se la partita è conclusa e, in caso negativo, incrementa il contatore del turno
        if (vincitore != -1)
            return true;
        else {
            contatoreTurno++;
            return false;
        }
    }

    /**
     * Determina qual è il giocatore più veloce, ovvero quello che comincerà il turno.
     * Se i due giocatori hanno uguale velocità, chi comincia viene scelto casualmente
     *
     * @return L'indice del giocatore che comincia il turno per primo (1 o 2)
     */
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
