package mainPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import game.Castello;
import tipo.Music;

public class Login extends JFrame {

    private JFrame frame;
    private JButton bottoneLogin;
    private JLabel userLabel;
    private JTextField userTextField;
    private JPanel pannello;
    private String username;

    public Login() {
        initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login window = new Login();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //Questa classe implementa l'interfaccia grafica del login del gioco
    public void initialize() {
        frame = new JFrame();
        frame.setTitle("Login");
        pannello = new JPanel();
        bottoneLogin = new JButton("Entra");
        bottoneLogin.setEnabled(true);
        userLabel = new JLabel("Username");
        userLabel.setForeground(Color.white);
        userTextField = new JTextField(20);
        bottoneLogin.setBorder(null);
        setupButtonListener();
        frame.getContentPane().add(pannello, BorderLayout.CENTER);
        pannello.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pannello.add(bottoneLogin);
        pannello.add(userLabel);
        pannello.add(userTextField);

        frame.setBounds(100, 100, 450, 300);
        userLabel.setBounds(100, 50, 80, 25);
        userTextField.setBounds(180, 50, 165, 25);
        bottoneLogin.setBounds(195, 131, 60, 37);

        JLabel sfondo = new JLabel("");
        sfondo.setIcon(new ImageIcon("img/Castello.png"));
        sfondo.setBounds(0, -34, 450, 306);
        pannello.add(sfondo);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        mostraDialogoSceltaMusica();
    }

    private void setupButtonListener() {
        bottoneLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onLoginClicked();
            }
        });
    }
    //Mostra un dialogo per chiedere se riprodurre musica di sottofondo
    //e avvia la riproduzione in un nuovo THREAD se l'utente sceglie "Sì"

    private void mostraDialogoSceltaMusica() {
        SwingUtilities.invokeLater(() -> {
            int risposta = JOptionPane.showConfirmDialog(frame, "Vuoi ascoltare la musica di sottofondo?",
                    "Scelta Musica", JOptionPane.YES_NO_OPTION);
            if (risposta == JOptionPane.YES_OPTION) {
                Music music = new Music("img/castello1.wav");
                Thread musicThread = new Thread(music);
                musicThread.start();
            }
        });
    }

    //Ottiene il nome utente dall'input, genera un nome casuale se vuoto, e mostra errori in caso di fallimento
    private void onLoginClicked() {
        username = userTextField.getText().trim();

        if (username.isEmpty()) {
            try {
                username = Engine.getRandomName();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Errore nel recuperare il nome casuale. Inserisci un nome utente.",
                        "Errore di login",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (username == null || username.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Errore nel recuperare il nome casuale. Inserisci un nome utente.",
                        "Errore di login",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            System.out.println(
                    "*****************************************************************************************");
            System.out.println("                            Nome casuale ottenuto: " + username);
        } else {
            System.out.println(
                    "*****************************************************************************************");
            System.out.println("                           Nome utente inserito: " + username);
        }

        try {
            Castello castello = new Castello(username);
            Engine engine = new Engine(castello);
            frame.setState(JFrame.ICONIFIED); // Minimizza la finestra di login
            engine.run();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Errore durante l'avvio del gioco.", "Errore",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public JButton getBottoneLogin() {
        return bottoneLogin;
    }

    public void setBottoneLogin(JButton bottoneLogin) {
        this.bottoneLogin = bottoneLogin;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Login(String username) {
        this.username = username;
    }

    public String getTestoUser() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
