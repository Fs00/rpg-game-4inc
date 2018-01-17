package ittbuonarroti.rpggame;

import java.math.*;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Personaggio
{
    protected int puntiVitaTotali;
    protected int puntiStaminaTotali;

    protected int puntiVita;
    protected int puntiStamina;
    protected int velocita;
    protected int attacco;
    protected int difesa;
    protected String nome;
    protected char sesso;

    protected int stats;

    public Personaggio (int puntiVitaTotali, int puntiStaminaTotali, int velocita, String nome, char sesso) {
        int[] miniarray = {puntiVitaTotali, puntiStaminaTotali, velocita};
        this.randomStats(miniarray);

        this.nome = nome;
        this.sesso = sesso;
    }

    public void setPuntiVita (int puntiVita) {
        this.puntiVita = puntiVita;
    }

    public void setPuntiStamina (int puntiStamina) {
        this.puntiStamina = puntiStamina;
    }

    public int getPuntiVitaTotali () {
        return puntiVitaTotali;
    }

    public int getPuntiStaminaTotali () {
        return puntiStaminaTotali;
    }

    public int getPuntiVita () {
        return puntiVita;
    }

    public int getPuntiStamina () {
        return puntiStamina;
    }

    public int getVelocita () {
        return velocita;
    }

    public int getAttacco() {
        return attacco;
    }

    public void setAttacco(int attacco) {
        this.attacco = attacco;
    }

    public int getDifesa() {
        return difesa;
    }

    public void setDifesa(int difesa) {
        this.difesa = difesa;
    }

    public String getNome () {
        return nome;
    }

    public char getSesso () {
        return sesso;
    }

    protected void randomStats(int[] stats){
        // 1 = +; 0 = -
        //TODO FINIRE CON TUTTE LE STATISTICHE
        if(ThreadLocalRandom.current().nextInt(0, 1 + 1) == 1){
            this.puntiVitaTotali = stats[0] + ThreadLocalRandom.current().nextInt(0, 4 + 1);
        }else{
            this.puntiVitaTotali = stats[0] - ThreadLocalRandom.current().nextInt(0, 4 + 1);
        }
    }
}