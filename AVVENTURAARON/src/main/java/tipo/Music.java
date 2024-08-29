package tipo;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// Classe per gestire la riproduzione di musica in un THREAD separato
public class Music implements Runnable {

    private Clip clip;
    private String filePath;

    public Music(String filePath) {
        this.filePath = filePath;
    }

    public void startMusica() {
        try {
            File file = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopMusica() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }

    @Override
    public void run() {
        startMusica();
    }
}
