package ittbuonarroti.rpggame.chara.units;

import ittbuonarroti.rpggame.chara.Combattente;

public class Mercenario extends Combattente {

    public Mercenario() {
        super(200, 60, 25, 30, 22, "Mercenario", 'F');
    }

    public Mercenario(String nome, char sesso) {
        super(200, 60, 25, 30, 22, nome, sesso);
    }


}
