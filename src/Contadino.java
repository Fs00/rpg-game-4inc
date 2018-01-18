package ittbuonarroti.rpggame;

public class Contadino extends Personaggio implements IAttaccante
{
    private boolean attaccoCaricato = true;

    public Contadino(String nome, char sesso)
    {
        super(100, 35, 10, 50, 15, nome, sesso);
    }

    /**
     * Effettua l'attacco ai danni del nemico
     * @param nemico Bersaglio dell'attacco
     */
    public void attacca(Personaggio nemico)
    {
        int danno = getAttacco();
        if (attaccoCaricato)        // moltiplicatore dell'attacco caricato
            danno *= 2;
        nemico.riceviColpo(this, danno, true);
        attaccoCaricato = false;
    }

    public void contrattacca(Personaggio nemico, int danno) {
        nemico.riceviColpo(this, danno, false);
    }

    public void preparaAttacco() {
        attaccoCaricato = true;
    }
}