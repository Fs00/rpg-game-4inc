package ittbuonarroti.rpggame.characters;

public interface IAttaccante {
    void preparaAttacco();

    void attacca(Personaggio p);

    void contrattacca(Personaggio p, int danno);
}
