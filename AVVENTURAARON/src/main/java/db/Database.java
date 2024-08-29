package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Questa classe implementa il database, permette di salvare il nome e il tempo che impiega per giocare 
public class Database {

    private Connection connessione; // interazione tra il programma ed il DBMS
    private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS utenti (username VARCHAR(30), tempo INT(11))";

    // Costruttore della classe
    public Database() {
        try {
            connesso();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Questo metodo permette la connessione al database.
    public void connesso() throws SQLException {
        connessione = DriverManager.getConnection("jdbc:h2:./castello/ErikeAron");
        Statement stm = connessione.createStatement();
        stm.executeUpdate(CREATE_TABLE);
        stm.close();
        connessione.close();
    }

    // Questo metodo permette la riconnessione al database, nel caso in cui ci fosse una disconnessione.
    public void reconnect() throws SQLException {
        if (connessione != null && !connessione.isValid(0)) {
            connessione = DriverManager.getConnection("jdbc:h2:./castello/ErikeAron");
        }
    }

    // Questo metodo permmette l'inserimento del tempo di gioco di una partita nel database
    public void inserimentoTempo(String nome, int tempo) throws SQLException {
        reconnect();

        PreparedStatement stm = connessione.prepareStatement("SELECT tempo FROM utenti WHERE username = ?");
        stm.setString(1, nome);
        ResultSet result = stm.executeQuery();

        if (result.next()) {
            if (result.getInt(1) > tempo) {

                PreparedStatement dichiarazione = connessione
                        .prepareStatement("UPDATE utenti SET tempo = ? WHERE username = ?");
                dichiarazione.setInt(1, tempo);
                dichiarazione.setString(2, nome);
                dichiarazione.executeUpdate();
                dichiarazione.close();
            } else {
                System.out.println("Il tempo è maggiore di quello già presente");
            }
        } else {
            PreparedStatement dichiarazione = connessione.prepareStatement("INSERT INTO utenti VALUES (?,?)");
            dichiarazione.setString(1, nome);
            dichiarazione.setInt(2, tempo);
            dichiarazione.executeUpdate();
            dichiarazione.close();
        }
        stm.close();
        result.close();
    }

    //Questo metodo permette di classificare gli utenti che hanno impiegato minor tempo nel terminare una partita
    public void punteggioTempo() throws SQLException {
        reconnect();

        Statement stm = connessione.createStatement();
        ResultSet resSet = stm.executeQuery("SELECT username, tempo FROM utenti ORDER BY tempo ASC");
        System.out.println("<****************************>");
        System.out.println("     lista dei giocatori: ");
        System.out.println("<****************************>");

        while (resSet.next()) {
            System.out.println(resSet.getString(1) + " " + resSet.getInt(2));

        }
        resSet.close();
        stm.close();
    }
}
