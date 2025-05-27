package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Rubert extends Entity {

    public NPC_Rubert(GamePanel gp) {

        super(gp);
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }
    public void getImage() {

    up1 = setup("/res/npc/rubert_up_1", gp.tileSize, gp.tileSize);
    up2 = setup("/res/npc/rubert_up_2", gp.tileSize, gp.tileSize);
    down1 = setup("/res/npc/rubert_down_1", gp.tileSize, gp.tileSize);
    down2 = setup("/res/npc/rubert_down_2", gp.tileSize, gp.tileSize);
    left1 = setup("/res/npc/rubert_left_1", gp.tileSize, gp.tileSize);
    left2 = setup("/res/npc/rubert_left_2", gp.tileSize, gp.tileSize);
    right1 = setup("/res/npc/rubert_right_1", gp.tileSize, gp.tileSize);
    right2 = setup("/res/npc/rubert_right_2", gp.tileSize, gp.tileSize);
}

public void setDialogue() {
    dialogues[0] = "Hello";
    dialogues[1] = "Are you here for the \nlegendary treasure?";
    dialogues[2] = "I'm just a dog!";
    dialogues[3] = "What are you looking at?";
    dialogues[4] = "That shirt looks hideous";
}

public void setAction() {

    if (onPath == true) {
        int goalCol = 46;
        int goalRow = 54;
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

    onPath = true;
}
}