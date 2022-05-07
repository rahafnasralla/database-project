package main.project;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class music {
    void playMusic(String MusicLocation) {
        try {
            File musicpath = new File(MusicLocation);
            if (musicpath.exists())
            {
                AudioInputStream input = AudioSystem.getAudioInputStream(musicpath);
                Clip clip = AudioSystem.getClip();
                clip.open(input);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}



