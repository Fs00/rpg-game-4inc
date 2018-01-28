package ittbuonarroti.rpggame;

import ittbuonarroti.rpggame.characters.*;
import ittbuonarroti.rpggame.engine.GestionePartita;

import java.util.Scanner;

/**
 * Classe che contiene il metodo Main
 */
public class App {
    public static void main(String[] args) {
        try {

            final int N_PLAYERS = 2;

            boolean sceltaCorretta = false;
            Personaggio[] personaggi = new Personaggio[N_PLAYERS];  // contiene i personaggi dei giocatori
            Scanner input = new Scanner(System.in);

            // SCELTA DI NOME, SESSO E PERSONAGGIO
            for (int i = 0; i < N_PLAYERS; i++) {
                String nome = null, scelta;
                char sesso = 0;     // valore placeholder

                // Input del nome
                while (!sceltaCorretta) {
                    System.out.print("\nGiocatore " + (i + 1) + ", come ti chiami? ");
                    nome = input.nextLine();

                    // Controllo che nessuno degli altri giocatori abbia lo stesso nome
                    sceltaCorretta = true;
                    int cnt = 0;
                    while (cnt < i && sceltaCorretta) {
                        if (nome.equals(personaggi[cnt].getNome())) {
                            sceltaCorretta = false;
                            System.out.print("Uno degli altri giocatori ha già questo nome!");
                        }
                        cnt++;
                    }
                }
                sceltaCorretta = false;

                // Scelta del sesso
                while (!sceltaCorretta) {
                    System.out.print("Sei maschio o femmina? (M/F) ");
                    scelta = input.nextLine();
                    if (scelta.length() == 1 && (scelta.equals("M") || scelta.equals("F") || scelta.equals("m") || scelta.equals("f"))) {
                        sesso = scelta.toUpperCase().charAt(0);
                        sceltaCorretta = true;
                    }
                    else
                        System.out.println("Scelta errata!");
                }
                sceltaCorretta = false;

                // Scelta del personaggio
                System.out.println("\nScegli il tuo personaggio:");
                System.out.println("DEBOLE (D)\n" + "Insert description here\n" + "Statistiche di base: PV 50, ATK 0, DEF 0, VEL 40, STM 5");
                System.out.println("CONTADINO (C)\n" + "Insert description here\n" + "Statistiche di base: PV 100, ATK 35, DEF 10, VEL 50, STM 15");
                System.out.println("SOLDATO (S)\n" + "Insert description here\n" + "Statistiche di base: PV 180, ATK 55, DEF 35, VEL 20, STM 25");
                System.out.println("MERCENARIO (M)\n" + "Insert description here\n" + "Statistiche di base: PV 200, ATK 60, DEF 25, VEL 30, STM 22");

                while (!sceltaCorretta) {
                    System.out.print("Fai la tua scelta: ");
                    scelta = input.nextLine();
                    switch (scelta) {
                        case "d":
                        case "D":
                            personaggi[i] = new Debole(nome, sesso);
                            sceltaCorretta = true;
                            break;
                        case "c":
                        case "C":
                            personaggi[i] = new Contadino(nome, sesso);
                            sceltaCorretta = true;
                            break;
                        case "s":
                        case "S":
                            personaggi[i] = new Soldato(nome, sesso);
                            sceltaCorretta = true;
                            break;
                        case "m":
                        case "M":
                            personaggi[i] = new Mercenario(nome, sesso);
                            sceltaCorretta = true;
                            break;
                        default:
                            System.out.println("Scelta errata!");
                            break;
                    }
                }
                sceltaCorretta = false;

                // Stampa statistiche randomizzate
                System.out.println("\nEcco le statistiche del tuo personaggio:");
                System.out.println(personaggi[i].ottieniStatistiche());
            }

            // INIZIO PARTITA
            GestionePartita partita = new GestionePartita(personaggi);
            int[] ordineGiocatori = new int[N_PLAYERS];     // contiene gli indici dei giocatori (1 o 2) in ordine di turno
            String scelta;

            clearConsole();
            while (partita.getVincitore() == -1)      // ciclo esterno: turno giocatore 1 + giocatore 2
            {
                // Selezione giocatore che incomincia il turno
                int primoGiocatore = partita.giocatorePiuVeloce();
                if (primoGiocatore == 1) {
                    ordineGiocatori[0] = 1;
                    ordineGiocatori[1] = 2;
                }
                else if (primoGiocatore == 2) {
                    ordineGiocatori[0] = 2;
                    ordineGiocatori[1] = 1;
                }

                for (int giocatore : ordineGiocatori) {     // ciclo interno: turno dei singoli giocatori

                    System.out.println("\nTURNO " + partita.getContatoreTurno());

                    // Stampa condizioni personaggi
                    for (Personaggio p : personaggi)
                        System.out.println(p.toString());

                    // Stampa mosse disponibili
                    System.out.println("\nTURNO DEL GIOCATORE " + personaggi[giocatore - 1].getNome().toUpperCase());
                    System.out.println("Mosse disponibili:");
                    System.out.println("F: tenta fuga");
                    if (personaggi[giocatore - 1] instanceof IAttaccante) {
                        System.out.println("A: attacca");
                        System.out.println("C: caricati per sferrare un attacco potenziato il prossimo turno");
                    }
                    if (personaggi[giocatore - 1] instanceof IDifesa)
                        System.out.println("D: difenditi per subire danni dimezzati il prossimo turno");

                    // Scelta mossa
                    while (!sceltaCorretta) {
                        System.out.print("Fai la tua mossa: ");
                        scelta = input.nextLine();

                        // Effettua la mossa in base alla scelta e qualora questa fosse corretta pulisci la console
                        // in modo che il risultato della mossa venga stampato subito dopo la pulizia dell'output
                        switch (scelta) {
                            // Fuga
                            case "F":
                            case "f":
                                clearConsole();
                                partita.faiMossa(giocatore, GestionePartita.MOVE_RUN);
                                sceltaCorretta = true;
                                break;

                            // Attacco
                            case "A":
                            case "a":
                                if (!(personaggi[giocatore - 1] instanceof IAttaccante))
                                    System.out.println("Mossa non disponibile per questo personaggio!");
                                else {
                                    clearConsole();
                                    partita.faiMossa(giocatore, GestionePartita.MOVE_ATTACK);
                                    sceltaCorretta = true;
                                }
                                break;

                            // Preparazione dell'attacco caricato
                            case "C":
                            case "c":
                                if (!(personaggi[giocatore - 1] instanceof IAttaccante))
                                    System.out.println("Mossa non disponibile per questo personaggio!");
                                else {
                                    clearConsole();
                                    partita.faiMossa(giocatore, GestionePartita.MOVE_POWER_UP);
                                    sceltaCorretta = true;
                                }
                                break;

                            // Attivazione dello "scudo difensivo"
                            case "D":
                            case "d":
                                if (!(personaggi[giocatore - 1] instanceof IDifesa))
                                    System.out.println("Mossa non disponibile per questo personaggio!");
                                else {
                                    clearConsole();
                                    partita.faiMossa(giocatore, GestionePartita.MOVE_GUARD);
                                    sceltaCorretta = true;
                                }
                                break;

                            default:
                                System.out.println("Scelta errata!");
                        }
                    }
                    sceltaCorretta = false;

                    // Controlla se, dopo la mossa, la partita è conclusa e in tal caso esci dal ciclo
                    if (partita.controlloPartitaConclusa() == true)
                        break;
                }
            }

            // Stampa vincitore
            int winner = partita.getVincitore();
            if (winner > 0)
                System.out.println("Il vincitore è il giocatore " + winner + ", con il personaggio " + personaggi[winner - 1].getNome() + "!");
            else
                System.out.println("La partita si è conclusa in parità!");
        }
        catch (InterruptedException e) {    // exception handler per il Thread.sleep() di GestionePartita.faiMossa()
            System.out.println("Il thread è stato interrotto.");
        }
    }

    private static void clearConsole() {
        if (System.getProperty("os.name").contains("Windows")) {    // Il cmd di Windows non supporta i caratteri escape :/
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            catch (Exception exc) {
                System.out.println("Errore inatteso durante la pulizia dello schermo della console.");
            }
        }
        else {
            System.out.println("\033[H\033[2J");
            System.out.flush();
        }
    }
}
