package Model.sound.playBGM;

import javax.sound.sampled.*;
import java.io.InputStream;

public class playBGM extends Thread {
    private Clip clip;
    private String music;

    public playBGM() {
        music = "/Model/sound/BGM.wav";
    }

    @Override
    public void run() {
        try {
            InputStream source = getClass().getResourceAsStream(music);

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(source);

            clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (Exception e) {
            System.err.println("BGM 재생 오류");
        }
    }
}