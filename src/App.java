package ittbuonarroti.rpggame;

import ittbuonarroti.rpggame.characters.*;
import ittbuonarroti.rpggame.engine.GestionePartita;

import java.util.Scanner;

/**
 * Classe che contiene il metodo Main
 */
public class App {
    public static void main(String[] args) {

        final int N_PLAYERS = 2;

        boolean sceltaCorretta = false;
        Personaggio[] personaggi = new Personaggio[N_PLAYERS];
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < N_PLAYERS; i++) {
            String nome, scelta;
            char sesso = 0;     // valore placeholder

            // Input del nome
            System.out.print("\nGiocatore " + (i + 1) + ", come ti chiami? ");
            nome = input.nextLine();

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
        }

        // INIZIO PARTITA
        GestionePartita partita = new GestionePartita(personaggi);
        int[] ordineGiocatori = new int[N_PLAYERS];
        String scelta;

        while (partita.getVincitore() == -1)      // ciclo esterno: turno giocatore 1 + giocatore 2
        {
            System.out.println("\nTURNO " + partita.getContatoreTurno());

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
                // TODO: pulisci schermo console

                // Stampa condizioni personaggi
                for (Personaggio p : personaggi)
                    System.out.println(p.toString());

                // Scelta mossa
                while (!sceltaCorretta) {
                    System.out.println("\nTURNO DEL GIOCATORE " + giocatore);
                    System.out.println("Mosse disponibili:");
                    System.out.println("F: tenta fuga");
                    if (partita.getPlayerCharacter(giocatore) instanceof IAttaccante) {
                        System.out.println("A: attacca");
                        System.out.println("C: caricati per sferrare doppi danni il prossimo turno");
                    }
                    if (partita.getPlayerCharacter(giocatore) instanceof IDifesa)
                        System.out.println("D: difenditi per subire danni dimezzati il prossimo turno");
                    System.out.print("Fai la tua mossa: ");
                    scelta = input.nextLine();

                    switch (scelta) {
                        case "F":
                        case "f":
                            partita.faiMossa(giocatore, GestionePartita.MOVE_RUN);
                            sceltaCorretta = true;
                            break;
                        case "A":
                        case "a":
                            if (!(partita.getPlayerCharacter(giocatore) instanceof IAttaccante))
                                System.out.println("Mossa non disponibile per questo personaggio!");
                            else {
                                partita.faiMossa(giocatore, GestionePartita.MOVE_ATTACK);
                                sceltaCorretta = true;
                            }
                            break;
                        case "C":
                        case "c":
                            if (!(partita.getPlayerCharacter(giocatore) instanceof IAttaccante))
                                System.out.println("Mossa non disponibile per questo personaggio!");
                            else {
                                partita.faiMossa(giocatore, GestionePartita.MOVE_POWER_UP);
                                sceltaCorretta = true;
                            }
                            break;
                        case "D":
                        case "d":
                            if (!(partita.getPlayerCharacter(giocatore) instanceof IDifesa))
                                System.out.println("Mossa non disponibile per questo personaggio!");
                            else {
                                partita.faiMossa(giocatore, GestionePartita.MOVE_GUARD);
                                sceltaCorretta = true;
                            }
                            break;
                        default:
                            System.out.println("Scelta errata!");
                    }
                }
                sceltaCorretta = false;
            }

            // Controlla se, dopo la mossa, la partita è conclusa e in tal caso esci dal ciclo
            if (partita.controlloPartitaConclusa() == true)
                break;
        }

        // Stampa vincitore
        int winner = partita.getVincitore();
        if (winner > 0)
            System.out.println("Il vincitore è il giocatore " + winner + ", con il personaggio " + partita.getPlayerCharacter(winner).getNome() + "!");
        else
            System.out.println("La partita si è conclusa in parità!");
    }
}
