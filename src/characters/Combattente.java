package ittbuonarroti.rpggame.characters;

import ittbuonarroti.rpggame.engine.GestionePartita;

/**
 * Classe astratta da cui derivano {@link Soldato} e {@link Mercenario}.
 */
public abstract class Combattente extends Personaggio implements IAttaccante {

    /**
     * Booleano che indica se il Combattente intente sferrare
     * un attacco con potenza raddoppiata rispetto al normale
     */
    private boolean attaccoCaricato = false;

    /**
     * Metodo Costruttore<br>
     * Si limita a chiamare il costruttore di Personaggio, dato che non vi è nessuna operazione speciale da eseguire
     */
    public Combattente(int puntiVitaTotali, int attacco, int difesa, int velocita, int puntiStaminaTotali, String nome, char sesso) {
        super(puntiVitaTotali, attacco, difesa, velocita, puntiStaminaTotali, nome, sesso);
    }

    /**
     * Implementazione di {@link IAttaccante#attacca(Personaggio)}
     */
    public void attacca(Personaggio nemico) {
        int danno = getAttacco();
        // Verifica se nel turno precedente è stato usato caricaAttacco() e in caso affermativo aumenta il danno del 50%
        if (attaccoCaricato) {
            GestionePartita.stampaMessaggio("Il suo attacco viene potenziato!");
            danno *= 1.5;
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
        GestionePartita.stampaMessaggio(getNome() + " contrattacca!");
        nemico.riceviColpo(this, danno, false);
    }
}
