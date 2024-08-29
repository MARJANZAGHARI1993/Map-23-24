package tipo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// metodi e attributi dei comandi.
public class Comandi implements Serializable {

    private final ComandiTipo tipo;
    private String nome;
    private Set<String> alias;

    // Costruttore di Comandi con tipo
    public Comandi(ComandiTipo tipo) {
        this.tipo = tipo;
    }

    // Costruttore di Comandi con tipo e nome
    public Comandi(ComandiTipo tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
    }

    // Costruttore di Comandi con tipo, nome e alias
    public Comandi(ComandiTipo tipo, String nome, Set<String> alias) {
        this.tipo = tipo;
        this.nome = nome;
        this.alias = alias;
    }

    public String getNome() {
        return nome;
    }

    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    public ComandiTipo getTipo() {
        return tipo;
    }

}
