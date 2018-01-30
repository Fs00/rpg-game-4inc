package ittbuonarroti.rpggame.engine;

import ittbuonarroti.rpggame.characters.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Classe di gestione della partita, utilizzata sia per la modalità
 * a riga di comando (CLI) che per quella grafica (GUI)
 */
public class GestionePartita {

    /**
     * Contiene il riferimento ai due personaggi attualmente in scontro
     */
    private Personaggio player1, player2;

    /**
     * Contatore del numero di mossa corrente
     */
    private int contatoreTurno;

    /**
     * Indica se la partita si è conclusa o meno:<br>
     * -1:    Non Terminata<br>
     * 0:     Pareggio<br>
     * 1 o 2: Ha vinto il giocatore di indice 1/2
     */
    private int vincitore = -1;

    /**
     * Indica l'eventuale indice del giocatore fuggito dal terreno di scontro
     * (-1 se nessuno è fuggito)
     */
    private int giocatoreFuggito = -1;

    // CODICI per le MOSSE
    public final static int MOVE_ATTACK = 1;
    public final static int MOVE_RUN = 2;
    public final static int MOVE_POWER_UP = 3;
    public final static int MOVE_GUARD = 4;
    public final static int MOVE_ITEM = 5;     // Per ora non usato


    /**
     * Metodo Costruttore<br>
     * @param giocatori Array di Personaggio già caricato in precedenza
     */
    public GestionePartita(Personaggio[] giocatori) {
        this.player1 = giocatori[0];
        this.player2 = giocatori[1];
        contatoreTurno = 1;
    }

    /**
     * Stampa un dato messaggio a video
     *
     * @param messaggio Messaggio da stampare
     */
    public static void stampaMessaggio(String messaggio) {
        System.out.println(messaggio);
        // TODO: quando ci sarà la GUI, adattare questo metodo
    }

    /**
     * Fa effettuare al giocatore passato per parametro la mossa richiesta
     *
     * @param indiceGiocatore L'indice del giocatore corrente (1 o 2)
     * @param codiceMossa     Il codice della mossa da effettuare
     */
    public void faiMossa(int indiceGiocatore, int codiceMossa) throws InterruptedException {

        Personaggio giocatoreCorrente, nemico;
        boolean mossaCompletata = false;

        if (indiceGiocatore == 1) {
            giocatoreCorrente = player1;
            nemico = player2;
        } else {
            giocatoreCorrente = player2;
            nemico = player1;
        }

        // attacco, caricaAttacco e preparaDifesa richiedono stamina > 0 per poter essere eseguite
        // La fuga può essere tentata anche con stamina = 0, tuttavia causa decremento di stamina qualora fosse > 0
        switch (codiceMossa) {
            // Attacco
            case GestionePartita.MOVE_ATTACK:
                if (giocatoreCorrente.getPuntiStamina() > 0) {
                    stampaMessaggio(giocatoreCorrente.getNome() + " attacca!");
                    ((IAttaccante) giocatoreCorrente).attacca(nemico);
                    mossaCompletata = true;
                } else
                    stampaMessaggio("Non hai abbastanza stamina per effettuare questa mossa!");
                break;

            // Attivazione dello "scudo difensivo"
            case GestionePartita.MOVE_GUARD:
                if (giocatoreCorrente.getPuntiStamina() > 0) {
                    stampaMessaggio(giocatoreCorrente.getNome() + " si sta preparando a difendersi.");
                    ((IDifesa) giocatoreCorrente).preparaDifesa();
                    mossaCompletata = true;
                } else
                    stampaMessaggio("Non hai abbastanza stamina per effettuare questa mossa!");
                break;

            // Preparazione dell'attacco caricato
            case GestionePartita.MOVE_POWER_UP:
                if (giocatoreCorrente.getPuntiStamina() > 0) {
                    stampaMessaggio(giocatoreCorrente.getNome() + " si prepara a sferrare un attacco potenziato il prossimo turno.");
                    ((IAttaccante) giocatoreCorrente).caricaAttacco();
                    mossaCompletata = true;
                } else
                    stampaMessaggio("Non hai abbastanza stamina per effettuare questa mossa!");
                break;

            // Uso oggetti: Da fare in futuro?
            case GestionePartita.MOVE_ITEM:
                throw new NotImplementedException();

            // Fuga dallo scontro
            case GestionePartita.MOVE_RUN:
                stampaMessaggio(giocatoreCorrente.getNome() + " sta cercando di scappare...");
                Thread.sleep(750);      // pausa ad effetto
                if (giocatoreCorrente.ritirata(nemico) == true) {
                    giocatoreFuggito = indiceGiocatore;
                    stampaMessaggio(giocatoreCorrente.getNome() + " è riuscito a scappare!");
                }
                else
                    stampaMessaggio("La fuga di " + giocatoreCorrente.getNome() + " non è riuscita!");

                mossaCompletata = true;
                break;

            // Tutti gli altri casi: mossa non valida
            default:
                throw new IllegalArgumentException("Mossa inserita errata!");
        }

        // Se la mossa è stata effettuata correttamente, decrementa i puntiStamina di 1
        if (mossaCompletata)
            giocatoreCorrente.decrementaStamina(1);

        // Se il Soldato aveva attivato la modalità difesa ma il suo nemico non ha attaccato, disabilita
        // questa modalità ed informa il giocatore che la sua mossa non è servita
        if (nemico instanceof Soldato && ((Soldato) nemico).getGuardStatus() == true) {
            ((Soldato) nemico).disabilitaGuard();
            stampaMessaggio(giocatoreCorrente.getNome() + " non ha attaccato in questo turno. " +
                    nemico.getNome() + " ha quindi sprecato la modalità difesa!");
        }
    }

