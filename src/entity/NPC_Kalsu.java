package entity;

import java.awt.Graphics2D;

import main.GamePanel;
import obj.OBJ_Bow_Default;

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
    dialogues[0] = "Vihree kuula";
    dialogues[1] = "Elos vaan kale";
    dialogues[2] = "Ota tämmöne";
}

public void setAction() {}

public void speak() {
    if (dialogues[dialogueIndex] == null) {
        dialogueIndex = 0;
    }

    gp.ui.currentDialogue = dialogues[dialogueIndex];
    dialogueIndex++;

    if (dialogueIndex == 3 && usedObject == false) {
        gp.obj[gp.currentMap][0] = new OBJ_Bow_Default(gp);
        gp.obj[gp.currentMap][0].worldX = 50*gp.tileSize;
        gp.obj[gp.currentMap][0].worldY = 50*gp.tileSize;
        usedObject = true;
    }
}

@Override
public void draw(Graphics2D g2) {

    image = down1;
    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    g2.drawImage(image, screenX, screenY, null);
    }
}