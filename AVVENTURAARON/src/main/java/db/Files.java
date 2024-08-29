package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import mainPackage.DescrizioneGioco;
import tipo.Oggetto;
import tipo.Stanza;

// Questa classe permette il salvataggio e caricamento della partita su file.
public class Files implements Serializable {

    // Questa funzione permette il salvataggio del gioco su file, con i vari
    //progressi effettuati durante la parita(oggetti, tempo e percentuale di vita del giocatore)
    public void salvaFile(File file, DescrizioneGioco gioco) {
        try {
            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            System.out.println("Salvataggio in corso...");

            objectStream.writeObject(gioco.getStanzaCorrente());
            objectStream.writeObject(gioco.getInventario());
            objectStream.writeObject(gioco.getPercentualeTotale());

            objectStream.close();
            fileStream.close();

            System.out.println("Partita salvata correttamente.");
        } catch (IOException e) {
            System.out.println("Impossibile salvare la partita.");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void leggiFile(File file, DescrizioneGioco gioco) throws IOException, ClassNotFoundException {
        FileInputStream fIn = new FileInputStream(file);
        ObjectInputStream objIn = new ObjectInputStream(fIn);

        gioco.setStanzaCorrente((Stanza) objIn.readObject());
        gioco.setInventario((List<Oggetto>) objIn.readObject());
        gioco.setPercentualeTotale((int) objIn.readObject());

        objIn.close();
        fIn.close();
    }

}
