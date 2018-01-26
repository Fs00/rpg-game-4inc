package ittbuonarroti.rpggame.engine;

import ittbuonarroti.rpggame.characters.IAttaccante;
import ittbuonarroti.rpggame.characters.IDifesa;
import ittbuonarroti.rpggame.characters.Personaggio;
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
            giocatoreCorrente = player1;
            nemico = player2;
        }
        else {
            giocatoreCorrente = player2;
            nemico = player1;
        }

        switch (codiceMossa) {
            case GestionePartita.MOVE_ATTACK:
                ((IAttaccante) giocatoreCorrente).attacca(nemico);
                break;
            case GestionePartita.MOVE_GUARD:
                ((IDifesa) giocatoreCorrente).preparaDifesa();
                break;
            case GestionePartita.MOVE_POWER_UP:
                ((IAttaccante) giocatoreCorrente).caricaAttacco();
                break;
            case GestionePartita.MOVE_ITEM:
                throw new NotImplementedException();        // Coming Soon (?)
            case GestionePartita.MOVE_RUN:
                //FUGA
                if (giocatoreCorrente.ritirata(nemico) == true)
                    this.giocatoreFuggito = indiceGiocatore;
                break;
            default:
                throw new IllegalArgumentException("Mossa inserita errata!");
        }
    }

    public int getContatoreTurno() {
        return contatoreTurno;
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
        // Ritorna al main se la partita è conclusa
        return vincitore != -1;
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
