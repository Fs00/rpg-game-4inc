package ittbuonarroti.rpggame.characters;

/**
 * Rappresenta un'istanza del personaggio Contadino.
 */
public class Contadino extends Personaggio implements IAttaccante {
    private boolean attaccoCaricato = false;
    private boolean isDef = false;

    public Contadino(String nome, char sesso) {
        super(100, 35, 10, 50, 15, nome, sesso);
    }

    /**
     * Implementazione di {@link IAttaccante#attacca(Personaggio)}
     */
    public void attacca(Personaggio nemico) {
        int danno = getAttacco();
        // Verifica se nel turno precedente è stato usato caricaAttacco() e in caso affermativo raddoppia il danno
        if (attaccoCaricato) {
            danno *= 2;
            attaccoCaricato = false;
        }
        nemico.riceviColpo(this, danno, true);
    }

    /**
     * Implementazione di {@link IAttaccante#contrattacca(Personaggio, int)}
     */
    public void contrattacca(Personaggio nemico, int danno) {
        nemico.riceviColpo(this, danno, false);
    }

    /**
     * Implementazione di {@link IAttaccante#caricaAttacco()}
     */
    public void caricaAttacco() {
        attaccoCaricato = true;
    }
}