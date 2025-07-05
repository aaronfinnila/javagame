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
        type = type_npc;
        direction = "still";
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
    dialogues[2] = "Got some nice offers for you!";
}

public void setAction() {}

public void speak() {
    if (dialogues[dialogueIndex] == null) {
        dialogueIndex = 0;
    }
    if (dialogueIndex >= 3) {
        gp.gameState = gp.dialogueState;
    } else {
        gp.gameState = gp.tradeState;
    }

    if (dialogueIndex == 6) {
        gp.ui.storeDiscount = 5;
        dialogues[3] = null;
    }

    
    gp.ui.currentDialogue = dialogues[dialogueIndex];
    dialogueIndex++;
    gp.ui.npc = this;
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

    g2.drawImage(image, screenX, screenY, null);
    }
}