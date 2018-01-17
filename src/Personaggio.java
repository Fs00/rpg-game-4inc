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


    public Personaggio (int puntiVitaTotali, int puntiStaminaTotali, int velocita, String nome, char sesso) {
        this.puntiVitaTotali = puntiVitaTotali;
        this.puntiStaminaTotali = puntiStaminaTotali;
        puntiVita = puntiVitaTotali;
        puntiStamina = puntiStaminaTotali;
        this.velocita = velocita;
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

    public String getNome () {
        return nome;
    }

    public char getSesso () {
        return sesso;
    }
}