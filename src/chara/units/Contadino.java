package ittbuonarroti.rpggame.chara.units;

import ittbuonarroti.rpggame.chara.IAttaccante;
import ittbuonarroti.rpggame.chara.Personaggio;

public class Contadino extends Personaggio implements IAttaccante {
    private boolean attaccoCaricato = false;

    public Contadino (String nome, char sesso) {
        super(100, 35, 10, 50, 15, nome, sesso);
    }

    /**
     * Effettua l'attacco ai danni del nemico
     * @param nemico Bersaglio dell'attacco
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
     * Effettua il contrattacco ai danni del nemico
     *
     * @param nemico Bersaglio del contrattacco
     * @param danno  Quantità di PV da togliere (deve coincidere con il danno dell'attacco originario)
     */
    public void contrattacca(Personaggio nemico, int danno) {
        nemico.riceviColpo(this, danno, false);
    }

    /**
     * Il personaggio perde un turno per sferrare un attacco di potenza raddoppiata nel turno successivo
     */
    public void preparaAttacco() {
        attaccoCaricato = true;
    }
}