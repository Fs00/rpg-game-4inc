package ittbuonarroti.rpggame.chara;

import ittbuonarroti.rpggame.chara.Personaggio;

public interface IAttaccante {
    void preparaAttacco();

    void attacca(Personaggio p);

    void contrattacca(Personaggio p, int danno);
}
