package ittbuonarroti.rpggame.engine;

import ittbuonarroti.rpggame.characters.Personaggio;

public class GestionePartita {

    private Personaggio player, enemy;
    private int turno, turno_cont; //indice turno e contatore turni
    private boolean run;

    public GestionePartita(Personaggio inPlayer, Personaggio inEnemy) {
        this.player = inPlayer;
        this.enemy = inEnemy;
        this.turno_cont = 0;
        this.run = false;

        if(inPlayer.getVelocita() > inEnemy.getVelocita())
            turno = 1; //TURNO GIOCATORE
        else
            turno = 2;
    }

    public GestionePartita(Personaggio[] inPlayers, Personaggio[] inEnemies) {
        //TODO LOTTA MULTIPLA
    }



    public int getTurno_cont() {
        return turno_cont;
    }

    public int getTurno() {
        return turno;
    }

    public boolean fine(){
        return this.player.getPuntiVita() == 0 || this.enemy.getPuntiVita() == 0 || this.run;
    }
}
