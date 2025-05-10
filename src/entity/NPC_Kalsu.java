package entity;

import java.awt.Graphics2D;

import main.GamePanel;

public class NPC_Kalsu extends Entity {

    public NPC_Kalsu(GamePanel gp) {

        super(gp);
        speed = 0;
        direction = "still";
        getImage();
        setDialogue();
    }

public void getImage() {
    down1 = setup("/res/npc/kalsu", gp.tileSize, gp.tileSize);
}

public void setDialogue() {
    dialogues[0] = "Yes, my queen...";
    dialogues[1] = "Oh yes, you shall have them all...";
    dialogues[2] = "Oh, it seems we have a guest.";
    dialogues[3] = "Who does he think he is, intruding\nupon our private space?";
    dialogues[4] = "He looks like a foreigner, doesn't\nhe?";
    dialogues[5] = "Another GRAND ADVENTURER, coming\nfor the LEGENDARY TREASURE, huh?";
    dialogues[6] = "Oh, right! Because THIS one will be the one who gets\nit all! SURELY he won't end up like the rest, RIIGHT???";
    dialogues[7] = "And then we will be waiting...\nwon't we, my queen? Oh yes, yes\nwe will...";
}

public void setAction() {}

public void speak() {
    if (dialogues[dialogueIndex] == null) {
        dialogueIndex = 0;
    }

    gp.ui.currentDialogue = dialogues[dialogueIndex];
    dialogueIndex++;
}

@Override
public void draw(Graphics2D g2) {

    image = down1;
    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    g2.drawImage(image, screenX, screenY, null);
    }
}