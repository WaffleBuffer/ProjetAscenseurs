package Options;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
 
/**
 * Jouer un son au format wav
 * @author Julien
 */
public class JouerFichierWAV extends Thread {
 
    private String cheminDuFichier;
    private Position positionActuelle;
    private final int TAILLE_BUFFER_EXTERNE = 524288; // 128Kb
    enum Position {GAUCHE, DROITE, NORMAL};
 
    public JouerFichierWAV(String fichierWAV) {
        cheminDuFichier = fichierWAV;
        positionActuelle = Position.NORMAL;
    }
 
    public void run() {
        File fichier = new File(cheminDuFichier);
        if (!fichier.exists()) {
            System.err.println("Wave file not found: " + cheminDuFichier);
            return;
        }
 
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(fichier);
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
            return;
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
 
        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
 
        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
 
        if (auline.isControlSupported(FloatControl.Type.PAN)) {
            FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
            if (positionActuelle == Position.DROITE) {
                pan.setValue(1.0f);
            } else if (positionActuelle == Position.GAUCHE) {
                pan.setValue(-1.0f);
            }
        }
 
        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[TAILLE_BUFFER_EXTERNE];
 
        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    auline.write(abData, 0, nBytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            auline.drain();
            auline.close();
        }
    }//run()
} 