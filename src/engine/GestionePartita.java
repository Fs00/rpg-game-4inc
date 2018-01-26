package ittbuonarroti.rpggame.engine;

import ittbuonarroti.rpggame.characters.IAttaccante;
import ittbuonarroti.rpggame.characters.IDifesa;
import ittbuonarroti.rpggame.characters.Personaggio;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GestionePartita {

    private Personaggio player1, player2;   // giocatori
    //private int[] ordineGiocatori;        // array contenente gli indici dei giocatori nell'ordine in cui attaccheranno nel turno corrente
    private int contatoreTurno;             // contatore turno attuale
    private int vincitore = -1;           // indica se la partita è conclusa o meno (-1 non terminata, 0 pareggio, il resto indici giocatori)
    private int giocatoreFuggito = -1;      // indice giocatore fuggito (-1 nessuno)

    // CODICI MOSSE
    // Nota: le ho riordinate per facilitare i controlli in caso di input errato nel main (@Fs00)
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
            giocatoreCorrente = this.player1;
            nemico = this.player2;
        }
        else {
            giocatoreCorrente = this.player2;
            nemico = this.player1;
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
                throw new NotImplementedException(); //Coming Soon (TM)
            case GestionePartita.MOVE_RUN:
                //FUGA
                if (giocatoreCorrente.ritirata(nemico) == true)
                    this.giocatoreFuggito = indiceGiocatore;
                //TODO Messaggio fuga riuscita o fallita (dovrebbe essere stampato direttamente dalla classe Personaggio)
                break;
            default:
                stampaMessaggio("");
                break;
        }
    }

    public int getContatoreTurno() {
        return contatoreTurno;
    }

    /**
     * Controlla se la partita è conclusa controllando certi paramteri:
     * - PV G1 sono a 0
     * - PV G2 sono a 0
     * - PS di entrambi esauriti
     * - Giocatore fuggito
     *
     * @return se la partita è conclusa (true) o meno (false)
     */
    public boolean controlloPartitaConclusa() {
        //P1 0 HP
        if (player1.getPuntiVita() == 0)
            vincitore = 2;
            //P2 0 HP
        else if (player2.getPuntiVita() == 0)
            vincitore = 1;
            //Zero SP
        else if (player1.getPuntiStamina() == 0 && player2.getPuntiStamina() == 0)
            if (player1.getPuntiVita() > player2.getPuntiVita())
                vincitore = 1;
            else if (player2.getPuntiVita() > player1.getPuntiVita())
                vincitore = 2;
            else
                vincitore = 0;
            //Fuga
        else if (giocatoreFuggito != -1)
            switch (giocatoreFuggito) {
                case 1:
                    vincitore = 2;
                    break;
                case 2:
                    vincitore = 1;
                    break;
            }
        //Fine
        return vincitore != -1;
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
