package ittbuonarroti.rpggame;

public class Contadino extends Personaggio implements IAttaccante
{
    public Contadino(String nome, char sesso)
    {
        super(100, 35, 10, 50, 15, nome, sesso);
    }

    /**
     * Effettua l'attacco ai danni del nemico
     * @param nemico Bersaglio dell'attacco
     */
    public int attacca(Personaggio nemico)
    {
        nemico.riceviColpo(this, getAttacco());
        return 0;   // placeholder in attesa di definire meglio l'implementazione
    }
}