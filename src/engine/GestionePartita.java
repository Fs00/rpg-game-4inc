package ittbuonarroti.rpggame.engine;

import ittbuonarroti.rpggame.characters.Combattente;
import ittbuonarroti.rpggame.characters.Personaggio;

public class GestionePartita {

    private Personaggio player1;          // --
    private Personaggio player2;          // --
    private int[] ordineGiocatori;        // array contenente gli indici dei giocatori nell'ordine in cui attaccheranno nel turno corrente
    private int contatoreTurno;           // contatore turno attuale
    private boolean finePartita = false;  // fuggito?
    private int giocatoreFuggito = -1;    // indice giocatore fuggito (-1 nessuno)

    public final static int MOVE_ATTACK = 1;
    public final static int MOVE_GUARD = 2;
    public final static int MOVE_POWER_UP = 3;
    public final static int MOVE_ITEM = 4;
    public final static int MOVE_RUN = 5;

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

    /**
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

    public void faiMossa(int indiceGiocatore, int codiceMossa) {

        Combattente temp;
        Personaggio avv;
        if (indiceGiocatore == 1) {
            temp = (Combattente) this.player1;
            avv = this.player2;
        } else {
            temp = (Combattente) this.player2;
            avv = this.player1;
        }

        switch (codiceMossa) {
            case GestionePartita.MOVE_ATTACK:
                //ATTACCO TODO
                break;
            case GestionePartita.MOVE_GUARD:
                temp.setGuard(true);
                break;
            case GestionePartita.MOVE_POWER_UP:
                temp.caricaAttacco();
                break;
            case GestionePartita.MOVE_ITEM:
                //PLACEHOLDER
                break;
            case GestionePartita.MOVE_RUN:
                //FUGA
                if (temp.ritirata(avv) == 1)
                    this.giocatoreFuggito = indiceGiocatore;
                //TODO Messaggio fuga riuscita o fallita
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

    // TODO RIVEDERE
    /*public boolean fine(){
        return  this.run;
    }*/

    public int giocatorePiuVeloce() {
        if (player1.getVelocita() > player2.getVelocita())
            return 1;
        else if (player2.getVelocita() > player1.getVelocita())
            return 2;
        else
            //TODO RNG MONETA
            return 1;
        //RNG.random(0,2);
    }
}
