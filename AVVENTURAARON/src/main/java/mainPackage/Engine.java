package mainPackage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import game.Castello;
import parser.Parser;
import parser.ParserOutput;

public class Engine {

    private final Castello gioco;
    private Parser parser;

    public Engine(Castello gioco) {
        this.gioco = gioco;
        parser = new Parser();
    }
    
// Avvia il gioco, accetta e processa i comandi dell'utente, gestendo l'interazione con il parser e l'oggetto gioco
    public void run() throws IOException, ClassNotFoundException, SQLException {
        System.out.println("\n******************************  Sei entrato nel gioco  *********************************");
        System.out.println("\n                               Benvenuto nel Castello               ");
        System.out.println("\n Ti trovi di fronte ad un maestoso castello che si erge fiero e misterioso nella valle.\n"
                + " All'interno delle sue mura, il castello si rivela un labirinto di stanze e corridoi, \n"
                + " ognuna con i propri segreti e pericoli; sarà  il palcoscenico della tua epica impresa. \n"
                + " Dovrai affrontare le sfide che ogni stanza presenta per salvare il tuo caro amico ERIK. \n"
                + "           Il tempo è essenziale!! Il destino del tuo amico dipende solo da te.");
        System.out.println("\n                       TROVA LA STRADA GIUSTA PER AIUTARE ERIK");
        System.out.println("\n*****************************************************************************************");
        System.out.print("\n Digita 'h' per visualizzare i comandi del gioco ");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String comandi = scanner.nextLine();
            System.out.println();
            ParserOutput p = parser.parse(comandi, gioco.getComandi(), gioco.getStanzaCorrente().getOggetto(),
                    gioco.getInventario());
            if (p.getComandi() != null) {
                if (gioco.prossimaMossa(p, gioco)) {
                    gioco.esci();
                }
            } else {
                System.out.println("Comando non valido, digita 'help'");
            }
        }
        scanner.close();
    }
    //Metodo che permette di creare un nome random per l'username del giocatore
    public static String getRandomName() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://randomuser.me/api/"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JSONObject jsonObject = new JSONObject(response.body());
            JSONArray resultsArray = jsonObject.getJSONArray("results");
            JSONObject nameObject = resultsArray.getJSONObject(0).getJSONObject("name");
            return nameObject.getString("first") + " " + nameObject.getString("last");
        } else {
            System.err.println("Errore nella risposta dell'API: " + response.statusCode());
            return null;
        }
    }
}
