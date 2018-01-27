package ittbuonarroti.rpggame.characters;

/**
 * Interfaccia che devono implementare tutti i personaggi in gradi di mitigare i colpi
 */
public interface IDifesa {

    /**
     * Il personaggio perde un turno per poi subire danni dimezzati nel turno successivo
     */
    void preparaDifesa();
}
