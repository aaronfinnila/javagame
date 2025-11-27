package npc;

import entity.Entity;
import main.GamePanel;

public class NPC_Percival extends Entity {

    GamePanel gp;

    public NPC_Percival(GamePanel gp) {

        super(gp);
        this.gp = gp;
        speed = 1;
        soundNum = 33;
        type = type_npc;
        name = "Percival";
        direction = "down";
        getImage();
        setDialogue();

        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 30;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

public void getImage() {

    down1 = setup("/res/npc/percival_down1", gp.tileSize, gp.tileSize);
    down2 = setup("/res/npc/percival_down2", gp.tileSize, gp.tileSize);
    up1 = setup("/res/npc/percival_up1", gp.tileSize, gp.tileSize);
    up2 = setup("/res/npc/percival_up2", gp.tileSize, gp.tileSize);
    left1 = setup("/res/npc/percival_left1", gp.tileSize, gp.tileSize);
    left2 = setup("/res/npc/percival_left2", gp.tileSize, gp.tileSize);
    right1 = setup("/res/npc/percival_right1", gp.tileSize, gp.tileSize);
    right2 = setup("/res/npc/percival_right2", gp.tileSize, gp.tileSize);
}

public void setDialogue() {

    dialogues[0][0] = "...";
    dialogues[0][1] = "Go away, I'm busy.";

    dialogues[1][0] = "...";
    dialogues[1][1] = "...";
    dialogues[1][2] = "You're still here.";
    dialogues[1][3] = "What do you want?";
    dialogues[1][4] = "";

    dialogues[2][0] = "That's none of your business,\ntraveler.";
    dialogues[2][1] = "You're a nosy one, aren't you?";
    dialogues[2][2] = "...";
    dialogues[2][3] = "(sigh)\n";
    dialogues[2][4] = "Alright then, it's an old book.";
    dialogues[2][5] = "Has to do with the history of\nthis place.";
    dialogues[2][6] = "According to legend, a man named\nRilk used to live on this island.";
    dialogues[2][7] = "He was a strong warrior, and he\nwent to the mines to look for\nsome treasure";
    dialogues[2][8] = "However, the darkness in that\nplace slowly made him go crazy.";
    dialogues[2][9] = "They say that when he lost his\nmind, his soul was split into three\ndiamonds.";
    dialogues[2][10] = "Three heart-shaped diamonds.\nI've tried to track down these\ndiamonds for years.";
    dialogues[2][11] = "Two of them should be in the\nmines, but one should be somewhere\non the surface.";
    dialogues[2][12] = "I suspect that someone in the\nvillage is hiding the one on\nthe surface.";
    dialogues[2][13] = "Lots of travelers go missing\nevery year in those mines...";
    dialogues[2][14] = "If we could find the diamond\nhearts, nobody would need to go\nin there again.";
    dialogues[2][15] = "If you manage to find and bring\nthem to me, I will reward you\ngreatly.";
    dialogues[2][16] = "I will also help you get back to\nMidland.";
    dialogues[2][17] = "So remember: one diamond heart on\nthe surface, and two in the mines.";
    dialogues[2][18] = "Good luck, traveler.\nBe careful in those mines...";

    dialogues[3][0] = "What happened to the peace\nand quiet in this darn village?";

    dialogues[5][0] = "I can't believe it...";
    dialogues[5][1] = "You really found them!";
    dialogues[5][2] = "I cannot thank you enough...";
    dialogues[5][3] = "You can get your reward from the\nwoods below the casino.";
    dialogues[5][4] = "There should be a path there on\nthe right side of the casino.";
    dialogues[5][5] = "Also, take this Hammer.\nYou will need it to break some\nrocks on the way there.";
    dialogues[5][6] = "I will be waiting for you there.";

    dialogues[7][0] = "Oh, there you are.";
    dialogues[7][1] = "I have to thank you for\neverything you've done.";
    dialogues[7][2] = "Thanks to you, we will finally\nhave some peace in this village.";
    dialogues[7][3] = "Shall we get going then?";
    dialogues[7][4] = "";

    dialogues[9][0] = "Okay then. Come tell me\nwhen you're ready to set off.";

    dialogues[11][0] = "Alright, we're off!";
    dialogues[11][1] = "";
}

public void setAction() {

    if (gp.currentMap == gp.house3Map) {
        if (getGoalCol(this) == 55) {
            searchPath(47, 83);
        } else if (getGoalCol(this) == 47) {
            searchPath(55, 83);
        }
    } else {
        if (getGoalCol(this) == 38) {
            searchPath(48, 83);
        } else if (getGoalCol(this) == 48) {
            searchPath(38, 83);
        }
    }
}

public String getInteractText() {

    if (gp.currentMap == gp.house3Map) {
        return "Ask what he's reading?";
    } else {
        return "Travel to Midland?";
    }
}

public void getInteractChoices() {

    if (gp.currentMap == gp.house3Map) {
        if (gp.ui.interactChoice == 1) {
            gp.keyH.spacePressed = false;
            startDialogue(this, 2);
            gp.ui.interactChoice = 0;
        } else if (gp.ui.interactChoice == 2) {
            gp.keyH.spacePressed = false;
            startDialogue(this, 3);
            gp.ui.interactChoice = 0;
        }
    } else {
        if (gp.ui.interactChoice == 2) {
            gp.keyH.spacePressed = false;
            startDialogue(this, 9);
            gp.ui.interactChoice = 0;
        } else if (gp.ui.interactChoice == 1) {
            gp.keyH.spacePressed = false;
            startDialogue(this, 11);
            gp.ui.interactChoice = 0;
        }
    }
}
public void speak() {

    if (gp.currentMap == gp.treasureislandMap) {
        dialogueSet = 6;
        dialogueIndex = 0;
    }

    super.speak();
    startDialogue(this, dialogueSet);
    facePlayer();

    if (gp.player.diamondHeartCount == 3) {
        dialogueSet = 5;
    }
}

}