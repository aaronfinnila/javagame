package entity;

import java.awt.Graphics2D;

import main.GamePanel;
import obj.OBJ_Bow_Default;
import obj.OBJ_Hammer;
import obj.OBJ_Longsword;
import obj.OBJ_Potion_Red;

public class NPC_Michael extends Entity {

    public NPC_Michael(GamePanel gp) {

        super(gp);
        speed = 0;
        direction = "down";
        getImage();
        setDialogue();
        setItems();
    }

public void getImage() {
    down1 = setup("/res/npc/michael", gp.tileSize, gp.tileSize);
}

public void setDialogue() {
    dialogues[0] = "Hello there! Welcome to my store!";
    dialogues[1] = "What would you like today?";
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

public void setItems() {
    inventory.add(new OBJ_Potion_Red(gp));
    inventory.add(new OBJ_Potion_Red(gp));
    inventory.add(new OBJ_Bow_Default(gp));
    inventory.add(new OBJ_Longsword(gp));
    inventory.add(new OBJ_Hammer(gp));
}

@Override
public void draw(Graphics2D g2) {

    image = down1;
    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    g2.drawImage(image, screenX, screenY, null);}
}