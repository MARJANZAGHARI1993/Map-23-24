package mainPackage;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import parser.ParserOutput;
import tipo.Comandi;
import tipo.ComandiTipo;
import tipo.Oggetto;
import tipo.Stanza;

public abstract class DescrizioneGioco implements Serializable {

    private final List<Stanza> stanza = new ArrayList<>();
    private List<Comandi> comandi = new ArrayList<>();
    private List<Oggetto> inventario = new ArrayList<>();
    private Stanza stanzaCorrente;
    private int percentualeTotale;
    boolean aumentoPercentuale; // true per indicare un aumento di percentuale, false per un decremento
    private Timer tempo = new Timer();

    // Questo metodo inizializza tutti i comandi da utilizzare,le stanze presenti
    // la mappa del gioco e gli oggetti all'interno delle varie stanze.
    public void inizializzazione() {
        // tutti i comandi
        Comandi nord = new Comandi(ComandiTipo.NORD);
        nord.setAlias(new String[]{"nord", "n", "north"});
        getComandi().add(nord);

        Comandi sud = new Comandi(ComandiTipo.SOUTH);
        sud.setAlias(new String[]{"sud", "s", "south"});
        getComandi().add(sud);

        Comandi est = new Comandi(ComandiTipo.EAST);
        est.setAlias(new String[]{"est", "e", "east"});
        getComandi().add(est);

        Comandi ovest = new Comandi(ComandiTipo.WEST);
        ovest.setAlias(new String[]{"ovest", "o", "west"});
        getComandi().add(ovest);

        Comandi help = new Comandi(ComandiTipo.HELP);
        help.setAlias(new String[]{"help", "?", "aiuto", "h"});
        getComandi().add(help);

        Comandi osserva = new Comandi(ComandiTipo.LOOK_AT);
        osserva.setAlias(new String[]{"guarda", "g", "osserva", "lookat"});
        getComandi().add(osserva);

        Comandi apri = new Comandi(ComandiTipo.OPEN);
        apri.setAlias(new String[]{"apri", "a", "open"});
        getComandi().add(apri);

        Comandi prendi = new Comandi(ComandiTipo.PICK_UP);
        prendi.setAlias(new String[]{"prendi", "p", "raccogli", "pickup"});
        getComandi().add(prendi);

        Comandi database = new Comandi(ComandiTipo.DATABASE);
        database.setAlias(new String[]{"db", "database", "classifica"});
        getComandi().add(database);

        Comandi usa = new Comandi(ComandiTipo.USE);
        usa.setAlias(new String[]{"usa", "u", "utilizza", "use"});
        getComandi().add(usa);

        Comandi inventario = new Comandi(ComandiTipo.Inventario);
        inventario.setAlias(new String[]{"inventario", "i"});
        getComandi().add(inventario);

        Comandi indossa = new Comandi(ComandiTipo.WEAR);
        indossa.setAlias(new String[]{"indossa", "i", "metti", "infila", "wear"});
        getComandi().add(indossa);

        Comandi esci = new Comandi(ComandiTipo.EXIT);
        esci.setAlias(new String[]{"esci", "exit", "quit", "abbandona", "esc"});
        getComandi().add(esci);

        Comandi salva = new Comandi(ComandiTipo.SAVE);
        salva.setAlias(new String[]{"salva", "save"});
        getComandi().add(salva);

        Comandi carica = new Comandi(ComandiTipo.LOAD);
        carica.setAlias(new String[]{"carica", "load"});
        getComandi().add(carica);

        // stanza 1
        Stanza partenza = new Stanza(1, "Cortile principale",
                "Ora sei all'interno del castello. Ti trovi all'ingresso principale, dovrai fare molta attenzione!!.",
                70);
        partenza.setGuarda(
                "Sei all'ingresso del castello, scegli la direzione per continuare la tua avventura,\n fai attenzione a scegliere la strada giusta, per poter uscirne illeso. \n È chiaro che l' avventura che stai per intraprendere è appena iniziata.");
        getStanza().add(partenza);
        // stanza 2
        Stanza saladellearmature = new Stanza(2, "Sala delle Armature", "Sei arrivato nella Sala delle Armature");
        saladellearmature.setGuarda(
                "Guarda!! Un brivido ti corre lungo la schiena mentre ammiri la maestosit\u00e0 di questi antichi manufatti,\n"
                + "scorgi che ci sono due oggetti che ti potranno servire e ti proteggeranno \n"
                + "se li prendi avrai pi\u00f9 vite per salvate Erik \n"
                + "c'\u00e8 una spada da utilizzare e una tuta da indossare");
        getStanza().add(saladellearmature);
        // stanza 3
        Stanza saladeglielfi = new Stanza(3, " Sala degli Elfi", "Sei arrivato nella Sala degli Elfi.", 10);
        saladeglielfi.setGuarda(
                "Elfo: Hey tu, che ci fai qui?? \nTU:Sono qui per portare in salvo il mio amico! \nElfo: Avrai sbagliato sicuramente strada, ti consiglio di tornare indietro.. \nTU: Aiutooo!!!Forse mi conviene tornare all'ingresso!\n");
        getStanza().add(saladeglielfi);
        // stanza 4
        Stanza saladeisegreti = new Stanza(4, "Sala dei Segreti", "Sei nella Sala dei Segreti!!", 35);
        saladeisegreti.setGuarda("Le voci sussurrano che questa stanza nasconda segreti dimenticati e poteri arcani,\n"
                + "qui \u00e8 nascosta la chiave che aprir\u00e0 la porta della prigione.\n"
                + "Prendila e usala appena varcherai di fronte alla maestosa porta della prigione.");
        getStanza().add(saladeisegreti);
        // stanza 5
        Stanza prigione = new Stanza(5, "Prigione", "Sei arrivato alla prigione!!");
        prigione.setGuarda("Qui c'\u00e8 il tuo amico che ti aspetta gi\u00e0 da un po'! \n"
                + "Non vede l'ora di essere nuovamente libero, \u00e8 il momento di usare la chiave,\n"
                + "far uscire il tuo amico e scappare insieme!");
        getStanza().add(prigione);
        // stanza 6
        Stanza saladellevipere = new Stanza(6, "Sala delle Vipere", "Sei arrivato nella Sala delle Vipere", 10);
        saladellevipere.setGuarda("Tutta la stanza \u00e8 ricoperta da creature striscianti che sembrano seguirti...\n"
                + "mentre il loro veleno potrebbe essere letale se non presti attenzione \nesci il prima possibile da questa stanza ");
        getStanza().add(saladellevipere);
        // stanza 7
        Stanza uscitadalcastello = new Stanza(12, "Uscita dal castello", "Sei uscita dal castello senza aiutare il tuo amico!!!");
        uscitadalcastello.setGuarda(
                "Ti trovi all'uscita sul retro del castello, se pensi di non potercela fare esci dal gioco \ne la tua avventura terminerà, altrimenti torna indietro in fretta \nPRIMA CHE FINISCE IL TEMPO!!!");
        getStanza().add(uscitadalcastello);
        // coridoio 1
        Stanza corridoio1 = new Stanza(7, "Corridoio n.1", "Sei nel corridoio '1', in che direzione vuoi proseguire?!");
        corridoio1.setGuarda(
                "Da questo corridoio potrai decidere il tuo destino, \npuoi proseguire verso destra, la strada più sicura \no verso sinistra...");
        getStanza().add(corridoio1);
        // coridoio 2
        Stanza corridoio2 = new Stanza(8, "Corridoio n.2", "Sei nel corridoio 2");
        corridoio2.setGuarda(
                "Da qui puoi decidere se entare in una nuova stanza e trovare altri oggetti utili o tornare in dietro.");
        getStanza().add(corridoio2);
        // coridoio 3
        Stanza corridoio3 = new Stanza(9, "Corridoio n.3", "Sei nel corridoio 3");
        corridoio3.setGuarda(
                "Guarda, sei molto vicino al tuo obiettivo, non tornare \nindietro proprio ora che sei quasi sul punto di salvare il tuo amico.\nContinua sulla stessa direzione e non te ne pentirai!!");
        getStanza().add(corridoio3);
        // coridoio 4
        Stanza corridoio4 = new Stanza(10, "Corridoio n.4", "Sei nel corridoio 4, da quale direzione vuoi andare???");
        corridoio4.setGuarda("Di fronte a te c'è una stanza molto pericolosa sarebbe ideale che tu tornassi indietro");
        getStanza().add(corridoio4);
        // coridoio 5
        Stanza corridoio5 = new Stanza(11, "Corridoio n.5", "Sei nel corridoio 5");
        corridoio5.setGuarda(
                "Proseguendo ancora verso sud uscirai dal castello... \nDecidi tu se tornare indietro! \nSempre se non è troppo tardi!  ");
        getStanza().add(corridoio5);

        // mappa del gioco
        partenza.setAdiacenti(null, corridoio1, null, null);
        saladellearmature.setAdiacenti(null, corridoio2, corridoio1, null);
        saladeglielfi.setAdiacenti(null, corridoio4, null, corridoio1);
        corridoio1.setAdiacenti(partenza, null, saladeglielfi, saladellearmature);
        saladeisegreti.setAdiacenti(corridoio2, corridoio3, null, null);
        saladellevipere.setAdiacenti(corridoio4, corridoio5, null, null);
        corridoio2.setAdiacenti(saladellearmature, saladeisegreti, null, null);
        prigione.setAdiacenti(corridoio3, null, null, null);
        corridoio3.setAdiacenti(saladeisegreti, prigione, null, null);
        corridoio4.setAdiacenti(saladeglielfi, saladellevipere, null, null);
        corridoio5.setAdiacenti(saladellevipere, uscitadalcastello, null, null);
        uscitadalcastello.setAdiacenti(corridoio5, null, null, null);

        // oggetti
        Oggetto spada = new Oggetto(1, "Spada", "Aumenta la tua difesa", new String[]{"spada"}, 10);
        saladellearmature.getOggetto().add(spada);
        spada.setPrendibile(true);
        spada.setUtilizzabile(true);

        Oggetto tuta = new Oggetto(2, "Tuta protettiva", "Tuta protettiva, ti salverà dagli attacchi",
                new String[]{"tuta", "tuta protettiva"}, 10);
        saladellearmature.getOggetto().add(tuta);
        tuta.setPrendibile(true);
        tuta.setIndossabile(true);

        Oggetto chiave = new Oggetto(8, "chiave", "Questa chiave ti servirà per aprire la porta della prigione",
                new String[]{"chiavi"}, 10);
        saladeisegreti.getOggetto().add(chiave);
        chiave.setPrendibile(true);
        chiave.setUtilizzabile(true);

        // percentuale di partenza a inizio gioco
        setPercentualeTotale(70);
        aumentoPercentuale = false;
        // Set "partenza" come stanza di partenza a inizio
        setStanzaCorrente(partenza);

    }

    public List<Stanza> getStanza() {
        return stanza;
    }

    public List<Comandi> getComandi() {
        return comandi;
    }

    public void setComandi(List<Comandi> comandi) {
        this.comandi = comandi;
    }

    public Stanza getStanzaCorrente() {
        return stanzaCorrente;
    }

    public void setStanzaCorrente(Stanza stanzaCorrente) {
        this.stanzaCorrente = stanzaCorrente;
    }

    public List<Oggetto> getInventario() {
        return inventario;
    }

    public void setInventario(List<Oggetto> inventario) {
        this.inventario = inventario;
    }

    public int getPercentualeTotale() {
        return percentualeTotale;
    }

    public void setPercentualeTotale(int percentualeTotale) {
        this.percentualeTotale = percentualeTotale;
    }

    public Timer getTempo() {
        return tempo;
    }

    public void setTempo(Timer tempo) {
        this.tempo = tempo;
    }

    public abstract boolean prossimaMossa(ParserOutput p, DescrizioneGioco gioco)
            throws IOException, ClassNotFoundException;

    public abstract void percentualeStampa(int percentualeTotale);

    public void nextMove(ParserOutput p, PrintStream out) {
    }

    public void init() {
    }

    public void setTempo(tipo.Timer object) {

    }

}
