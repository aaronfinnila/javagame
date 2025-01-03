package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import obj.OBJ_Heart;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    BufferedImage heart_full, heart_half, heart_empty;
    Font arial_40, arial_80B, arial_50B, ocraext;
    public boolean messageOn = false;
    public boolean damagePitDraw = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";
    public int commandNumber = 0;

    public UI(GamePanel gp) {

        this.gp = gp;

        InputStream is = getClass().getResourceAsStream("/res/font/OCRAEXT.TTF");
        try {
            ocraext = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // HUD HEARTS
        
        OBJ_Heart heart = new OBJ_Heart(gp);
        heart_empty = heart.image3;
        heart_half = heart.image2;
        heart_full = heart.image;
    }

    public void showMessage(String text) {

        messageOn = true;
        message = text;
    }
    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(ocraext);
        g2.setColor(Color.white);


        // TITLE STATE

        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // PLAY STATE

        if (gp.gameState == gp.playState) {
            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
                int x = getXforCenteredText(message);
                int y = gp.screenHeight/4;
                g2.drawString(message, x, y);
                messageCounter++;
            }
            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }

            drawPlayerLife();
        }

        // PAUSE STATE

        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        // DIALOGUE STATE

        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }

public void drawPlayerLife() {

    int x = gp.tileSize/2;
    int y = gp.tileSize/2;
    int i = 0;

    // DRAW MAX HEALTH

    while (i < gp.player.maxHealth/2) {
        g2.drawImage(heart_empty, x, y, null);
        i++;
        x += gp.tileSize;
    }


    // RESET

    x = gp.tileSize/2;
    y = gp.tileSize/2;
    i = 0;

    // DRAW CURRENT HEALTH

    while (i < gp.player.health) {
        g2.drawImage(heart_half, x, y, null);
        i++;
        if (i < gp.player.health) {
            g2.drawImage(heart_full, x, y, null);
        }
        i++;
        x += gp.tileSize;
    }
}

public void drawTitleScreen() {

    // BACKGROUND

    g2.setColor(new Color(40, 100, 40));
    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

    // TITLE NAME

    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
    String text = "Rilk";
    int x = getXforCenteredText(text);
    int y = gp.tileSize * 3;

    // SHADOW

    g2.setColor(Color.black);
    g2.drawString(text, x+5, y+5);

    // MAIN COLOR

    g2.setColor(Color.white);
    g2.drawString(text, x, y);

    // CHARACTER IMAGE

    x = gp.screenWidth / 2 - (gp.tileSize)/2 - 30;
    y += gp.tileSize*1 + 10;
    g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

    // MENU

    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
    text = "NEW GAME";
    x = gp.screenWidth / 2 - (gp.tileSize*2) - 15;
    y = gp.screenHeight - (gp.tileSize * 4);

    g2.setColor(Color.black);
    g2.drawString(text, x+3, y+3);
    g2.setColor(Color.white);
    g2.drawString(text, x, y);
    if (commandNumber == 0) {
        g2.drawString(">", x-gp.tileSize, y);
    }

    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
    text = "LOAD GAME";
    x = gp.screenWidth / 2 - (gp.tileSize*2) - 15;
    y = gp.screenHeight - (gp.tileSize * 3);
    g2.setColor(Color.black);
    g2.drawString(text, x+3, y+3);
    g2.setColor(Color.white);
    g2.drawString(text, x, y);
    if (commandNumber == 1) {
        g2.drawString(">", x-gp.tileSize, y);
    }


    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
    text = "QUIT";
    x = gp.screenWidth / 2 - (gp.tileSize*2) - 15;
    y = gp.screenHeight - (gp.tileSize * 2);
    g2.setColor(Color.black);
    g2.drawString(text, x+3, y+3);
    g2.setColor(Color.white);
    g2.drawString(text, x, y);
    if (commandNumber == 2) {
        g2.drawString(">", x-gp.tileSize, y);
    }
    }
    
public void drawPauseScreen() {

    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
    String text = "PAUSED";
    int x = getXforCenteredText(text);
    int y = gp.screenHeight/6;
    drawSubWindow(-100, -100, gp.screenWidth + gp.tileSize*3, gp.screenWidth + gp.tileSize*3);
    g2.drawString(text, x, y);
}

public void drawDialogueScreen() {
     
    // WINDOW

    int x = gp.tileSize * 2;
    int y = gp.tileSize/2;
    int width = gp.screenWidth - (gp.tileSize * 4);
    int height = gp.tileSize * 3 + 5;
    int textSize = 35;

    drawSubWindow(x, y, width, height);

    // NPC SPECIFIC FONT SIZE

    if (Arrays.asList(gp.npc[1].dialogues).contains(currentDialogue)) {
        textSize = 20;
    }

    if (currentDialogue == "The goddess statue fills you with joy.\nYour health has been replenished.") {
        textSize = 20;
    }

    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, textSize));
    x += gp.tileSize - 5;
    y += gp.tileSize;

    for (String line : currentDialogue.split("\n")) {
        g2.drawString(line, x, y);
        y += 40;
    }
}

public void drawSubWindow(int x, int y, int width, int height) {

    Color c = new Color(0, 0, 0, 220);
    g2.setColor(c);
    g2.fillRoundRect(x, y, width, height, 35, 35);

    c = new Color(255, 255, 255);
    g2.setColor(c);
    g2.setStroke(new BasicStroke(5));
    g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
}

public int getXforCenteredText(String text) {

    int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    int x = gp.screenWidth/2 - length/2;
    return x;
}
}