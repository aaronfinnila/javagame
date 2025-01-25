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
    Font arial_40, arial_80B, arial_50B, ocraext, consola;
    public boolean messageOn = false;
    public boolean goldMessageOn = false;
    public boolean expMessageOn = false;
    public boolean damagePitDraw = false;
    public String expMessage = "";
    public String goldMessage = "";
    public String message = "";
    public int expMessageCounter;
    public int goldMessageCounter;
    public int messageCounter;
    public String currentDialogue = "";
    public int commandNumber = 0;

    public UI(GamePanel gp) {

        this.gp = gp;

        InputStream is = getClass().getResourceAsStream("/res/font/OCRAEXT.TTF");
        InputStream is2 = getClass().getResourceAsStream("/res/font/CONSOLA.TTF");
        try {
            ocraext = Font.createFont(Font.TRUETYPE_FONT, is);
            consola = Font.createFont(Font.TRUETYPE_FONT, is2);
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

    public void showExpMessage(String text) {
    
        expMessageOn = true;
        expMessage = text;
    }

    public void showGoldMessage(String text) {

        goldMessageOn = true;
        goldMessage = text;
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
                g2.setColor(Color.black);
                g2.drawString(message, x+2, y+2);
                g2.setColor(Color.white);
                g2.drawString(message, x, y);
                messageCounter++;
            }
            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
            if (expMessageOn == true) {
                int x = 15;
                int y = gp.tileSize*10;
                g2.setFont(consola);
                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 23F));
                g2.setColor(Color.black);
                g2.drawString(expMessage, x+2, y+2);
                g2.setColor(Color.white);
                g2.drawString(expMessage, x, y);
                expMessageCounter++;
                if (expMessageCounter > 120) {
                    expMessageCounter = 0;
                    expMessageOn = false;
                }
            }
            if (goldMessageOn == true) {
                int x = 15;
                int y = gp.tileSize*11;
                g2.setFont(consola);
                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 23F));
                g2.setColor(Color.black);
                g2.drawString(goldMessage, x+2, y+2);
                g2.setColor(Color.white);
                g2.drawString(goldMessage, x, y);
                goldMessageCounter++;
                if (goldMessageCounter > 120) {
                    goldMessageCounter = 0;
                    goldMessageOn = false;
                }
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

        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
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

    // CONDITIONAL FONT SIZE

    if (Arrays.asList(gp.npc[1].dialogues).contains(currentDialogue)) {
        textSize = 20;
    }

    if (currentDialogue == "The goddess statue fills you with joy.\nYour health has been replenished.") {
        textSize = 20;
    }

    if (gp.ui.currentDialogue.equals("You are now level " + gp.player.level + "!\nYou feel stronger than before!")) {
        textSize = 28;
    }

    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, textSize));
    x += gp.tileSize - 5;
    y += gp.tileSize;

    for (String line : currentDialogue.split("\n")) {
        g2.drawString(line, x, y);
        y += 40;
    }
}

public void drawCharacterScreen() {

    // CREATE A FRAME

    final int frameX = gp.tileSize;
    final int frameY = gp.tileSize;
    final int frameWidth = gp.tileSize*5;
    final int frameHeight = gp.tileSize*10;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    
    // TEXT

    g2.setColor(Color.white);
    g2.setFont(consola);
    g2.setFont(g2.getFont().deriveFont(21F));
    int textX = frameX + 20;
    int textY = frameY + gp.tileSize;
    final int lineHeight = 35;

    // NAMES

    g2.drawString("Level", textX, textY);
    textY += lineHeight;
    g2.drawString("Health", textX, textY);
    textY += lineHeight;
    g2.drawString("Strength", textX, textY);
    textY += lineHeight;
    g2.drawString("Dexterity", textX, textY);
    textY += lineHeight;
    g2.drawString("Attack", textX, textY);
    textY += lineHeight;
    g2.drawString("Defense", textX, textY);
    textY += lineHeight;
    g2.drawString("Exp", textX, textY);
    textY += lineHeight;
    g2.drawString("Next Level", textX, textY);
    textY += lineHeight;
    g2.drawString("Gold", textX, textY);
    textY += lineHeight + 20;
    g2.drawString("Weapon:", textX, textY);
    textY += lineHeight + 15;
    g2.drawString("Shield:", textX, textY);

    // VALUES

    int tailX = (frameX + frameWidth) - 30;
    textY = frameY + gp.tileSize;
    String value;

    value = String.valueOf(gp.player.level);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.maxHealth);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.strength);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.dexterity);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.attack);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.defense);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.exp);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.nextLevelExp);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.gold);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize - 15, textY-15, null);
    textY += gp.tileSize;
    g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize - 15, textY-5, null);
    g2.setFont(ocraext);
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

public int getXforAlignToRightText(String text, int tailX) {

    int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    int x = tailX - length;
    return x;
}
}