package org.MainApp;

import jakarta.persistence.EntityManager;
import org.trasporti.ENUMS.StatoMezzo;
import org.trasporti.ENUMS.Timbrato;
import org.trasporti.ENUMS.TipoMezzo;
import org.trasporti.ENUMS.Validita;
import org.trasporti.EntityManagerUtil;
import org.DAO.*;
import org.trasporti.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        Scanner scanner = new Scanner(System.in);
        try {
            MezzoDAO mezzoDAO = new MezzoDAO(em);
            ManutenzioneDAO manutenzioneDAO = new ManutenzioneDAO(em);
            DistributoreDAO distributoreDAO = new DistributoreDAO(em);
            AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
            BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
            ObliterazioneDAO obliterazioneDAO = new ObliterazioneDAO(em);
            PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
            TesseraDAO tesseraDAO = new TesseraDAO(em);
            TrattaDAO trattaDAO = new TrattaDAO(em);

            String sceltaUtente = "";
            while (!sceltaUtente.equals("1") && !sceltaUtente.equals("2") && !sceltaUtente.equals("3")) {
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
            if (nTessera.equals("0")) {
                System.out.println("Inserisci il tuo nome e cognome");
                String nome = scanner.nextLine();
                Tessera newTessera = new Tessera(nome, LocalDate.now());
                tesseraDAO.save(newTessera);
                tesseraUtente = newTessera;
                System.out.println("Tessera creata, scadenza il " + newTessera.getDataEmissione().plusDays(360));
                break;
            } else if (tesseraDAO.verificaValidita(Long.valueOf(nTessera))) {
                tesseraUtente = tesseraDAO.getById(Long.valueOf(nTessera));
                break;
            }
        }
        String input = "";
        while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
            String input2;
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
                        input2 = scanner.nextLine();
                        switch (input2) {
                            case "1":
                                sceltaMezzo();
                                break;
                            case "2":
                                System.out.println("Allora compralo!");
                                input = "";
                                break;
                            default:
                        }
                    } while (!input2.equals("1") && !input2.equals("2"));
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
                System.out.println(i++ + ". " + d.getNome());
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
                System.out.println(i++ + ". " + d.getNome());
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
        biglietto.setTimbrato(Timbrato.TIMBRATO);
        Obliterazione obliterazione = new Obliterazione(mezzo, biglietto, LocalDate.now());
        obliterazioneDAO.save(obliterazione);
        System.out.println("Biglietto timbrato");
    }

    public static Mezzo sceltaMezzo() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEntityManager();
        PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        int i = 1;
        int input;
        List<Percorrenza> listaPercorrenze = percorrenzaDAO.getAllWorking();
        while (true) {
            System.out.println("Seleziona la tratta che vuoi usare");
            for (Percorrenza p : listaPercorrenze)
                System.out.println((i++) + ". " + p);
            input = Integer.parseInt(scanner.nextLine());
            if (listaPercorrenze.size() >= input && input > 0) {
                System.out.println("Buon viaggio!");
                return percorrenzaDAO.getById((long) input).getMezzo();
            }
            System.out.println("Numero non valido!");
        }
    }

    public static void amministratore() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("0")) {
            System.out.println("Cosa vuoi fare?");
            System.out.println("0. Esci dal programma");
            System.out.println("1. Manutenzione e gestione mezzi");
            System.out.println("2. Creazione nuove tratte");
            System.out.println("3. Creazione nuova percorrenza");
            System.out.println("4. Visualizza statistiche");
            input = scanner.nextLine();
            switch (input) {
                case "0":
                    System.out.println("Arrivederci!");
                    break;
                case "1":
                    Main.manutenzione();
                    break;
                case "2":
                    Main.creazioneTratte();
                    break;
                case "3":
                    Main.creazionePercorrenze();
                    break;
                case "4":
                    Main.statistiche();
                    break;
                default:
                    System.out.println("Numero non valido!");
            }
        }
    }

    public static void manutenzione() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEntityManager();
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        ManutenzioneDAO manutenzioneDAO = new ManutenzioneDAO(em);
        String input = "";
        while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
            System.out.println("Cosa vuoi fare?");
            System.out.println("1. Nuova manutenzione");
            System.out.println("2. Termina una manutenzione esistente");
            System.out.println("3. Crea un nuovo mezzo");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    List<Mezzo> listaMezziFunzionanti = mezzoDAO.getAllWorking();
                    int i = 1;
                    System.out.println("Quale mezzo vuoi far riparare?");
                    for (Mezzo m : listaMezziFunzionanti)
                        System.out.println((i++) + ". " + m);
                    int scelta = Integer.parseInt(scanner.nextLine());
                    if (listaMezziFunzionanti.size() >= scelta && scelta > 0) {
                        Mezzo mezzoDaManutenzionare = listaMezziFunzionanti.get(scelta - 1);
                        System.out.println("Che manutenzione deve effettuare il mezzo?");
                        String descr = scanner.nextLine();
                        mezzoDaManutenzionare.setStatoMezzo(StatoMezzo.GUASTO);
                        Manutenzione newManutenzione = new Manutenzione(mezzoDaManutenzionare, descr, LocalDate.now());
                        manutenzioneDAO.save(newManutenzione);
                        System.out.println("Manutenzione iniziata in data " + LocalDate.now());
                    }
                    break;
                case "2":
                    List<Manutenzione> listaManutenzioni = manutenzioneDAO.getAll();
                    int j = 1;
                    System.out.println("Quale manutenzione vuoi terminare?");
                    for (Manutenzione m : listaManutenzioni)
                        System.out.println((j++) + ". " + m);
                    int scelta2 = Integer.parseInt(scanner.nextLine());
                    if (listaManutenzioni.size() >= scelta2 && scelta2 > 0) {
                        Manutenzione manutenzioneDaAggiornare = listaManutenzioni.get(scelta2 - 1);
                        Mezzo mezzoDaAggiornare = manutenzioneDaAggiornare.getMezzo();
                        mezzoDaAggiornare.setStatoMezzo(StatoMezzo.FUNZIONANTE);
                        manutenzioneDaAggiornare.setDataFine(LocalDate.now());
                        manutenzioneDAO.getAll();
                        System.out.println("Manutenzione terminata in data " + LocalDate.now());
                    }
                    break;
                case "3":
                    while (true) {
                        System.out.println("Che mezzo vuoi creare? (TRAM | BUS)");
                        String tipoMezzo = scanner.nextLine();
                        TipoMezzo tipoMezzo1;
                        if (tipoMezzo.equalsIgnoreCase("TRAM") || tipoMezzo.equalsIgnoreCase("BUS")) {
                            if (tipoMezzo.equalsIgnoreCase("TRAM")) {
                                tipoMezzo1 = TipoMezzo.TRAM;
                            } else {
                                tipoMezzo1 = TipoMezzo.BUS;
                            }
                            System.out.println("Inserisci il numero del mezzo");
                            String numeroMezzo = scanner.nextLine();
                            Mezzo newMezzo = new Mezzo(numeroMezzo, tipoMezzo1, StatoMezzo.FUNZIONANTE);
                            mezzoDAO.save(newMezzo);
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("Numero non valido!");
            }
        }
    }

    public static void creazioneTratte() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEntityManager();
        TrattaDAO trattaDAO = new TrattaDAO(em);
        System.out.println("Da dove parte la tratta?");
        String partenza = scanner.nextLine();
        System.out.println("Capolinea della tratta");
        String capolinea = scanner.nextLine();
        System.out.println("Tempo percorrenza stimato (OO:MM:SS)");
        String tempoStimato = scanner.nextLine();
        Tratta newTratta = new Tratta(partenza, capolinea, tempoStimato);
        try {
            trattaDAO.save(newTratta);
            System.out.println("Nuova tratta creata");
        } catch (Exception ignored) {
        }
    }

    public static void creazionePercorrenze() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEntityManager();
        TrattaDAO trattaDAO = new TrattaDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
        while (true) {
            System.out.println("Quale tratta è stata usata?");
            List<Tratta> listaTratte = trattaDAO.getAll();
            int i = 1;
            for (Tratta t : listaTratte)
                System.out.println((i++) + ". " + t);
            int scelta = Integer.parseInt(scanner.nextLine());
            if (listaTratte.size() >= scelta && scelta > 0) {
                Tratta trattaScelta = listaTratte.get(scelta - 1);
                System.out.println("Quale mezzo ha percorso la tratta?");
                List<Mezzo> listaMezzi = mezzoDAO.getAllWorking();
                int j = 1;
                for (Mezzo m : listaMezzi) {
                    System.out.println((j++) + ". " + m);
                }
                int scelta2 = Integer.parseInt(scanner.nextLine());
                if (listaMezzi.size() >= scelta2 && scelta2 > 0) {
                    Mezzo mezzoScelto = listaMezzi.get(scelta2 - 1);
                    System.out.println("Inserisci il tempo effettivo di percorrenza (OO:MM:SS)");
                    String tempo = scanner.nextLine();
                    Percorrenza newPercorrenza = new Percorrenza(mezzoScelto, trattaScelta, tempo);
                    System.out.println("Nuova percorrenza creata");
                    break;
                }
            }
        }
    }

    public static void statistiche() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEntityManager();
        String input = "";
        while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5") && !input.equals("6")) {
            System.out.println("Che statistiche vuoi vedere?");
            System.out.println("1. Biglietti e abbonamenti emessi da un distributore a scelta");
            System.out.println("2. Biglietti e abbonamenti emessi in un periodo di tempo a scelta");
            System.out.println("3. Numero di biglietti obliterati in un mezzo a scelta");
            System.out.println("4. Numero di biglietti obliterati in un periodo di tempo a scelta");
            System.out.println("5. Numero di volte in cui un mezzo percorre una tratta");
            System.out.println("6. Tempo medio di percorrenza di una tratta");
            input = scanner.nextLine();
            System.out.println();
            switch (input) {
                case "1":
                    System.out.println("Numero totale di biglietti e abbonamenti per il distributore scelto: " + Main.statistica1());
                    break;
                case "2":
                    System.out.println("Numero di abbonamenti e biglietti emessi in un tempo scelto: " + Main.statistica2());
                    break;
                case "3":
                    System.out.println("Numero di biglietti obliterati nel mezzo scelto: " + Main.statistica3());
                    break;
                case "4":
                    System.out.println("Numero di biglietti obliterati in un tempo scelto: " + Main.statistica4());
                    break;
                case "5":
                    System.out.println("Numero di volte in cui il mezzo scelto percorre la tratta scelta: " + Main.statistica5());
                    break;
                case "6":
                    System.out.println("Tempo di percorrenza medio della tratta scelta: " + Main.statistica6());
                    break;
                default:
                    System.out.println("Numero non valido!");
            }
        }
    }


    public static Integer statistica1() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEntityManager();
        DistributoreDAO distributoreDAO = new DistributoreDAO(em);
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
        List<Distributore> listaDistributori = distributoreDAO.getAll();
        long id;
        while (true) {
            System.out.println("Quale distributore vuoi controllare?");
            int i = 1;
            for (Distributore d : listaDistributori) {
                System.out.println((i++) + ". " + d);
            }
            int scelta = Integer.parseInt(scanner.nextLine());
            if (listaDistributori.size() >= scelta && scelta > 0) {
                Distributore distributoreScelto = listaDistributori.get(scelta - 1);
                id = distributoreScelto.getId();
                break;
            }
        }
        List<Biglietto> listaBiglietti = bigliettoDAO.getByDistributore(id);
        List<Abbonamento> listaAbbonamenti = abbonamentoDAO.getByDistributore(id);
        return listaAbbonamenti.size() + listaBiglietti.size();
    }

    public static Integer statistica2() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEntityManager();
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
        LocalDate dataInizio, dataFine;
        System.out.println("Inserisci data inizio periodo (yyyy-mm-dd)");
        dataInizio = LocalDate.parse(scanner.nextLine());
        System.out.println("Inserisci data fine periodo (yyyy-mm-dd)");
        dataFine = LocalDate.parse(scanner.nextLine());

        return abbonamentoDAO.getByData(dataInizio, dataFine) + bigliettoDAO.getByData(dataInizio, dataFine);
    }

    public static Integer statistica3() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEntityManager();
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        ObliterazioneDAO obliterazioneDAO = new ObliterazioneDAO(em);
        List<Mezzo> listaMezzi = mezzoDAO.getAll();
        long id;
        while (true) {
            System.out.println("Quale mezzo vuoi controllare?");
            int i = 1;
            for (Mezzo m : listaMezzi) {
                System.out.println((i++) + ". " + m);
            }
            int scelta = Integer.parseInt(scanner.nextLine());
            if (listaMezzi.size() >= scelta && scelta > 0) {
                Mezzo mezzoScelto = listaMezzi.get(scelta - 1);
                id = mezzoScelto.getId();
                break;
            }
        }
        return obliterazioneDAO.getByMezzo(id);
    }

    public static Integer statistica4() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEntityManager();
        ObliterazioneDAO obliterazioneDAO = new ObliterazioneDAO(em);
        LocalDate dataInizio, dataFine;
        System.out.println("Inserisci data inizio periodo (yyyy-mm-dd)");
        dataInizio = LocalDate.parse(scanner.nextLine());
        System.out.println("Inserisci data fine periodo (yyyy-mm-dd)");
        dataFine = LocalDate.parse(scanner.nextLine());
        return obliterazioneDAO.getByData(dataInizio, dataFine);
    }

    public static Integer statistica5() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEntityManager();
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
        List<Tratta> listaTratte = trattaDAO.getAll();
        List<Mezzo> listaMezzi = mezzoDAO.getAll();
        long idM, idT;
        while (true) {
            System.out.println("Quale mezzo vuoi controllare?");
            int i = 1;
            for (Mezzo m : listaMezzi) {
                System.out.println((i++) + ". " + m);
            }
            int scelta = Integer.parseInt(scanner.nextLine());
            if (listaMezzi.size() >= scelta && scelta > 0) {
                Mezzo mezzoScelto = listaMezzi.get(scelta - 1);
                idM = mezzoScelto.getId();
                break;
            }
        }
        while (true) {
            System.out.println("Quale tratta vuoi controllare?");
            int j = 1;
            for (Tratta t : listaTratte) {
                System.out.println((j++) + ". " + t);
            }
            int scelta2 = Integer.parseInt(scanner.nextLine());
            if (listaTratte.size() >= scelta2 && scelta2 > 0) {
                Tratta trattaScelta = listaTratte.get(scelta2 - 1);
                idT = trattaScelta.getId();
                break;
            }
        }
        return percorrenzaDAO.getByMezzoAndTrattaId(idM, idT).size();
    }

    public static String statistica6() {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = EntityManagerUtil.getEntityManager();
        TrattaDAO trattaDAO = new TrattaDAO(em);
        PercorrenzaDAO percorrenzaDAO = new PercorrenzaDAO(em);
        List<Tratta> listaTratte = trattaDAO.getAll();
        Long id;
        while (true) {
            System.out.println("Quale tratta vuoi controllare?");
            int j = 1;
            for (Tratta t : listaTratte) {
                System.out.println((j++) + ". " + t);
            }
            int scelta2 = Integer.parseInt(scanner.nextLine());
            if (listaTratte.size() >= scelta2 && scelta2 > 0) {
                Tratta trattaScelta = listaTratte.get(scelta2 - 1);
                id = trattaScelta.getId();
                break;
            }
        }
        int t = percorrenzaDAO.getAll().stream()
                .filter(percorrenza -> Objects.equals(percorrenza.getTratta().getId(), id))
                .collect(Collectors.averagingInt(percorrenze -> Interval.travelTimeToSeconds(percorrenze.getTempoEffettivo())))
                .intValue();
        return Interval.secondsToTravelTime(t);
    }
}