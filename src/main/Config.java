package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    
    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

            if (gp.fullScreenOn == true) {
                bw.write("On");
            }
            if (gp.fullScreenOn == false) {
                bw.write("Off");
            }
            bw.newLine();

            // Music volume

            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            // SE volume

            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

            // Save existance

            bw.write(String.valueOf(gp.saveExists));
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String s = br.readLine();

            if (s != null) {
                if (s.equals("On")) {
                    gp.fullScreenOn = true;
                } else {
                    gp.fullScreenOn = false;
                }
            }

            // Music volume

            s = br.readLine();
            if (s != null) {
                gp.music.volumeScale = Integer.parseInt(s);
            }

            // SE volume

            s = br.readLine();

            if (s != null) {
                gp.se.volumeScale = Integer.parseInt(s);
            }

            // Save existance

            s = br.readLine();
            if (s != null) {
                if (s.equals("false")) {
                    gp.saveExists = false;
                } else {
                gp.saveExists = true;
                }
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
