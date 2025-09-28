package entity;

import java.awt.Graphics2D;
import java.util.Random;

import main.GamePanel;
public class NPC_Fear extends Entity {

    int escapePath;
    Random random;

    public NPC_Fear(GamePanel gp) {

        super(gp);
        type = type_npc;
        name = "Fear";
        direction = "still";
        solidArea.x = 8;
        solidArea.y = 16;
        defaultSpeed = 6;
        image = down1;
        speed = defaultSpeed;
        soundNum = 32;
        maxHealth = 10;
        health = maxHealth;
        onPath = true;
        exp = 2;
        solidArea.width = 30;
        solidArea.height = 60;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
        setDialogue();
        escapePath = 0;
        random = new Random();
        escapePath = random.nextInt(3);
    }

public void getImage() {

    down1 = setup("/res/npc/fear", gp.tileSize, gp.tileSize*2);
}

public void setDialogue() {

    dialogues[0][0] = "...";
    dialogues[0][1] = "...";
    dialogues[0][2] = "Darkness...";
    dialogues[0][3] = "Only darkness...";
    dialogues[0][4] = "";

    dialogues[1][0] = "He will have you...";
    dialogues[1][1] = "...";
    dialogues[1][2] = "Look for him...";
    dialogues[1][3] = "In the depths...";

    dialogues[2][0] = "...";
    dialogues[2][1] = "Oh...";
    dialogues[2][2] = "Run...";
    dialogues[2][3] = "Quickly...";
}

public void setAction() {
        
    if (onPath == true) {

        // CHECK CHASE

        startStalking(gp.player, 5);

    } else {

    }
}

public void startStalking(Entity target, int distance) {
        
    boolean chase = true;

    if (chase == true) {
        searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
    }

    if (getTileDistance(target) == distance) {
        direction = "still";
        chase = false;
    }

    if (getTileDistance(target) > distance) {
        chase = true;
    }

    if (getTileDistance(target) < distance) {
        chase = false;
        switch (this.escapePath) {
            case 0: searchPath(26, 38);
            case 1: searchPath(40, 62);
            case 2: searchPath(60, 33);
        }
    }
}

public void damageReaction() {
    actionLockCounter = 0;
}

public void checkDrop() {
    dropItem(currentWeapon);
}

public void getInteractChoices() {

    if (gp.ui.interactChoice == 1) {
        startDialogue(this, 1);
        gp.ui.interactChoice = 0;
    } else if (gp.ui.interactChoice == 2) {
        startDialogue(this, 2);
        gp.ui.interactChoice = 0;
    }
}

public String getInteractText() {
    String text = "Sacrifice your soul?";
    return text;
}

public void speak() {
    super.speak();
    startDialogue(this, 0);
    onPath = false;
    direction = "still";
}

@Override
public void draw(Graphics2D g2) {
    if (this.type  == type_npc) {
    image = down1;
    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    g2.drawImage(image, screenX, screenY, null);
    } else {
        super.draw(g2);
    }
}
}