package ittbuonarroti.rpggame;

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

    /**
     * Fa scappare un personaggio dal terreno di gioco.
     * NB (TODO): Bisognerà prevedere una funzione nella classe di gestione per finire il duello (caso 1) o proseguirlo (caso 0)
     * @param avv Personaggio avversario da cui si sta cercando di scappare
     * @return 1 se il personaggio riesce a scappare; 0 altrimenti
     */
    public int ritirata(Personaggio avv) {
        if (velocita > avv.getVelocita()) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public void subisciColpo (int danno) {
        puntiVita -= danno;
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

    protected void randomStats(int[] stats) {
        // 1 = +; 0 = -
        //TODO FINIRE CON TUTTE LE STATISTICHE
        if (ThreadLocalRandom.current().nextInt(0, 1 + 1) == 1)
        {
            this.puntiVitaTotali = stats[0] + ThreadLocalRandom.current().nextInt(0, 4 + 1);
        } else
        {
            this.puntiVitaTotali = stats[0] - ThreadLocalRandom.current().nextInt(0, 4 + 1);
        }
    }

    @Override
    public String toString() {
        return "Personaggio: " + this.getClass().getSimpleName() +
                "\n Nome: " + nome +
                "\n Sesso: " + sesso +
                "\n Punti Vita: " + puntiVita + " (max " + puntiVitaTotali + ")" +
                "\n Stamina: " + puntiStamina + " (max " + puntiStaminaTotali + ")" +
                "\n Velocità: " + velocita;
    }
}