package game;

public class Menu {

    // Crea un metodo statico che stampa il menu
    public static void printComandi() {

        System.out.println("Lista dei comandi:");
        System.out.println(
                "Nord    --> Vai indritto\n" +
                        "Sud     --> Vai avanti\n" +
                        "Est     --> Vai a destra\n" +
                        "Ovest   --> Vai a sinistra\n" +
                        "Aiuto   --> Mostra la lista dei comandi\n" +
                        "Esci    --> Esce dalla partita in corso\n" +
                        "Guarda  --> Descrive l' ambiente in cui ti trovi\n" +
                        "Prendi  --> Raccoglie l' oggetto presente nella stanza\n" +
                        "Usa     --> Utilizza l' oggetto che possiedi\n" +
                        "Indossa --> Indossa l' oggetto che possiedi\n" +
                        "Inventario     --> visualizzazione dell'inventario\n" +
                        "Classifica --> Stampa la classifica in base al tempo migliore\n" +
                        "Carica     --> carica l'ultima partita salvata\n" +
                        "Salva     --> Salva la partita in corso");

    }
}