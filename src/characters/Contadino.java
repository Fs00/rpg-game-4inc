package ittbuonarroti.rpggame.characters;

/**
 * Rappresenta un'istanza del personaggio Contadino
 */
public class Contadino extends Personaggio implements IAttaccante {
    private boolean attaccoCaricato = false;

    /**
     * Metodo Costruttore<br>
     * Inizializza le statistiche in base ai seguenti parametri (poi randomizzati da {@link Personaggio#assegnaStatistiche}):<br>
     * - puntiVitaTotali: 100<br>
     * - attacco: 35<br>
     * - difesa: 10<br>
     * - velocita: 50<br>
     * - puntiStaminaTotali: 15<br>
     */
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