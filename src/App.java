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
        GestionePartita gestore;

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
                    sesso = scelta.charAt(0);
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

        gestore = new GestionePartita(personaggi);
        // TO BE CONTINUED
    }
}
