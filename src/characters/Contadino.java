package ittbuonarroti.rpggame.characters;

import ittbuonarroti.rpggame.engine.GestionePartita;

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
        // Verifica se nel turno precedente è stato usato caricaAttacco() e in caso affermativo aumenta il danno del 50%
        if (attaccoCaricato) {
            GestionePartita.stampaMessaggio("Il suo attacco viene potenziato!");
            danno *= 1.5;
            attaccoCaricato = false;
        }
        nemico.riceviColpo(this, danno, true);
    }

    /**
     * Implementazione di {@link IAttaccante#contrattacca(Personaggio, int)}
     */
    public void contrattacca(Personaggio nemico, int danno) {
        GestionePartita.stampaMessaggio(getNome() + " contrattacca!");
        nemico.riceviColpo(this, danno, false);
    }

    /**
     * Implementazione di {@link IAttaccante#caricaAttacco()}
     */
    public void caricaAttacco() {
        attaccoCaricato = true;
    }

    /**
     * Metodo statico che restituisce una descrizione del tipo di personaggio
     *
     * @return La stringa da stampare a video
     */
    public static String getDescrizione() {
        return "Capo di una tradizionale famiglia contadina, caratterizzato da un grande spirito di sopravvivenza. " +
                "L'assenza di armature lo rende piuttosto agile, anche se poi è tutt'altro che un gran combattente. " +
                "Però, a dirla tutta, il fatto di essere sottovalutati da chi la guerra la fa di mestiere può dare qualche vantaggio...";
    }
}