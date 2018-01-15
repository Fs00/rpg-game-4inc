package ittbuonarroti.rpggame;

public abstract class Personaggio
{
    private final int puntiVitaTotali;
    private final int puntiStaminaTotali;

    private int puntiVita;
    private int puntiStamina;
    private int velocita;
    private String nome;
    private char sesso;


    public Personaggio (int puntiVitaTotali, int puntiStaminaTotali, int puntiVita, int puntiStamina, int velocita, String nome, char sesso) {
        this.puntiVitaTotali = puntiVitaTotali;
        this.puntiStaminaTotali = puntiStaminaTotali;
        this.puntiVita = puntiVita;
        this.puntiStamina = puntiStamina;
        this.velocita = velocita;
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

    public String getNome () {
        return nome;
    }

    public char getSesso () {
        return sesso;
    }
}