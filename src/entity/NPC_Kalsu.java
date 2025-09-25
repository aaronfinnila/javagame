package entity;

import java.awt.Graphics2D;
import java.util.Random;

import main.GamePanel;
import obj.OBJ_Key;
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
        soundNum = 30;
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
        setItems();
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

    dialogues[1][0] = "Oh, that's a nice sword you got...";
    dialogues[1][1] = "Oh yes, very shiny...";
    dialogues[1][2] = "It would be a real shame if it\nended up in the wrong hands...";
    dialogues[1][3] = "We would really like to have it,\nwouldn't we? He he he...";
    dialogues[1][4] = "What do you say, adventurer?\nI could give you something very,\nvery valuable in return...";
    dialogues[1][5] = "";

    dialogues[2][0] = "Ah well, your loss.\nMy item could have opened up\nmany doors for you. Thehehe...";

    dialogues[3][0] = "Pleasure doing business with you..\nWasn't it, my love? Thehehe...";
}

public void setItems() {
    inventory.add(new OBJ_Key(gp));
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
    dropItem(currentWeapon);
}

public void getInteractChoices() {

    if (gp.ui.interactChoice == 1) {
        if (gp.player.currentWeapon == gp.player.inventory.get(gp.ui.playerItemIndex)) {
            gp.player.currentWeapon = gp.player.inventory.get(0);
        }
        inventory.add(gp.player.inventory.get(gp.ui.playerItemIndex));
        gp.player.inventory.add(inventory.getFirst());
        gp.player.hasKey++;
        gp.player.inventory.remove(gp.ui.playerItemIndex);
        inventory.remove(inventory.getFirst());
        gp.keyH.spacePressed = false;
        gp.playSE(1);
        startDialogue(this, 3);
        gp.ui.interactChoice = 0;
    } else if (gp.ui.interactChoice == 2) {
        System.out.println(gp.keyH.spacePressed);
        gp.keyH.spacePressed = false;
        startDialogue(this, 2);
        gp.ui.interactChoice = 0;
    }
}

public String getInteractText() {
    String text = "Trade Longsword for Unknown Item?";
    return text;
}

public void speak() {
    super.speak();
    startDialogue(this, 0);
    int tempIndex = 0;
    for (Entity e : gp.player.inventory) {
        if (e.name == "Longsword") {
            startDialogue(this, 1);
            tempIndex = gp.player.inventory.indexOf(e);
            gp.ui.playerItemIndex = tempIndex;
/*             Entity newmon = new NPC_Kalsu(gp);
            gp.monster[gp.currentMap][0] = newmon;
            newmon.worldX = worldX;
            newmon.worldY = worldY;
            newmon.onPath = true;
            newmon.type = type_monster;
            alive = false;
            gp.npc[gp.currentMap][0] = null;
            newmon.inventory.add(e); */
        }
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