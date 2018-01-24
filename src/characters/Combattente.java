package ittbuonarroti.rpggame.characters;

/**
 * Classe astratta da cui derivano {@link Soldato} e {@link Mercenario}.
 */
public abstract class Combattente extends Personaggio implements IAttaccante {
    private boolean attaccoCaricato = false;

    public Combattente(int puntiVitaTotali, int attacco, int difesa, int velocita, int puntiStaminaTotali, String nome, char sesso) {
        super(puntiVitaTotali, attacco, difesa, velocita, puntiStaminaTotali, nome, sesso);
    }

    /**
     * Implementazione di {@link IAttaccante#attacca(Personaggio)}
     */
    public void attacca(Personaggio nemico) {
        //return -((this.getAttacco() - nemico.getDifesa()) * moltAttacco);
        int danno = getAttacco();
        // Verifica se nel turno precedente è stato usato caricaAttacco() e in caso affermativo raddoppia il danno
        if (attaccoCaricato) {
            danno *= 2;
            attaccoCaricato = false;
        }
        nemico.riceviColpo(this, danno, true);
    }

    /**
     * Implementazione di {@link IAttaccante#caricaAttacco()}}
     */
    public void caricaAttacco() {
        this.attaccoCaricato = true;
    }

    /**
     * Implementazione di {@link IAttaccante#contrattacca(Personaggio, int)}
     */
    public void contrattacca(Personaggio nemico, int danno) {
        nemico.riceviColpo(this, danno, false);
    }
}
