package ittbuonarroti.rpggame.characters;

/**
 * Rappresenta un'istanza del personaggio Contadino.
 */
public class Contadino extends Personaggio implements IAttaccante {
    private boolean attaccoCaricato = false;

    public Contadino (String nome, char sesso) {
        super(100, 35, 10, 50, 15, nome, sesso);
    }

    /**
     * Implementazione di {@link IAttaccante#attacca(Personaggio)}
     */
    public void attacca (Personaggio nemico) {
        int danno = getAttacco();
        // Verifica se nel turno precedente è stato usato preparaAttacco() e in caso affermativo raddoppia il danno
        if (attaccoCaricato)
            danno *= 2;
        nemico.riceviColpo(this, danno, true);
        attaccoCaricato = false;
    }

    /**
     * Implementazione di {@link IAttaccante#contrattacca(Personaggio, int)}
     */
    public void contrattacca(Personaggio nemico, int danno) {
        nemico.riceviColpo(this, danno, false);
    }

    /**
     * Implementazione di {@link IAttaccante#preparaAttacco()}
     */
    public void preparaAttacco() {
        attaccoCaricato = true;
    }
}