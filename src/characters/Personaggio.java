package ittbuonarroti.rpggame.characters;

import ittbuonarroti.rpggame.engine.GestionePartita;
import ittbuonarroti.rpggame.engine.RNG;

/**
 * Classe astratta che rappresenta un Personaggio generico.
 * Contiene tutte le statistiche e i metodi di base.
 */
public abstract class Personaggio {

    /**
     * Valore costante massimo che può raggiungere l'attributo puntiVita
     */
    private int puntiVitaTotali;

    /**
     * Valore costante massimo che può raggiungere l'attributo puntiStamina
     */
    private int puntiStaminaTotali;

    /**
     * Rappresenta la vita del Personaggio. Se il valore è minore o uguale a 0,
     * significa che il Personaggio è morto.
     */
    private int puntiVita;

    /**
     * Rappresenta la resistenza del Personaggio.<br>
     * Per ogni mossa eseguita, questo valore viene decrementato di una unità.
     * Se arriva a 0, il Personaggio non può più fare nulla.
     */
    private int puntiStamina;

    /**
     * Rappresenta la velocità del Personaggio.
     * Questo attributo serve a calcolare la probabilità di ritirata e di contrattacco
     */
    private int velocita;

    /**
     * Rappresenta la potenza in attacco, cioè la quantità di puntiVita
     * da sottrarre all'avversario
     */
    private int attacco;

    /**
     * Rappresenta la resistenza ad un colpo nemico.<br>
     * Al ricevimento di un colpo nemico, questo valore viene sottratto dal valore di attacco
     */
    private int difesa;

    /**
     * Stringa contenente il nome del Personaggio
     */
    private String nome;

    /**
     * Carattere maiuscolo contenente il sesso (M/F) del Personaggio
     */
    private char sesso;

    /**
     * Metodo Costruttore<br>
     * I parametri puntiVitaTotali; attacco; difesa; velocita; puntiStaminaTotali sono randomizzati dal metodo privato
     * assegnaStatistiche(int[])
     */
    public Personaggio(int puntiVitaTotali, int attacco, int difesa, int velocita, int puntiStaminaTotali, String nome, char sesso) {
        int[] statsDaRandomizzare = {puntiVitaTotali, attacco, difesa, velocita, puntiStaminaTotali};
        assegnaStatistiche(statsDaRandomizzare);
        this.nome = nome;
        this.sesso = sesso;
    }

    /**
     * Fa scappare un personaggio dal terreno di gioco:<br>
     * - Calcola la probabilità di riuscire a scappare (50 + (VEL personaggio che si vuole ritirare - VEL nemico))<br>
     * - Genera un numero casuale tra 1 e 100<br>
     * Se il numero generato è minore della probabilità, il personaggio riesce a scappare, altrimenti no
     *
     * @param avv Personaggio avversario da cui si sta cercando di scappare
     * @return true se il personaggio riesce a scappare; false altrimenti
     */
    public boolean ritirata(Personaggio avv) {
        if (50 + (getVelocita() - avv.getVelocita()) < RNG.randomNumber(0, 100))
            return true;
        else
            return false;
    }

    /**
     * Modifica i punti vita del personaggio<br>
     * Il metodo è da overridare nelle classi derivate in caso di mitigazioni particolari
     *
     * @param valore Quantità di puntiVita da togliere (valore negativo) o da aggiungere (valore positivo)
     */
    public void modificaPuntiVita (int valore) {
        if (valore + puntiVita > puntiVitaTotali)               // Evita di andare oltre puntiVitaTotali
            puntiVita = puntiVitaTotali;
        else if (valore < 0 && Math.abs(valore) > puntiVita)    // Evita di avere puntiVita negativi
            puntiVita = 0;
        else
            puntiVita += valore;

        // Messaggio
        if (valore < 0) {
            GestionePartita.stampaMessaggio(nome + " subisce " + Math.abs(valore) + " punti di danno!");
            if (puntiVita == 0)
                GestionePartita.stampaMessaggio(nome + " è morto!");
        }
        else
            GestionePartita.stampaMessaggio(nome + " recupera " + valore + " PV!");
    }

