//Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
package parser;

import java.util.List;

import tipo.Comandi;
import tipo.Oggetto;

// Questa classe è in grado di riconoscere il seguente tipo di frasi.
// <comando> <oggetto inventario> // Esempio: "usa chiave."
public class Parser {

    // Controlla se la stringa è presente nella lista dei comandi
    private int controlloComando(String token, List<Comandi> comandi) {
        for (int i = 0; i < comandi.size(); i++) {
            if (comandi.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    // Controlla se la stringa è presente nella lista degli oggetti
    private int controlloOggetto(String token, List<Oggetto> oggetto) {
        for (int i = 0; i < oggetto.size(); i++) {
            if (token.equals(oggetto.get(i).getNome()) || oggetto.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    // Il parser è implementato in modo abbastanza dipendente dalla lingua e riconosce frasi del tipo <azione> <oggetto>.
    public ParserOutput parse(String comandi, List<Comandi> comandiLista, List<Oggetto> oggetto,
            List<Oggetto> inventario) {
        String cmd = comandi.toLowerCase().trim();
        String[] tokens = cmd.split("\\s+|\\'");

        int numeroToken = tokens.length; // Numero totale delle parole
        int i = 0; // Posizione della parola che si sta esaminando

        int inputComando = controlloComando(tokens[i], comandiLista); // Cerca il comando nella lista dei comandi

        if (inputComando > -1) {

            if (numeroToken > i + 1) {

                i++; // Passa alla parola successiva
                int inputOggetto = controlloOggetto(tokens[i], oggetto); // Cerca l' oggetto tra gli oggetti della stanza
                int inputOggInventario = controlloOggetto(tokens[i], inventario); // Cerca l' oggetto tra gli oggetti dell' inventario

                if (inputOggetto > -1) {
                    // Restituisce il comando e l'oggetto
                    return new ParserOutput(comandiLista.get(inputComando), oggetto.get(inputOggetto), null);

                } else if (inputOggInventario > -1) {
                    // Restituisce il comando oggetto inventario
                    return new ParserOutput(comandiLista.get(inputComando), null, inventario.get(inputOggInventario));
                } else {
                    return new ParserOutput(null, null, null);
                }
            } else { // Se c'è solo il comando
                return new ParserOutput(comandiLista.get(inputComando), null, null);
            }
        } else { // Se non c'è il comando
            return new ParserOutput(null, null, null);
        }
    }
}
