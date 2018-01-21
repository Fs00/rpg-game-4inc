package ittbuonarroti.rpggame.engine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Classe utilizzata per avviare file audio.
 * I file audio devono essere in formato WAV per funzionare.
 */

public class Audio {

    public static final int BGM_LOOP = 99999;

    /**
     * Avvia un file audio con o senza loop (Accetta solo il formato WAV)
     * @param filename nome file audio
     * @param loop numero loop (se maggiore di 0 viene loopato)
     */
    public static Clip playSound(String filename, int loop) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/sound/" + filename).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            if(loop > 0)
                clip.loop(loop);
            clip.start();
            return clip;
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Avvia un file audio senza loop (accetta solo il formato WAV)
     * @param filename nome file audio
     * @return handler Clip
     */
    public static Clip playSound(String filename){
        return playSound(filename,  0);
    }
}
