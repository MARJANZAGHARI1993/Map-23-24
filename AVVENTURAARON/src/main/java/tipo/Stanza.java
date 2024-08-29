package tipo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Classe che rappresenta una stanza nel gioco, con descrizione, oggetti e stanze adiacenti
public class Stanza implements Serializable {

    private final int id;
    private String nome;
    private String descrizione;
    private String guarda;
    private int percentualeVitale;
    private boolean accessibile = true;
    private Stanza sud = null;
    private Stanza nord = null;
    private Stanza est = null;
    private Stanza ovest = null;
    private final List<Oggetto> oggetto = new ArrayList<>();
    private List<Oggetto> oggetti;

    // Costruttore
    public Stanza(int id, String nome, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.percentualeVitale = 0;
    }

    public Stanza(int id, String nome, String descrizione, int percentualeVitale) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.percentualeVitale = percentualeVitale;
    }

    // Attributi di Stanza
    public int getId() {
        return id;
    }

    public List<Oggetto> getOggetti() {
        return oggetti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getPercentualeVitale() {
        return percentualeVitale;
    }

    public void setPercentualeVitale(int percentualeVitale) {
        this.percentualeVitale = percentualeVitale;
    }

    public boolean isAccessibile() {
        return accessibile;
    }

    public void setAccessibile(boolean accessibile) {
        this.accessibile = accessibile;
    }

    public Stanza getSud() {
        return sud;
    }

    public void setSud(Stanza sud) {
        this.sud = sud;
    }

    public Stanza getNord() {
        return nord;
    }

    public void setNord(Stanza nord) {
        this.nord = nord;
    }

    public Stanza getEst() {
        return est;
    }

    public void setEst(Stanza est) {
        this.est = est;
    }

    public Stanza getOvest() {
        return ovest;
    }

    public void setOvest(Stanza ovest) {
        this.ovest = ovest;
    }

    public List<Oggetto> getOggetto() {
        return oggetto;
    }

    public void setAdiacenti(Stanza nord, Stanza sud, Stanza ovest, Stanza est) {
        this.nord = nord;
        this.sud = sud;
        this.est = est;
        this.ovest = ovest;
    }

    public String getGuarda() {
        return guarda;
    }

    public void setGuarda(String guarda) {
        this.guarda = guarda;
    }

}
