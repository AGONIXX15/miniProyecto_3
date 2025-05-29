package models.pokemon.utils;
import javax.sound.sampled.*;
import java.io.File;

import javax.sound.sampled.*;
import java.io.File;

public class ReproduceSound {
    private Clip clip;

    public void loadSound(String path) {
        try {
            File fileSound = new File(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileSound);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Control de volumen
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-10.0f); // Baja el volumen
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playSound() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void loopSound() {
        if (clip != null) {
            clip.setFramePosition(0);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-30.0f);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Reproduce sin parar
        }
    }

    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
