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

import entity.Entity;
import obj.OBJ_Arrow;
import obj.OBJ_Heart;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    BufferedImage heart_full, heart_half, heart_empty, arrow;
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
    public int slotCol = 0;
    public int slotRow = 0;
    public int itemIndexOnSlot = 0;
    public int subState = 0;

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

        // HUD OBJECTS
        
        OBJ_Heart heart = new OBJ_Heart(gp);
        heart_empty = heart.image3;
        heart_half = heart.image2;
        heart_full = heart.image;
        Entity arrowEnt = new OBJ_Arrow(gp);
        arrow = arrowEnt.image;
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
            if (gp.player.currentShoot != null) {
                drawPlayerAmmo();
            }
        }

        // PAUSE STATE

        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        // DIALOGUE STATE

        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }

        // CHARACTER STATE

        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory();
        }

        // OPTIONS STATE

        if (gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }

        // GAME OVER STATE
        
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
    }

public void drawInventory() {
    int frameX = gp.tileSize*12;
    int frameY = gp.tileSize;
    int frameWidth = gp.tileSize*6-10;
    int frameHeight = gp.tileSize*6-10;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    final int slotXstart = frameX + 20;
    final int slotYstart = frameY + 20;
    int slotX = slotXstart;
    int slotY = slotYstart;
    int slotXCount = 0;
    @SuppressWarnings("unused")
    int slotYCount = 0;

    for (Entity e : gp.player.inventory) {

        
        if (e == gp.player.currentWeapon || e == gp.player.currentShield || e == gp.player.currentShoot) {
            g2.setColor(new Color(240,190,90));
            g2.fillRoundRect(slotX,slotY,gp.tileSize,gp.tileSize,10,10);
        }
        g2.drawImage(e.image, slotX, slotY, null);
        slotX += gp.tileSize;
        slotXCount++;
        if (slotXCount > 4) {
            slotY += gp.tileSize;
            slotX = slotXstart;
            slotYCount++;
            slotXCount = 0;
        }
    }
    
    int cursorX = slotXstart + (gp.tileSize * slotCol);
    int cursorY = slotYstart + (gp.tileSize * slotRow);
    int cursorWidth = gp.tileSize;
    int cursorHeight = gp.tileSize;
    
    g2.setColor(Color.white);
    g2.setStroke(new BasicStroke(3));
    g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
    
    int dFrameX = frameX;
    int dFrameY = frameY + frameHeight;
    int dFrameWidth = frameWidth;
    int dFrameHeight = gp.tileSize*4;
    
    int textX = dFrameX + 20;
    int textY = dFrameY + 29;
    g2.setFont(g2.getFont().deriveFont(15F));
    int itemIndex = slotCol + (slotRow * 5);
    itemIndexOnSlot = itemIndex;
    if (itemIndex < gp.player.inventory.size()) {
        drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
        String message = gp.player.inventory.get(itemIndex).description;
        for (String line : message.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 20;
        }
        if (gp.player.inventory.get(itemIndex).type == 3 || gp.player.inventory.get(itemIndex).type == 5 || gp.player.inventory.get(itemIndex).type == 9) {
            g2.setFont(consola);
            g2.setFont(g2.getFont().deriveFont(15F));
            g2.drawString("Attack: " + gp.player.inventory.get(itemIndex).attackValue,dFrameX+15,dFrameY+170);
        }
        if (gp.player.inventory.get(itemIndex).type == 4) {
            g2.setFont(consola);
            g2.setFont(g2.getFont().deriveFont(15F));
            g2.drawString("Defense: " + gp.player.inventory.get(itemIndex).defenseValue,dFrameX+15,dFrameY+170);
        }
        if (gp.player.inventory.get(itemIndex).type == 6) {
            g2.setFont(consola);
            g2.setFont(g2.getFont().deriveFont(15F));
            g2.drawString("Healing: " + gp.player.inventory.get(itemIndex).healingValue,dFrameX+15,dFrameY+170);
        }
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

public void drawPlayerAmmo() {
        // DRAW ARROWS

        int x = gp.tileSize/2;
        int y = gp.tileSize*2;
        g2.drawImage(arrow, x, y, null);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        if (gp.player.arrows == 0) {
            g2.setColor(Color.RED);
        }
        g2.drawString("x" + gp.player.arrows, x+3, y+48);
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

    if (Arrays.asList(gp.npc[gp.currentMap][0].dialogues).contains(currentDialogue)) {
        textSize = 20;
    }

    if (gp.ui.currentDialogue.equals("The goddess statue fills you with joy.\nYour health has been replenished.")) {
        textSize = 20;
    }

    if (gp.ui.currentDialogue.equals("You are now level " + gp.player.level + "!\nYou feel stronger than before!")) {
        textSize = 28;
    }

    if (gp.ui.currentDialogue.equals("You drink the Red Potion!\nYour health has been\nreplenished by 4.")) {
        textSize = 30;
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

    final int frameX = gp.tileSize*2;
    final int frameY = gp.tileSize;
    final int frameWidth = gp.tileSize*5;
    final int frameHeight = gp.tileSize*10;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    
    // TEXT

    g2.setColor(Color.white);
    g2.setFont(consola);
    g2.setFont(g2.getFont().deriveFont(21F));
    int textX = frameX + 20;
    int textY = frameY + 33;
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
    g2.drawString("Arrows", textX, textY);
    textY += lineHeight;
    g2.drawString("Gold", textX, textY);
    textY += lineHeight + 15;
    g2.drawString("Weapon:", textX, textY);
    textY += lineHeight + 15;
    g2.drawString("Shield:", textX, textY);

    // VALUES

    int tailX = (frameX + frameWidth) - 30;
    textY = frameY + 33;
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

    value = String.valueOf(gp.player.arrows);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    value = String.valueOf(gp.player.gold);
    textX = getXforAlignToRightText(value, tailX);
    g2.drawString(value, textX, textY);
    textY += lineHeight;

    g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize - 15, textY-20, null);
    textY += gp.tileSize;
    g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize - 15, textY-10, null);
    g2.setFont(ocraext);
}

public void drawGameOverScreen() {

    g2.setColor(new Color(0,0,0,150));
    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

    int x;
    int y;
    String text;
    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

    text = "Game Over";
    g2.setColor(Color.black);
    x = getXforCenteredText(text);
    y = gp.tileSize*4;
    g2.drawString(text, x, y);

    g2.setColor(Color.white);
    g2.drawString(text, x-4, y-4);

    g2.setFont(g2.getFont().deriveFont(50f));
    text = "Retry";
    x = getXforCenteredText(text);
    y += gp.tileSize*4;
    g2.drawString(text, x, y);
    if (commandNumber == 0) {
        g2.drawString(">", x-40, y);
    }

    text = "Quit";
    x = getXforCenteredText(text);
    y += 75;
    g2.drawString(text, x, y);
    if (commandNumber == 1) {
        g2.drawString(">", x-40, y);
    }
}

public void drawOptionsScreen() {

    g2.setColor(Color.white);
    g2.setFont(consola.deriveFont(25F));

    int frameX = gp.tileSize*6;
    int frameY = gp.tileSize;
    int frameWidth = gp.tileSize*8;
    int frameHeight = gp.tileSize*10;
    drawSubWindow(frameX, frameY, frameWidth, frameHeight);

    switch(subState) {
        case 0: options_top(frameX, frameY); break;
        case 1: options_fullScreenNotification(frameX, frameY); break;
        case 2: options_control(frameX, frameY); break;
        case 3: options_endGameConfirmation(frameX, frameY); break;
    }

    gp.keyH.spacePressed = false;
}

public void options_top(int frameX, int frameY) {

    int textX;
    int textY;

    String text = "Options";
    textX = getXforCenteredText(text);
    textY = frameY + gp.tileSize;
    g2.drawString(text, textX, textY);

    // FULLSCREEN ON/OFF

    textX = frameX + gp.tileSize;
    textY += gp.tileSize*2;
    g2.drawString("Fullscreen", textX, textY);
    if (commandNumber == 0) {
        g2.drawString(">", textX-25, textY);
        if (gp.keyH.spacePressed == true) {
            if (gp.fullScreenOn == false) {
                gp.fullScreenOn = true;
            }
            else if (gp.fullScreenOn == true) {
                gp.fullScreenOn = false;
            }
            subState = 1;
        }
    }

    // MUSIC

    textY += gp.tileSize;
    g2.drawString("Music", textX, textY);
    if (commandNumber == 1) {
        g2.drawString(">", textX-25, textY);
    }

    // SE

    textY += gp.tileSize;
    g2.drawString("SE", textX, textY);
    if (commandNumber == 2) {
        g2.drawString(">", textX-25, textY);
    }

    // CONTROLS

    textY += gp.tileSize;
    g2.drawString("Controls", textX, textY);
    if (commandNumber == 3) {
        g2.drawString(">", textX-25, textY);
        if (gp.keyH.spacePressed == true) {
            subState = 2;
            commandNumber = 0;
        }
    }

    // END GAME

    textY += gp.tileSize;
    g2.drawString("End Game", textX, textY);
    if (commandNumber == 4) {
        g2.drawString(">", textX-25, textY);
        if (gp.keyH.spacePressed == true) {
            subState = 3;
            commandNumber = 0;
        }
    }

    // BACK

    textY += gp.tileSize*2;
    g2.drawString("Back", textX, textY);
    if (commandNumber == 5) {
        g2.drawString(">", textX-25, textY);
        if (gp.keyH.spacePressed == true) {
            gp.gameState = gp.playState;
            commandNumber = 0;
        }
    }

    // FULLSCREEN CHECKBOX

    textX = frameX + (int)(gp.tileSize*4.5);
    textY = frameY + gp.tileSize*2 + 24;
    g2.setStroke(new BasicStroke(3));
    g2.drawRect(textX, textY, 24, 24);
    if (gp.fullScreenOn == true) {
        g2.fillRect(textX, textY, 24, 24);
    }

    // MUSIC VOLUME

    textY += gp.tileSize;
    g2.drawRect(textX, textY, 120, 24);
    int volumeWidth = 24 * gp.music.volumeScale;
    g2.fillRect(textX, textY, volumeWidth, 24);

    // SE VOLUME

    textY += gp.tileSize;
    g2.drawRect(textX, textY, 120, 24);
    volumeWidth = 24 * gp.se.volumeScale;
    g2.fillRect(textX, textY, volumeWidth, 24);
    
    gp.config.saveConfig();
}

public void options_fullScreenNotification(int frameX, int frameY) {
    
    int textX = frameX + gp.tileSize-10;
    int textY = frameY + gp.tileSize*3;

    currentDialogue = "The change will take \neffect after restarting\nthe game.";

    for (String line: currentDialogue.split("\n")) {
        g2.drawString(line, textX, textY);
        textY += 40;
    }

    textY = frameY + gp.tileSize*9;
    g2.drawString("Back", textX, textY);
    if (commandNumber == 0) {
        g2.drawString(">", textX-25, textY);
        if (gp.keyH.spacePressed == true) {
            subState = 0;
        }
    }
}

public void options_control(int frameX, int frameY) {

    int textX;
    int textY;

    String text = "Controls";
    textX = getXforCenteredText(text);
    textY = frameY + gp.tileSize;
    g2.drawString(text, textX, textY);

    textX = frameX + gp.tileSize;
    textY += gp.tileSize;
    g2.drawString("Move", textX, textY); textY += gp.tileSize;
    g2.drawString("Attack", textX, textY); textY += gp.tileSize;
    g2.drawString("Shoot", textX, textY); textY += gp.tileSize;
    g2.drawString("Inventory", textX, textY); textY += gp.tileSize;
    g2.drawString("Pause", textX, textY); textY += gp.tileSize;
    g2.drawString("Interact", textX, textY); textY += gp.tileSize;
    g2.drawString("Options", textX, textY); textY += gp.tileSize;

    textX = frameX + gp.tileSize*6;
    textY = frameY + gp.tileSize*2;
    g2.drawString("WASD", textX, textY); textY += gp.tileSize;
    g2.drawString("Q", textX, textY); textY += gp.tileSize;
    g2.drawString("R", textX, textY); textY += gp.tileSize;
    g2.drawString("C", textX, textY); textY += gp.tileSize;
    g2.drawString("P", textX, textY); textY += gp.tileSize;
    g2.drawString("E", textX, textY); textY += gp.tileSize;
    g2.drawString("ESC", textX, textY); textY += gp.tileSize;

    // BACK

    textX = frameX + gp.tileSize;
    textY = frameX + gp.tileSize*4 + 15;
    g2.drawString("Back", textX, textY);
    if (commandNumber == 0) {
        g2.drawString(">", textX-25, textY);
        if (gp.keyH.spacePressed == true) {
            subState = 0;
            commandNumber = 3;
        }
    }
}

public void options_endGameConfirmation(int frameX, int frameY) {

    int textX = frameX + gp.tileSize - 15;
    int textY = frameY + gp.tileSize*3;

    currentDialogue = "Quit the game and\nreturn to title screen?";

    for (String line : currentDialogue.split("\n")) {
        g2.drawString(line, textX, textY);
        textY += 40;
    }

    // YES

    String text = "Yes";
    textX = getXforCenteredText(text);
    textY += gp.tileSize*3;
    g2.drawString(text, textX, textY);
    if (commandNumber == 1) {
        g2.drawString(">", textX-25, textY);
        if (gp.keyH.spacePressed == true) {
            subState = 0;
            gp.gameState = gp.titleState;
            gp.music.stop();
        }
    }

    // NO

    text = "No";
    textX = getXforCenteredText(text);
    textY += gp.tileSize;
    g2.drawString(text, textX, textY);
    if (commandNumber == 0) {
        g2.drawString(">", textX-25, textY);
        if (gp.keyH.spacePressed == true) {
            subState = 0;
            commandNumber = 4;
        }
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

public int getXforAlignToRightText(String text, int tailX) {

    int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    int x = tailX - length;
    return x;
}
}