package ittbuonarroti.rpggame.characters;

/**
 * Interfaccia che devono implementare tutti i personaggi che possono attaccare
 */
public interface IAttaccante {
    /**
     * Il personaggio perde un turno per sferrare un attacco di potenza raddoppiata nel turno successivo
     */
    void preparaAttacco();

    /**
     * Effettua l'attacco ai danni del nemico
     *
     * @param nemico Bersaglio dell'attacco
     */
    void attacca(Personaggio nemico);

    /**
     * Effettua il contrattacco ai danni del nemico
     *
     * @param nemico Bersaglio del contrattacco
     * @param danno  Quantità di PV da togliere (deve coincidere con il danno dell'attacco originario)
     */
    void contrattacca(Personaggio nemico, int danno);
}
