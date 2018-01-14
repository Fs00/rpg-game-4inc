package ittbuonarroti.rpggame;

public class Personaggio
{
    private final int puntiVitaTotali;
    private final int puntiStaminaTotali;

    private int puntiVita;
    private int puntiStamina;

    public Personaggio(int puntiVitaTot, int puntiStaminaTot)
    {
        puntiVitaTotali = puntiVitaTot;
        puntiStaminaTotali = puntiStaminaTot;
    }
}