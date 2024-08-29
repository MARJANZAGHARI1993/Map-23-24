package tipo;

public class Chiave {

    private int id;
    private String nome;
    private String descrizione;
    private boolean usato;

    // Costruttore della classe
    public Chiave(int id, String nome, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.usato = false;
    }

    // ottiene e imposta i metodi
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isUsato() {
        return usato;
    }

    public void setUsato(boolean usato) {
        this.usato = usato;
    }

}
