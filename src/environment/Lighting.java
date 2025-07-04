package environment;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Lighting {

    GamePanel gp;
    BufferedImage darknessFilter;
    int dayCounter;
    public float filterAlpha = 0f;

    // DAY STATE

    public final int day = 0;
    public final int dusk = 1;
    public final int night = 2;
    public final int dawn = 3;
    public int dayState = day;

    public Lighting(GamePanel gp) {
        
        this.gp = gp;
        setLightSource();
    }
    
    public void setLightSource() {

        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();

        int centerX = gp.player.screenX + (gp.tileSize)/2;
        int centerY = gp.player.screenY + (gp.tileSize)/2;
            
        Color color[] = new Color[12];
        float fraction[] = new float[12];
            
        color[0] = new Color(0,0,0.1f,0.1f);
        color[1] = new Color(0,0,0.1f,0.42f);
        color[2] = new Color(0,0,0.1f,0.52f);
        color[3] = new Color(0,0,0.1f,0.61f);
        color[4] = new Color(0,0,0.1f,0.69f);
        color[5] = new Color(0,0,0.1f,0.76f);
        color[6] = new Color(0,0,0.1f,0.82f);
        color[7] = new Color(0,0,0.1f,0.87f);
        color[8] = new Color(0,0,0.1f,0.91f);
        color[9] = new Color(0,0,0.1f,0.94f);
        color[10] = new Color(0,0,0.1f,0.96f);
        color[11] = new Color(0,0,0.1f,1f);
            
        fraction[0] = 0f;
        fraction[1] = 0.4f;
        fraction[2] = 0.5f;
        fraction[3] = 0.6f;
        fraction[4] = 0.65f;
        fraction[5] = 0.7f;
        fraction[6] = 0.75f;
        fraction[7] = 0.8f;
        fraction[8] = 0.85f;
        fraction[9] = 0.9f;
        fraction[10] = 0.95f;
        fraction[11] = 1f;

        if (gp.currentMap == 5) {
            if (gp.player.currentLight == null) {
                g2.setColor(new Color(0, 0, 0, 1f));
            } else {
                RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius/2, 
                fraction, color);
                g2.setPaint(gPaint);
            }
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            g2.dispose();
        } else if (gp.currentMap != 0 && gp.currentMap != 1) {
            g2.dispose();
        } else {
            if (gp.player.currentLight != null) {
                RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius/2, 
                fraction, color);
                g2.setPaint(gPaint);
            } else {
            
                RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, 1, 
                fraction, color);
            
                g2.setPaint(gPaint);
            }
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            g2.dispose();
        }
    }

    public void resetDay() {
        dayState = day;
        filterAlpha = 0f;
    }

    public void update() {

        if (gp.player.lightUpdated == true) {
            setLightSource();
            gp.player.lightUpdated = false;
        }

        if (dayState == day) {
            dayCounter++;

            if (dayCounter > 600) {
                dayState = dusk;
                dayCounter = 0;
                if (gp.currentMap == gp.introislandMap || gp.currentMap == gp.treasureislandMap) {
                    gp.stopMusic();
                }
            }
        }
        
        if (dayState == dusk) {
            filterAlpha += 0.001f;

            if (filterAlpha > 1f) {
                filterAlpha = 1f;
                dayState = night;
                if (gp.currentMap == gp.introislandMap || gp.currentMap == gp.treasureislandMap) {
                    gp.changeMusic(21);
                }
            }
        }

        if (dayState == night) {
            dayCounter++;

            if (dayCounter > 600) {
                dayState = dawn;
                dayCounter = 0;
                if (gp.currentMap == gp.treasureislandMap || gp.currentMap == gp.introislandMap) {
                    gp.stopMusic();
                }
            }
        }

        if (dayState == dawn) {
            filterAlpha -= 0.001f;

            if (filterAlpha < 0f) {
                filterAlpha = 0;
                dayState = day;
                if (gp.currentMap == gp.treasureislandMap) {
                    gp.changeMusic(17);
                } else if (gp.currentMap == gp.introislandMap) {
                    gp.changeMusic(0);
                } else {}
        }
    }
    }

    public void draw(Graphics2D g2) {

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        g2.drawImage(darknessFilter, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // DAYSTATE DEBUG
        
/*         String state = "";

        switch (dayState) {
            case day: {state = "Day"; break;}
            case dusk: {state = "Dusk"; break;}
            case night: {state = "Night"; break;}
            case dawn: {state = "Dawn"; break;}
        }
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(50f));
        g2.drawString(state, gp.screenWidth-gp.tileSize*3, gp.screenHeight-gp.tileSize*3);
        String filt = Float.toString(filterAlpha);
        g2.drawString(filt, gp.screenWidth-gp.tileSize*3, gp.screenHeight-gp.tileSize*2); */
    }
}
