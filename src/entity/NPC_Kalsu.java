package entity;

import java.awt.Graphics2D;
import java.util.Random;

import main.GamePanel;
import obj.OBJ_Nightingale;
public class NPC_Kalsu extends Entity {

    public NPC_Kalsu(GamePanel gp) {

        super(gp);
        type = type_npc;
        name = "Kalsu";
        direction = "still";
        currentWeapon = new OBJ_Nightingale(gp);
        solidArea.x = 8;
        solidArea.y = 16;
        defaultSpeed = 2;
        image = down1;
        speed = defaultSpeed;
        maxHealth = 10;
        health = maxHealth;
        attack = 3;
        defense = 0;
        exp = 2;
        Random random = new Random();
        gold = random.nextInt(1, 3);
        solidArea.width = 30;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
        getAttackImage();
        setDialogue();
    }

public void getImage() {
    down1 = setup("/res/npc/kalsu_down_1", gp.tileSize, gp.tileSize);
    down2 = setup("/res/npc/kalsu_down_2", gp.tileSize, gp.tileSize);
    up1 = setup("/res/npc/kalsu_up_1", gp.tileSize, gp.tileSize);
    up2 = setup("/res/npc/kalsu_up_2", gp.tileSize, gp.tileSize);
    left1 = setup("/res/npc/kalsu_left_1", gp.tileSize, gp.tileSize);
    left2 = setup("/res/npc/kalsu_left_2", gp.tileSize, gp.tileSize);
    right1 = setup("/res/npc/kalsu_right_1", gp.tileSize, gp.tileSize);
    right2 = setup("/res/npc/kalsu_right_2", gp.tileSize, gp.tileSize);
}

public void getAttackImage() {

    attackArea.height = currentWeapon.attackArea.height;
    attackArea.width = currentWeapon.attackArea.width;
    
    attackUp1 = setup("/res/npc/kalsu_nightingale_up1", gp.tileSize, gp.tileSize*2);
    attackUp2 = setup("/res/npc/kalsu_nightingale_up2", gp.tileSize, gp.tileSize*2);
    attackLeft1 = setup("/res/npc/kalsu_nightingale_left1", gp.tileSize*2, gp.tileSize);
    attackLeft2 = setup("/res/npc/kalsu_nightingale_left2", gp.tileSize*2, gp.tileSize);
    attackDown1 = setup("/res/npc/kalsu_down_2", gp.tileSize, gp.tileSize);
    attackDown2 = setup("/res/npc/kalsu_nightingale_down1", gp.tileSize, gp.tileSize*2+6);
    attackRight1 = setup("/res/npc/kalsu_right_1", gp.tileSize, gp.tileSize);
    attackRight2 = setup("/res/npc/kalsu_nightingale_right1", gp.tileSize*2, gp.tileSize);
}

public void setDialogue() {

    dialogues[0][0] = "Yes, my queen...";
    dialogues[0][1] = "Oh yes, you shall have them all...";
    dialogues[0][2] = "Oh, it seems we have a guest.";
    dialogues[0][3] = "Who does he think he is, intruding\nupon our private space?";
    dialogues[0][4] = "He looks like a foreigner, doesn't\nhe?";
    dialogues[0][5] = "Another GRAND ADVENTURER, coming\nfor the LEGENDARY TREASURE, huh?";
    dialogues[0][6] = "Oh, right! Because THIS one will be the one who gets\nit all! SURELY he won't end up like the rest, RIIGHT???";
    dialogues[0][7] = "And then we will be waiting...\nwon't we, my queen? Oh yes, yes\nwe will...";

    dialogues[1][0] = "Oh, that's a nice sword you got there...";
    dialogues[1][1] = "It would be a real shame if...";
    dialogues[1][2] = "SOMEONE STOLE IT!!!";
}

public void setAction() {
    
    if (onPath == true) {

        // CHECK CHASE

        searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        
        if (attacking == false) {
            checkAttackOrNot(60, gp.tileSize*4, gp.tileSize);
        }

} else {

}
}

public void damageReaction() {
    actionLockCounter = 0;
    }

public void checkDrop() {
    dropItem(new OBJ_Nightingale(gp));
}

public void speak() {
    super.speak();
    startDialogue(this, 0);
    int tempIndex = 0;
    for (Entity e : gp.player.inventory) {
        if (e.name.equals("Nightingale")) {
            startDialogue(this, 1);
            tempIndex = gp.player.inventory.indexOf(e);            

/*             Entity newmon = new NPC_Kalsu(gp);
            gp.monster[gp.currentMap][0] = newmon;
            newmon.worldX = worldX;
            newmon.worldY = worldY;
            newmon.onPath = true;
            newmon.type = type_monster;
            alive = false;
            gp.npc[gp.currentMap][0] = null; */
        }
    }
    if (tempIndex != 0) {
        gp.player.inventory.remove(tempIndex);
        tempIndex = 0;
    }
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