package ittbuonarroti.rpggame;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Personaggio
{
    private int puntiVitaTotali;
    private int puntiStaminaTotali;

    private int puntiVita;
    private int puntiStamina;
    private int velocita;
    private int attacco;
    private int difesa;

    private String nome;
    private char sesso;

    public Personaggio(int puntiVitaTotali, int attacco, int difesa, int velocita, int puntiStaminaTotali, String nome, char sesso) {
        int[] statsDaRandomizzare = {puntiVitaTotali, attacco, difesa, velocita, puntiStaminaTotali};
        randomizzaStats(statsDaRandomizzare);
        this.nome = nome;
        this.sesso = sesso;
    }

    /**
     * Fa scappare un personaggio dal terreno di gioco.
     * NB (TODO): Bisognerà prevedere una funzione nella classe di gestione per finire il duello (caso 1) o proseguirlo (caso 0)
     * @param avv Personaggio avversario da cui si sta cercando di scappare
     * @return 1 se il personaggio riesce a scappare; 0 altrimenti
     */
    public int ritirata(Personaggio avv) {
        if (velocita > avv.getVelocita()) {
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * Modifica la vita del personaggio. Da overridare nelle classi derivate in caso di mitigazioni particolari
     * @param valore Quantità di puntiVita da togliere (valore negativo) o da aggiungere (valore positivo)
     */
    public void modificaPuntiVita(int valore)
    {
        if (valore + puntiVita > puntiVitaTotali)   // Per evitare overflow di PV
            puntiVita = puntiVitaTotali;
        else if (valore > puntiVita)        // per evitare PV negativi
        {
            puntiVita = 0;
            // TODO: richiamare un metodo statico della classe di gestione per la morte o usare un valore di ritorno??
        }
        else
            puntiVita += valore;
    }

    /**
     * Metodo chiamato dal metodo attacca() dell'avversario, che calcola la probabilità di contrattacco per chi ne ha
     * la possibilità e mitiga i danni con la statistica difesa
     * @param nemico Colui che effettua l'attacco ai danni di this
     * @param danno Il danno effettuato
     * @param contrattaccabile Valore booleano che indica se l'attacco è contrattaccabile (quindi se si tratta di un contrattacco)
     */
    public void riceviColpo(IAttaccante nemico, int danno, boolean contrattaccabile)
    {
        boolean contrattaccoRiuscito = false;
        if (this instanceof IAttaccante && contrattaccabile) {      // se quindi ha a disposizione il metodo contrattacca()
            // TODO: possibilità di contrattacco
        }

        if (!contrattaccoRiuscito)      // se non è riuscito a contrattaccare, subisce il danno
        {
            // Mitigazione del danno attraverso la statistica difesa
            danno -= difesa;
            if (danno <= 0)     // il danno deve essere sempre almeno di 1
                danno = 1;

            this.modificaPuntiVita(Math.negateExact(danno));
        }
    }

    public void setPuntiStamina (int puntiStamina) {
        this.puntiStamina = puntiStamina;
    }

    public int getPuntiVitaTotali () {
        return puntiVitaTotali;
    }

    public int getPuntiStaminaTotali () {
        return puntiStaminaTotali;
    }

    public int getPuntiVita () {
        return puntiVita;
    }

    public int getPuntiStamina () {
        return puntiStamina;
    }

    public int getVelocita () {
        return velocita;
    }

    public int getAttacco() {
        return attacco;
    }

    public void setAttacco(int attacco) {
        this.attacco = attacco;
    }

    public int getDifesa() {
        return difesa;
    }

    public void setDifesa(int difesa) {
        this.difesa = difesa;
    }

    public String getNome () {
        return nome;
    }

    public char getSesso () {
        return sesso;
    }

    private void randomizzaStats(int[] stats) {
        // 1 = +; 0 = -
        //TODO FINIRE CON TUTTE LE STATISTICHE
        if (ThreadLocalRandom.current().nextInt(0, 1 + 1) == 1)
        {
            puntiVitaTotali = stats[0] + ThreadLocalRandom.current().nextInt(0, 4 + 1);
        }
        else
        {
            puntiVitaTotali = stats[0] - ThreadLocalRandom.current().nextInt(0, 4 + 1);
        }
    }

    @Override
    public String toString() {
        return "Personaggio: " + this.getClass().getSimpleName() +
                "\n Nome: " + nome +
                "\n Sesso: " + sesso +
                "\n Punti Vita: " + puntiVita + " (max " + puntiVitaTotali + ")" +
                "\n Stamina: " + puntiStamina + " (max " + puntiStaminaTotali + ")" +
                "\n Velocità: " + velocita;
    }
}