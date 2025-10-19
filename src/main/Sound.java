package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[50];
    FloatControl fc;
    int volumeScale = 3;
    float volume;
    GamePanel gp;

    public Sound(GamePanel gp) {
        this.gp = gp;

        soundURL[0] = getClass().getResource("/res/sound/themesong.wav");
        soundURL[1] = getClass().getResource("/res/sound/coin.wav");
        soundURL[2] = getClass().getResource("/res/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/res/sound/fanfare.wav");
        soundURL[4] = getClass().getResource("/res/sound/questionmark.wav");
        soundURL[5] = getClass().getResource("/res/sound/startersword.wav");
        soundURL[6] = getClass().getResource("/res/sound/playerdamage.wav");
        soundURL[7] = getClass().getResource("/res/sound/monsterdamage.wav");
        soundURL[8] = getClass().getResource("/res/sound/monsterdeath.wav");
        soundURL[9] = getClass().getResource("/res/sound/levelup.wav");
        soundURL[10] = getClass().getResource("/res/sound/inventoryclick.wav");
        soundURL[11] = getClass().getResource("/res/sound/arrow.wav");
        soundURL[12] = getClass().getResource("/res/sound/chest.wav");
        soundURL[13] = getClass().getResource("/res/sound/arrowpickup.wav");
        soundURL[14] = getClass().getResource("/res/sound/hammer.wav");
        soundURL[15] = getClass().getResource("/res/sound/itstone1.wav");
        soundURL[16] = getClass().getResource("/res/sound/gameover.wav");
        soundURL[17] = getClass().getResource("/res/sound/firstvillage.wav");
        soundURL[18] = getClass().getResource("/res/sound/house1.wav");
        soundURL[19] = getClass().getResource("/res/sound/house2.wav");
        soundURL[20] = getClass().getResource("/res/sound/store.wav");
        soundURL[21] = getClass().getResource("/res/sound/mysterious.wav");
        soundURL[22] = getClass().getResource("/res/sound/nightingale.wav");
        soundURL[23] = getClass().getResource("/res/sound/doorlocked.wav");
        soundURL[24] = getClass().getResource("/res/sound/defaulttext.wav");
        soundURL[25] = getClass().getResource("/res/sound/rocktext.wav");
        soundURL[26] = getClass().getResource("/res/sound/dooropen.wav");
        soundURL[27] = getClass().getResource("/res/sound/michaeltext.wav");
        soundURL[28] = getClass().getResource("/res/sound/ruberttext.wav");
        soundURL[29] = getClass().getResource("/res/sound/clairetext.wav");
        soundURL[30] = getClass().getResource("/res/sound/kalsutext.wav");
        soundURL[31] = getClass().getResource("/res/sound/dungeonmusic.wav");
        soundURL[32] = getClass().getResource("/res/sound/feartext.wav");
        soundURL[33] = getClass().getResource("/res/sound/percivaltext.wav");
        soundURL[34] = getClass().getResource("/res/sound/house3.wav");
        soundURL[35] = getClass().getResource("/res/sound/sofatext.wav");
    }

    public void setFile(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e) {

        }
    }

    public void play() {

        clip.start();
    }

    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {

        clip.stop();
    }

    public void checkVolume() {

        switch(volumeScale) {
            case 0: volume = -80f; break;
            case 1: volume = -20; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }

        fc.setValue(volume);
    }

    public void fadeMusic(int musicNum, String inOrOut) {
        if (inOrOut.equals("in")) {
            int originalVolume = volumeScale;
            int counter = 0;
            volumeScale = 0;
            gp.changeMusic(musicNum);
            while (true) {
                counter++;
                if (counter == 400) {
                    volumeScale = 0;
                    checkVolume();
                } else if (counter == 800) {
                    volumeScale = 1;
                    checkVolume();
                } else if (counter == 1200) {
                    volumeScale = 2;
                    checkVolume();
                } else if (counter == 1600) {
                    volumeScale = 3;
                    checkVolume();
                } else if (counter == 2000) {
                    volumeScale = 4;
                    checkVolume();
                } else if (counter == 2400) {
                    volumeScale = 5;
                    checkVolume();
                }

                if (volumeScale == originalVolume && counter > 45) {
                    System.out.println("inned ready");
                    break;
                }
            }
        } else if (inOrOut.equals("out")) {
            int originalVolume = volumeScale;
            int counter = 0;
            while (true) {
                counter++;
                if (counter == 400) {
                    volumeScale = 5;
                } else if (counter == 800) {
                    volumeScale = 4;
                    checkVolume();
                } else if (counter == 1200) {
                    volumeScale = 3;
                    checkVolume();
                } else if (counter == 1600) {
                    volumeScale = 2;
                    checkVolume();
                } else if (counter == 2000) {
                    volumeScale = 1;
                    checkVolume();
                } else if (counter == 2400) {
                    gp.stopMusic();
                    volumeScale = originalVolume;
                    checkVolume();
                    System.out.println("outed ready");
                    break;
                }

                if (volumeScale > originalVolume) {
                    volumeScale = originalVolume;
                }
            }
        }
    }
}
