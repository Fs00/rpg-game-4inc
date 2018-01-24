package ittbuonarroti.rpggame.engine;

import ittbuonarroti.rpggame.characters.Personaggio;

public class GestionePartita {

    private Personaggio[] giocatori;    // array contenente i giocatori
    private int[] ordineGiocatori;      // array contenente gli indici dei giocatori nell'ordine in cui attaccheranno nel turno corrente
    private int turnoAttuale;           // contatore turno attuale
    private boolean run;                // ?

    public GestionePartita(Personaggio[] giocatori) {
        this.giocatori = giocatori;
        ordineGiocatori = new int[giocatori.length];
        turnoAttuale = 1;
    }

    public static void stampaMessaggio(String messaggio) {
        System.out.println(messaggio);
        // TODO: quando ci sarà la GUI, adattare questo metodo
    }

    /**
     * Effettua le dovute preparazioni per il turno di battaglia successivo
     */
    public void turnoSuccessivo() {
        turnoAttuale++;

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
    }

    public void faiMossa(int indiceGiocatore, int codiceMossa) {
        // TODO
    }

    public int[] getOrdineGiocatori() {
        return ordineGiocatori;
    }

    public int getTurnoAttuale() {
        return turnoAttuale;
    }


    // TODO RIVEDERE
    /*public boolean fine(){
        return this.player.getPuntiVita() == 0 || this.enemy.getPuntiVita() == 0 || this.run;
    }*/
}