    /**
     * 'This' riceve il colpo subito da 'Nemico'<br>
     * - Se il personaggio ne ha la possibilità, calcola la probabilità di contrattacco ed in caso fortunato esegue questa mossa<br>
     * - Altrimenti subisce il danno mitigato dalla difesa: danno = danno-difesa<br>
     * Questo metodo è chiamato dal metodo {@link IAttaccante#attacca} dell'avversario
     *
     * @param nemico Colui che effettua l'attacco ai danni di this
     * @param danno Quantità di danno effettuato (inteso come puntiVita sottratti all'avversario)
     * @param contrattaccabile Booleano che indica se si tratta di un contrattacco (true) o meno (false)
     */
    public void riceviColpo (Personaggio nemico, int danno, boolean contrattaccabile) {
        boolean contrattaccoRiuscito = false;
        if (this instanceof IAttaccante && contrattaccabile) {      // Se ha a disposizione il metodo contrattacca()
            // Genera un numero casuale tra 0 e 100
            int random = RNG.randomNumber(0, 100);
            // Se il numero generato è compreso tra 0 e la velocità del personaggio divisa per 1.5, allora contrattacca
            if (random >= 0 && random <= velocita / 1.5) {
                ((IAttaccante) this).contrattacca(nemico, danno);
                contrattaccoRiuscito = true;
            }
        }

        if (!contrattaccoRiuscito) {      // Se non è riuscito a contrattaccare, subisce il danno (mitigato dal valore di difesa)
            danno -= difesa;
            if (danno <= 0)     // il danno deve essere sempre almeno di 1
                danno = 1;

            this.modificaPuntiVita(Math.negateExact(danno));    // Sottrae il danno dai puntiVita
        }
    }

    /**
     * Decrementa il valore di puntiStamina del Personaggio
     *
     * @param quantita Valore positivo (tipicamente 1) da sottrarre a puntiStamina
     */
    public void decrementaStamina(int quantita) {
        if (puntiStamina >= quantita)       // Evita di avere puntiStamina negativi
            puntiStamina -= quantita;
    }


    /* Metodi Getter/Setter */

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
     * Randomizza e poi assegna le statistiche del Personaggio in un range di ±5
     *  eccetto per la stamina, che varia in un range di ±2
     * Questo metodo è chiamato solo una volta dal costruttore
     *
     * @param stats Array contenente nel seguente ordine le statistiche di base del personaggio:
     *              puntiVitaTotali, attacco, difesa, velocita e puntiStaminaTotali
     */
    private void assegnaStatistiche(int[] stats) {
        //PV
        puntiVitaTotali = stats[0] + RNG.randomNumber(-5, 5);
        //ATTACCO
        attacco = stats[1] + RNG.randomNumber(-4, 4);
        if (attacco < 0)    // evita che il Debole abbia attacco negativo
            attacco = 0;
        //DIFESA
        difesa = stats[2] + RNG.randomNumber(-4, 4);
        if (difesa < 0)     // evita che il Debole abbia difesa negativa
            difesa = 0;
        //VELOCITA'
        velocita = stats[3] + RNG.randomNumber(-5, 5);
        //PS
        puntiStaminaTotali = stats[4] + RNG.randomNumber(-2, 2);

        // Inizialmente i valori di puntiVita e puntiStamina sono settati al massimo
        puntiStamina = puntiStaminaTotali;
        puntiVita = puntiVitaTotali;
    }

    /**
     * Metodo toString()
     *
     * @return Riepilogo breve dei principali attributi della classe e del tipo di personaggio
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + nome + " (" + sesso + ")" +
                "\n Punti Vita: " + puntiVita + "/" + puntiVitaTotali +
                "\n Stamina: " + puntiStamina + "/" + puntiStaminaTotali;
    }

    /**
     * Ottiene un riepilogo di tutte le statistiche del personaggio
     *
     * @return La stringa da stampare a video
     */
    public String ottieniStatistiche() {
        return " Punti Vita: " + puntiVitaTotali +
                "\n Attacco: " + attacco +
                "\n Difesa: " + difesa +
                "\n Velocità: " + velocita +
                "\n Punti Stamina: " + puntiStaminaTotali;
    }
}