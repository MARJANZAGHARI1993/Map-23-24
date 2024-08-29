package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import db.Database;
import db.Files;
import mainPackage.DescrizioneGioco;
import parser.ParserOutput;
import tipo.Oggetto;
import tipo.Stanza;
import tipo.Timer;

public class Castello extends DescrizioneGioco {

    private List<Oggetto> inventario;
    private boolean prossimaMossa;
    private boolean oggettoNonValido;
    private int mossa;
    private String username;
    private final File fileGioco = new File("castle_save.txt");
    private final Files file = new Files();
    private final Database db = new Database();
    private Stanza stanzaCorrente;
    private Timer timer;

    public Castello(String username) {
        super(); // Inizializza l'inventario
        this.username = username;
        this.timer = new Timer();
        try {
            inizializzazione();
            timer.start(); // Avvia il timer del gioco
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean prossimaMossa(ParserOutput scelta, DescrizioneGioco gioco)
            throws IOException, ClassNotFoundException {
        prossimaMossa = false;
        oggettoNonValido = false;
        mossa = 0;

        if (timer.isTimeUp()) {
            System.out.println("************** TEMPO SCADUTO! *****************");
            System.out.println("     __            _    _        _  _"
                    + "\n    /__  /\\  |\\/| |_   / \\ \\  / |_ |_)"
                    + "\n    \\_| /--\\ |  | |_   \\_/  \\/  |_ | \\");

            System.out.println("\n**********************************************");
            chiusura();
            return prossimaMossa;
        }

        handleScelta(scelta, gioco);

        switch (mossa) {
            case 1:
                displayStanzaCorrente();
                break;
            case 2:
                System.out.println("!! Direzione errata, qui c'è il muro!!");
                break;
            default:
                break;
        }

        return prossimaMossa;
    }

    private void handleScelta(ParserOutput scelta, DescrizioneGioco gioco) throws IOException, ClassNotFoundException {
        switch (scelta.getComandi().getTipo()) {
            case NORD ->
                moveToDirection(getStanzaCorrente().getNord(), "nord");
            case SOUTH ->
                moveToDirection(getStanzaCorrente().getSud(), "sud");
            case EAST ->
                moveToDirection(getStanzaCorrente().getEst(), "est");
            case WEST ->
                moveToDirection(getStanzaCorrente().getOvest(), "ovest");
            case HELP ->
                aiuto();
            case EXIT ->
                esci();
            case LOOK_AT ->
                guarda();
            case PICK_UP ->
                prendi(scelta.getOggetto());
            case WEAR ->
                indossa(scelta.getInvOggetto());
            case USE ->
                usa(scelta.getInvOggetto());
            case Inventario ->
                visualizzaInventario();
            case SAVE ->
                file.salvaFile(fileGioco, gioco);
            case LOAD -> {
                file.leggiFile(fileGioco, gioco);
                mossa = 1;
            }
            case DATABASE ->
                showDatabase();
            default ->
                System.out.println("Errore");
        }
    }

    private void moveToDirection(Stanza stanza, String direction) {
        if (stanza != null && stanza.isAccessibile()) {
            setStanzaCorrente(stanza);
            mossa = 1;
            if (getStanzaCorrente().getId() == 3 || getStanzaCorrente().getId() == 6) {
                variazionePercentuale(getPercentualeTotale(), getStanzaCorrente().getPercentualeVitale(), false);
            }
        } else {
            mossa = 2;
        }
    }

    private void guarda() {
        if (getStanzaCorrente().getGuarda() != null) {
            System.out.println(getStanzaCorrente().getGuarda());
        } else {
            System.out.println("Stai guardando il nulla.");
        }
    }

    private void prendi(Oggetto oggetto) {
        if (oggetto != null && oggetto.isPrendibile()) {
            getInventario().add(oggetto); // Aggiungi l'oggetto all'inventario
            getStanzaCorrente().getOggetto().remove(oggetto); // Rimuovi l'oggetto dalla stanza corrente
            System.out.println("Hai preso " + oggetto.getNome());
        } else {
            oggettoNonValido = true;
            System.out.println("Non puoi prendere questo oggetto.");
        }
    }

    private void indossa(Oggetto oggetto) {
        if (oggetto != null && oggetto.isIndossabile()) {
            System.out.println("Hai indossato " + oggetto.getNome());
            if (!oggetto.isIndossa()) {
                variazionePercentuale(getPercentualeTotale(), oggetto.getPercentualeVitale(), true);
                oggetto.setIndossa(true);
            }
        } else {
            System.out.println("Questo oggetto non si può indossare o devi ancora prenderlo");
            oggettoNonValido = true;
        }
    }

    private void usa(Oggetto oggetto) {
        if (oggetto != null && oggetto.isUtilizzabile()) {
            int stanzaCorrenteId = getStanzaCorrente().getId();

            if (oggetto.getId() == 1 && stanzaCorrenteId == 2) {
                getStanzaCorrente().getSud().setAccessibile(true);
                System.out.println("Hai utilizzato " + oggetto.getNome());
            } else if (oggetto.getId() == 8 && stanzaCorrenteId == 5) {
                handleVictory();
            } else if (!oggetto.isUtilizza()) {
                System.out.println("Non puoi utilizzare la " + oggetto.getNome() + " in questa stanza");
                variazionePercentuale(getPercentualeTotale(), oggetto.getPercentualeVitale(), true);
                oggetto.setUtilizza(true);
            }
        } else {
            System.out.println("Questo oggetto non si può utilizzare o devi ancora prenderlo");
            oggettoNonValido = true;
        }
    }

    private void handleVictory() {
        timer.stop();
        long totalTimeElapsed = timer.getElapsedTime();
        System.out.println(
                "Durata del gioco: " + totalTimeElapsed / 60 + " minuti e " + totalTimeElapsed % 60 + " secondi.");
        System.out.println("\n******************************************");
        System.out.println("\n              CONGRATULAZIONI             ");
        System.out.println("\n          HAI SALVATO IL TUO AMICO        ");
        System.out.println("\n******************************************");
        prossimaMossa = true;
        try {
            db.inserimentoTempo(username, (int) totalTimeElapsed);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showDatabase() {
        try {
            db.punteggioTempo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayStanzaCorrente() {
        System.out.print("--->>");
        percentualeStampa(getPercentualeTotale());
        System.out.println(getStanzaCorrente().getDescrizione());
        System.out.print("--->>");
    }

    public void esci() {
        System.out.println("Sei sicuro di voler uscire dal gioco?");
        System.out.println("Digita SI o NO:");
        boolean exit = false;
        do {
            Scanner fine = new Scanner(System.in);
            String conferma = fine.next().toUpperCase();
            if ("SI".equals(conferma)) {
                System.out.println("\n****************************************");
                System.out.println("\n         Grazie per aver giocato");
                System.out.println("\n****************************************");
                chiusura();
            } else if ("NO".equals(conferma)) {
                exit = true;
            } else {
                System.out.println("Digita SI o NO ");
            }
        } while (!exit);
    }

    public void aiuto() {
        Menu.printComandi();
    }

    public void percentualeStampa(int percentualeTotale) {
        System.out.println("--->> vite " + percentualeTotale + " %");
    }

    public void variazionePercentuale(int percentualeTotale, int percentuale, boolean aumentoPercentuale) {
        setPercentualeTotale(aumentoPercentuale ? percentualeTotale + percentuale : percentualeTotale - percentuale);
        if (getPercentualeTotale() <= 20) {
            setPercentualeTotale(20);
            System.out.println("********************************************");
            System.out.println("\n      HAI TERMINATO LE VITE E SEI MORTO");
            System.out.println("     __            _    _        _  _"
                    + "\n    /__  /\\  |\\/| |_   / \\ \\  / |_ |_)"
                    + "\n    \\_| /--\\ |  | |_   \\_/  \\/  |_ | \\");

            System.out.println("\n*********************************************");
            chiusura();
        } else if (getPercentualeTotale() > 100) {
            setPercentualeTotale(100);
        }
    }

    public void chiusura() {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in))) {
            buffer.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            Runtime.getRuntime().exit(0);
        }
    }

    public void visualizzaInventario() {
        if (getInventario().isEmpty()) {
            System.out.println("Il tuo inventario è vuoto.");
        } else {
            System.out.println("Nel tuo inventario hai:");
            for (Oggetto oggetto : getInventario()) {
                System.out.println("-->" + oggetto.getNome());
            }
        }
    }

}
