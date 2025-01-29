package org.MainApp;

import jakarta.persistence.EntityManager;
import org.trasporti.ENUMS.Validita;
import org.trasporti.EntityManagerUtil;
import org.DAO.*;
import org.trasporti.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        Scanner scanner = new Scanner(System.in);
        try {
            MezzoDAO mezzoDAO = new MezzoDAO(em);
            DistributoreDAO distributoreDAO = new DistributoreDAO(em);
            AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
            BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
            ObliterazioneDAO obliterazioneDAO = new ObliterazioneDAO(em);
            PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
            TesseraDAO tesseraDAO = new TesseraDAO(em);
            TrattaDAO trattaDAO = new TrattaDAO(em);
            ManutenzioneDAO manutenzioneDAO = new ManutenzioneDAO(em);


            String sceltaUtente = "";
            while (!sceltaUtente.equals("1") && !sceltaUtente.equals("2")) {
                System.out.println("Scegli tipo di utente");
                System.out.println("1. Amministratore");
                System.out.println("2. Utente");
                sceltaUtente = scanner.nextLine();
                switch (sceltaUtente) {
                    case "1":
                        Main.amministratore();
                        break;
                    case "2":
                        Main.utente();
                        break;
                    default:
                        System.out.println("Numero non valido, prova ad inserire 1 o 2");
                }
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static void utente() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEntityManager();
        TesseraDAO tesseraDAO = new TesseraDAO(em);
        String nTessera;
        Tessera tesseraUtente = new Tessera();
        while (true) {
            System.out.println("Inserisci il numero della tua tessera oppure digita 0 per crearne una nuova");
            nTessera = scanner.nextLine();
            if (nTessera.equals("O")) {
                System.out.println("Inserisci il tuo nome e cognome");
                String nome = scanner.nextLine();
                Tessera newTessera = new Tessera(nome, LocalDate.now());
                tesseraDAO.save(newTessera);
                tesseraUtente = newTessera;
                break;
            } else if (tesseraDAO.verificaValidita(Long.valueOf(nTessera))) {
                tesseraUtente = tesseraDAO.getById(Long.valueOf(nTessera));
                break;
            }
        }
        String input = "";
        while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
            System.out.println("Cosa vuoi fare?");
            System.out.println("1. Comprare biglietto");
            System.out.println("2. Comprare abbonamento");
            System.out.println("3. Prendere un mezzo");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    Main.compraBiglietto();
                    break;
                case "2":
                    Main.compraAbbonamento(tesseraUtente);
                    break;
                case "3":
                    do {
                        System.out.println("Hai già un biglietto o un abbonamento?");
                        System.out.println("1. Si");
                        System.out.println("2. No");
                        input = scanner.nextLine();
                        switch (input) {
                            case "1":
                                sceltaMezzo();
                                break;
                            case "2":
                                System.out.println("Allora compralo!");
                                break;
                            default:
                        }
                    } while (!input.equals("1") && !input.equals("2"));
                    input = "";
                    break;
                default:
                    System.out.println("Numero non valido, prova ad inserire 1 o 2 o 3");
            }

        }
    }

    public static void compraBiglietto() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEntityManager();
        DistributoreDAO distributoreDAO = new DistributoreDAO(em);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
        Biglietto newBiglietto;
        int input;
        while (true) {
            System.out.println("Dove vuoi comprare il biglietto?");
            List<Distributore> listaAttivi = distributoreDAO.getAllActive();
            int i = 1;
            for (Distributore d : listaAttivi)
                System.out.println(i++ + d.getNome());
            input = Integer.parseInt(scanner.nextLine());
            System.out.println(input);
            if (listaAttivi.size() >= input && input > 0) {
                newBiglietto = new Biglietto(listaAttivi.get(input - 1), LocalDate.now());
                bigliettoDAO.save(newBiglietto);
                System.out.println("Biglietto acquistato con successo");
                break;
            } else {
                System.out.println("Numero non valido, riprova");
            }
        }
        usaBiglietto(newBiglietto, Main.sceltaMezzo());
    }

    public static void compraAbbonamento(Tessera tesseraUtente) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEntityManager();
        DistributoreDAO distributoreDAO = new DistributoreDAO(em);
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
        Abbonamento abbonamento;
        int input;
        while (true) {
            System.out.println("Dove vuoi comprare l'abbonamento?");
            List<Distributore> listaAttivi = distributoreDAO.getAllActive();
            int i = 1;
            for (Distributore d : listaAttivi)
                System.out.println(i++ + d.getNome());
            input = Integer.parseInt(scanner.nextLine());
            if (listaAttivi.size() >= input && input > 0) {
                System.out.println("Inserisci che tipo di abbonamento vuoi? (SETTIMANALE o MENSILE)");
                String validita = scanner.nextLine();
                abbonamento = new Abbonamento(tesseraUtente, listaAttivi.get(input - 1), LocalDate.now(), Validita.SETTIMANALE);
                abbonamentoDAO.save(abbonamento);
                System.out.println("Abbonamento acquistato con successo");
                break;
            } else {
                System.out.println("Numero non valido, riprova");
            }
        }
        Main.sceltaMezzo();
    }

    public static void usaBiglietto(Biglietto biglietto, Mezzo mezzo) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEntityManager();
        ObliterazioneDAO obliterazioneDAO = new ObliterazioneDAO(em);
        Obliterazione obliterazione = new Obliterazione(mezzo, biglietto, LocalDate.now());
        obliterazioneDAO.save(obliterazione);
    }

    public static Mezzo sceltaMezzo() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEntityManager();
        PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        int i = 1;
        int input;
        List<Percorrenza> listaPercorrenze = percorrenzaDAO.getAll();
        while (true) {
            System.out.println("Seleziona la tratta che vuoi usare");
            for (Percorrenza p : listaPercorrenze)
                System.out.println((i++ + 1) + ". " + p);
            input = Integer.parseInt(scanner.nextLine());
            if (listaPercorrenze.size() >= input && input > 0) {
                System.out.println("Buon viaggio!");
                return percorrenzaDAO.getById((long) input).getMezzo();
            }
            System.out.println("Numero non valido!");
        }
    }

    public static void amministratore() {

    }
}