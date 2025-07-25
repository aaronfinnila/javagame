package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Claire extends Entity {

    boolean shopInteraction;

    public NPC_Claire(GamePanel gp) {
        
        
        super(gp);
        name = "Claire";
        direction = "right";
        speed = 1;
        type = type_npc;
        getImage();
        setDialogue();
        shopInteraction = false;

        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 30;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getImage() {

    up1 = setup("/res/npc/claire_up_1", gp.tileSize, gp.tileSize);
    up2 = setup("/res/npc/claire_up_2", gp.tileSize, gp.tileSize);
    down1 = setup("/res/npc/claire_down_1", gp.tileSize, gp.tileSize);
    down2 = setup("/res/npc/claire_down_2", gp.tileSize, gp.tileSize);
    left1 = setup("/res/npc/claire_left_1", gp.tileSize, gp.tileSize);
    left2 = setup("/res/npc/claire_left_2", gp.tileSize, gp.tileSize);
    right1 = setup("/res/npc/claire_right_1", gp.tileSize, gp.tileSize);
    right2 = setup("/res/npc/claire_right_2", gp.tileSize, gp.tileSize);
    }

public void setDialogue() {

    dialogues[0][0] = "Oh, hello! You must be another traveler!";
    dialogues[0][1] = "I hope you're not headed for those mines!\nBut then again, I suppose there aren't that\nmany other reasons";
    dialogues[0][2] = "why you'd be here...\nWell, if that is where you're headed, make\nsure you stock up at the local store before";
    dialogues[0][3] = "heading out. Being well prepared never hurt!";
    dialogues[0][4] = "Make sure to tell the shopkeeper I sent you.\nClaire, that is. Well, good luck!";
}

public void setAction() {

    if (onPath == true) {
        int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
        int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
        searchPath(goalCol, goalRow);
    } else {
        actionLockCounter++;
    
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
        
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
    
            actionLockCounter = 0;
        }
    }
}

public void speak() {
    super.speak();
    facePlayer();
    startDialogue(this, 0);

    if (dialogueSet == 0 && shopInteraction == false) {
        gp.ui.interactShop = true;
    }
}
}