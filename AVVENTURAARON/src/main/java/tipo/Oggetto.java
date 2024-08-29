package tipo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Classe che rappresenta un oggetto nel gioco, con attributi e azioni possibili
public class Oggetto implements Serializable {

    private final int id;
    private String nome;
    private String descrizione;
    private Set<String> alias;
    private int percentualeVitale;
    private boolean apribile = false;
    private boolean apri = false;
    private boolean utilizzabile = false;
    private boolean utilizza = false;
    private boolean indossabile = false;
    private boolean indossa = false;
    private boolean prendibile = false;

    // Costruttore
    public Oggetto(int id) {
        this.id = id;
    }

    public Oggetto(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Oggetto(int id, String nome, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public Oggetto(int id, String nome, String descrizione, String[] alias) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    public Oggetto(int id, String nome, String descrizione, String[] alias, int percentualeVitale) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.alias = new HashSet<>(Arrays.asList(alias));
        this.percentualeVitale = percentualeVitale;
    }

    // Attributi di oggetto
    public Oggetto(String nome2, boolean b) {
        this.id = 2;

    }

    public int getId() {
        return id;
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

    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    public int getPercentualeVitale() {
        return percentualeVitale;
    }

    public void setPercentualeVitale(int percentualeVitale) {
        this.percentualeVitale = percentualeVitale;
    }

    // Azioni applicabili agli oggetti
    public boolean isApribile() {
        return apribile;
    }

    public void setApribile(boolean apribile) {
        this.apribile = apribile;
    }

    public boolean isApri() {
        return apri;
    }

    public void setApri(boolean apri) {
        this.apri = apri;
    }

    public boolean isUtilizzabile() {
        return utilizzabile;
    }

    public void setUtilizzabile(boolean utilizzabile) {
        this.utilizzabile = utilizzabile;
    }

    public boolean isUtilizza() {
        return utilizza;
    }

    public void setUtilizza(boolean utilizza) {
        this.utilizza = utilizza;
    }

    public boolean isIndossabile() {
        return indossabile;
    }

    public void setIndossabile(boolean indossabile) {
        this.indossabile = indossabile;
    }

    public boolean isIndossa() {
        return indossa;
    }

    public void setIndossa(boolean indossa) {
        this.indossa = indossa;
    }

    public boolean isPrendibile() {
        return prendibile;
    }

    public void setPrendibile(boolean prendibile) {
        this.prendibile = prendibile;
    }

}
