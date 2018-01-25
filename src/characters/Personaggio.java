package ittbuonarroti.rpggame.characters;

import ittbuonarroti.rpggame.engine.RNG;

/**
 * Classe astratta che rappresenta un Personaggio generico.
 * Contiene tutte le statistiche e i metodi di base.
 */
public abstract class Personaggio {
    private int puntiVitaTotali;
    private int puntiStaminaTotali;

    private int puntiVita;
    private int puntiStamina;
    private int velocita;
    private int attacco;
    private int difesa;

    private String nome;
    private char sesso;

    /**
     * Metodo Costruttore<br>
     * I parametri puntiVitaTotali; attacco; difesa; velocita; puntiStaminaTotali sono randomizzati dal metodo
     * {@link Personaggio#assegnaStatistiche(int[])}
     */
    public Personaggio(int puntiVitaTotali, int attacco, int difesa, int velocita, int puntiStaminaTotali, String nome, char sesso) {
        int[] statsDaRandomizzare = {puntiVitaTotali, attacco, difesa, velocita, puntiStaminaTotali};
        assegnaStatistiche(statsDaRandomizzare);
        this.nome = nome;
        this.sesso = sesso;
    }

    /**
     * Fa scappare un personaggio dal terreno di gioco:<br>
     * - Calcola la probabilità di riuscire a scappare (50 + (VEL personaggio che si vuole ritirare - VEL nemico))
     * - Genera un numero casuale tra 1 e 100
     * Se il numero generato è minore della probabilità, il personaggio riesce a scappare, altrimenti no
     * @param avv Personaggio avversario da cui si sta cercando di scappare
     * @return 1 se il personaggio riesce a scappare; 0 altrimenti
     */
    public boolean ritirata(Personaggio avv) {
        if (50 + (50 - avv.getVelocita()) < RNG.randomNumber(0, 100))
            return true;
        else
            return false;
    }

    /**
     * Modifica la vita del personaggio<br>
     * Se il numero generato è minore della probabilità, il personaggio riesce a scappare, altrimenti no.
     * Il metodo è da overridare nelle classi derivate in caso di mitigazioni particolari
     * @param valore Quantità di puntiVita da togliere (valore negativo) o da aggiungere (valore positivo)
     */
    public void modificaPuntiVita (int valore) {
        if (valore + puntiVita > puntiVitaTotali)   // Per evitare overflow di PV
            puntiVita = puntiVitaTotali;
        else if (valore > puntiVita)                // per evitare PV negativi
            puntiVita = 0;
        else
            puntiVita += valore;
    }

    /**
     * 'This' riceve il colpo subito da 'Nemico'
     * (Questo metodo è chiamato dal metodo attacca() dell'avversario):<br>
     * - Se il personaggio ne ha la possibilità, calcola la probabilità di contrattacco ed in caso fortunato esegue questa mossa<br>
     * - Altrimenti subisce il danno (mitigato dalla difesa: danno = danno-difesa)
     * @param nemico Colui che effettua l'attacco ai danni di this
     * @param danno Quantità di danno effettuato (inteso come puntiVita sottratti all'avversario)
     * @param contrattaccabile Booleano che indica se l'attacco è contrattaccabile (quindi se si tratta di un contrattacco o meno)
     */
    public void riceviColpo (Personaggio nemico, int danno, boolean contrattaccabile) {
        boolean contrattaccoRiuscito = false;
        if (this instanceof IAttaccante && contrattaccabile) {      // se quindi ha a disposizione il metodo contrattacca()
            // Genera un numero a randomNumber tra 0 e 100
            int random = RNG.randomNumber(0, 100);
            // Se il numero generato è compreso tra 0 e la velocità del personaggio divisa per 1.5, allora contrattacca
            if (random >= 0 && random <= velocita / 1.5) {
                ((IAttaccante) this).contrattacca(nemico, danno);
                contrattaccoRiuscito = true;
            }
        }

        if (!contrattaccoRiuscito) {      // se non è riuscito a contrattaccare, subisce il danno
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


    /* Metodi Getter */

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

    /**
     * Metodo che randomizza e poi assegna le statistiche del Personaggio in un range di ±5 per tutte
     * le stats eccetto per la stamina, che varia in un range di ±2
     *
     * @param stats Array contenente nel seguente ordine le statistiche di base del personaggio:
     *              puntiVitaTotali, attacco, difesa, velocita e puntiStaminaTotali
     */
    private void assegnaStatistiche(int[] stats) {
        // 1 = +; 0 = -

        //PV
        if (RNG.lanciaMoneta() == true)
            puntiVitaTotali = stats[0] + RNG.randomNumber(0, 5);
        else
            puntiVitaTotali = stats[0] - RNG.randomNumber(0, 5);

        //ATTACCO
        if (RNG.lanciaMoneta() == true)
            attacco = stats[1] + RNG.randomNumber(0, 5);
        else
            attacco = stats[1] - RNG.randomNumber(0, 5);

        //DIFESA
        if (RNG.lanciaMoneta() == true)
            difesa = stats[2] + RNG.randomNumber(0, 5);
        else
            difesa = stats[2] - RNG.randomNumber(0, 5);

        //VELOCITA'
        if (RNG.lanciaMoneta() == true)
            velocita = stats[3] + RNG.randomNumber(0, 5);
        else
            velocita = stats[3] - RNG.randomNumber(0, 5);

        //PS
        if (RNG.lanciaMoneta() == true)
            puntiStaminaTotali = stats[4] + RNG.randomNumber(0, 2);
        else
            puntiStaminaTotali = stats[4] - RNG.randomNumber(0, 2);

        puntiStamina = puntiStaminaTotali;
        puntiVita = puntiVitaTotali;
    }

    /**
     * Metodo toString()
     * @return Stato degli attributi della classe e tipo di personaggio (Debole, Contadino, Mercenario, Soldato)
     */
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