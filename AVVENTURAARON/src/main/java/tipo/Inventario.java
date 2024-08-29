package tipo;

import java.util.ArrayList;
import java.util.List;

//classe che rappresenta un inventario con una lista di oggetti e metodi per gestirli
public class Inventario extends Oggetto {

    private List<Oggetto> lista = new ArrayList<>();

    // Costruttore
    public Inventario(int id) {
        super(id);
    }

    public Inventario(int id, String nome) {
        super(id, nome);
    }

    public Inventario(int id, String nome, String descrizione) {
        super(id, nome, descrizione);
    }

    public Inventario(int id, String nome, String descrizione, String[] alias) {
        super(id, nome, descrizione, alias);
    }

    // Metodi e Attributi dell'inventario
    public List<Oggetto> getLista() {
        return lista;
    }

    public void setLista(List<Oggetto> list) {
        this.lista = list;
    }

    public void aggiungi(Oggetto o) {
        lista.add(o);
    }

    public void rimuovi(Oggetto o) {
        lista.remove(o);
    }

}
