package parser;

import tipo.Comandi;
import tipo.Oggetto;

// Metodi che elaborano l' output del parser.
public class ParserOutput {

    private Comandi comandi;
    private Oggetto oggetto;
    private Oggetto invOggetto;

    public ParserOutput(Comandi comandi, Oggetto oggetto) {
        this.comandi = comandi;
        this.oggetto = oggetto;
    }

    public ParserOutput(Comandi comandi, Oggetto oggetto, Oggetto invOggetto) {
        this.comandi = comandi;
        this.oggetto = oggetto;
        this.invOggetto = invOggetto;
    }

    public Comandi getComandi() {
        return comandi;
    }

    public void setComandi(Comandi comandi) {
        this.comandi = comandi;
    }

    public Oggetto getOggetto() {
        return oggetto;
    }

    public void setOggetto(Oggetto oggetto) {
        this.oggetto = oggetto;
    }

    public Oggetto getInvOggetto() {
        return invOggetto;
    }

    public void setInvOggetto(Oggetto invOggetto) {
        this.invOggetto = invOggetto;
    }

}