    /**
     * Controlla se la partita è conclusa verificando se:<br>
     * - i puntiVita di uno dei due giocatori sono a 0<br>
     * - i puntiStamina di entrambi i giocatori esauriti<br>
     * - uno dei due giocatori è fuggito<br>
     * Ed inoltre determina l'eventuale vincitore della partita.
     *
     * @return true se la partita è conclusa, false altrimenti
     */
    public boolean controlloPartitaConclusa() {
        // Controlla puntiVita dei giocatori
        if (player1.getPuntiVita() == 0)
            vincitore = 2;
        else if (player2.getPuntiVita() == 0)
            vincitore = 1;
            // Controlla se puntiStamina di entrambi i giocatori sono a 0 (in tal caso vince chi ha più puntiVita)
        else if (player1.getPuntiStamina() == 0 && player2.getPuntiStamina() == 0) {
            GestionePartita.stampaMessaggio("Entrambi i giocatori non hanno più stamina! La partita termina.");
            if (player1.getPuntiVita() > player2.getPuntiVita())
                vincitore = 1;
            else if (player2.getPuntiVita() > player1.getPuntiVita())
                vincitore = 2;
            else
                vincitore = 0;
        }
        // Controlla se un giocatore è fuggito
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
            // FIXME: trovare un modo per far incrementare il contatore del turno ogni due mosse anziché una
            contatoreTurno++;
            return false;
        }
    }

    /**
     * Determina qual è il giocatore più veloce, ovvero quello che comincerà il turno.<br>
     * Se i due giocatori hanno uguale velocità, chi comincia viene scelto casualmente
     *
     * @return Indice del giocatore che comincia il turno per primo (1 o 2)
     */
    public int giocatorePiuVeloce() {
        if (player1.getVelocita() > player2.getVelocita())
            return 1;
        else if (player2.getVelocita() > player1.getVelocita())
            return 2;
        else {
            return RNG.randomNumber(1, 2);
        }
    }

    /*
     * Ottiene l'oggetto Personaggio legato al giocatore specificato
     *
     * @param indice Indice del giocatore (1 o 2)
     * @return L'oggetto del giocatore richiesto
     * @throws IllegalArgumentException se l'indice non è 1 o 2
     */
    // POTREBBE SERVIRE PER LA GUI? PER IL MOMENTO LO LASCIAMO QUA
    /*public Personaggio getPlayerCharacter (int indice) {
        if (indice == 1)
            return player1;
        else if (indice == 2)
            return player2;
        else
            throw new IllegalArgumentException("L'indice del giocatore non è valido.");
    }*/

    public int getVincitore () {
        return vincitore;
    }

    public int getContatoreTurno () {
        return contatoreTurno;
    }
}
