package ittbuonarroti.rpggame.chara;

import java.util.concurrent.ThreadLocalRandom;

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
     * 'randomizzaStats'
     */
    public Personaggio(int puntiVitaTotali, int attacco, int difesa, int velocita, int puntiStaminaTotali, String nome, char sesso) {
        int[] statsDaRandomizzare = {puntiVitaTotali, attacco, difesa, velocita, puntiStaminaTotali};
        randomizzaStats(statsDaRandomizzare);
        this.nome = nome;
        this.sesso = sesso;
    }

    /**
     * Fa scappare un personaggio dal terreno di gioco<br>
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
     * Modifica la vita del personaggio<br>
     * Il metodo è da overridare nelle classi derivate in caso di mitigazioni particolari
     * @param valore Quantità di puntiVita da togliere (valore negativo) o da aggiungere (valore positivo)
     */
    public void modificaPuntiVita (int valore) {
        if (valore + puntiVita > puntiVitaTotali)   // Per evitare overflow di PV
            puntiVita = puntiVitaTotali;
        else if (valore > puntiVita) {             // per evitare PV negativi
            puntiVita = 0;
            // TODO: richiamare un metodo statico della classe di gestione per la morte o usare un valore di ritorno??
        }
        else
            puntiVita += valore;
    }

    /**
     * 'This' riceve il colpo subito da 'Nemico'
     * (Questo metodo è chiamato dal metodo attacca() dell'avversario):<br>
     * - Se il personaggio ne ha la possibilità, calcola la probabilità di contrattacco ed in caso fortunato esegue questa mossa<br>
     * - Altrimenti subisce il danno (mitigato dlla difesa: danno = danno-difesa)
     * @param nemico Colui che effettua l'attacco ai danni di this
     * @param danno Quantità di danno effettuato (inteso come puntiVita sottratti all'avversario)
     * @param contrattaccabile Booleano che indica se l'attacco è contrattaccabile (quindi se si tratta di un contrattacco o meno)
     */
    public void riceviColpo (Personaggio nemico, int danno, boolean contrattaccabile) {
        boolean contrattaccoRiuscito = false;
        if (this instanceof IAttaccante && contrattaccabile) {      // se quindi ha a disposizione il metodo contrattacca()
            // Genera un numero a random tra 0 e 100
            int random = ThreadLocalRandom.current().nextInt(0, 101);
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


    /** Metodi Getter **/

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

        //PV
        if (ThreadLocalRandom.current().nextInt(0, 1 + 1) == 1)
        {
            puntiVitaTotali = stats[0] + ThreadLocalRandom.current().nextInt(0, 4 + 1);
        }
        else
        {
            puntiVitaTotali = stats[0] - ThreadLocalRandom.current().nextInt(0, 4 + 1);
        }

        //ATTACCO
        if (ThreadLocalRandom.current().nextInt(0, 1 + 1) == 1)
        {
            attacco = stats[1] + ThreadLocalRandom.current().nextInt(0, 4 + 1);
        }
        else
        {
            attacco = stats[1] - ThreadLocalRandom.current().nextInt(0, 4 + 1);
        }

        //DIFESA
        if (ThreadLocalRandom.current().nextInt(0, 1 + 1) == 1)
        {
            difesa = stats[2] + ThreadLocalRandom.current().nextInt(0, 4 + 1);
        }
        else
        {
            difesa = stats[2] - ThreadLocalRandom.current().nextInt(0, 4 + 1);
        }

        //VELOCITA'
        if (ThreadLocalRandom.current().nextInt(0, 1 + 1) == 1)
        {
            velocita = stats[3] + ThreadLocalRandom.current().nextInt(0, 4 + 1);
        }
        else
        {
            velocita = stats[3] - ThreadLocalRandom.current().nextInt(0, 4 + 1);
        }

        //PS
        if (ThreadLocalRandom.current().nextInt(0, 1 + 1) == 1)
        {
            puntiStaminaTotali = stats[4] + ThreadLocalRandom.current().nextInt(0, 2 + 1);
        }
        else
        {
            puntiStaminaTotali = stats[4] - ThreadLocalRandom.current().nextInt(0, 2 + 1);
        }
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