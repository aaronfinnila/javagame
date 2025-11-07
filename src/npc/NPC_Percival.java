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
    dialogues[2][6] = "Now go off and harass somebody\nelse, will you?";

    dialogues[3][0] = "What happened to the peace\nand quiet in this darn village?";

}

public void setAction() {

    if (getGoalCol(this) == 55) {
        searchPath(47, 46);
    } else if (getGoalCol(this) == 47) {
        searchPath(55, 46);
    }
}

public String getInteractText() {

    return "Ask what he's reading?";
}

public void getInteractChoices() {

    if (gp.ui.interactChoice == 1) {
        gp.keyH.spacePressed = false;
        startDialogue(this, 2);
        gp.ui.interactChoice = 0;
    } else if (gp.ui.interactChoice == 2) {
        gp.keyH.spacePressed = false;
        startDialogue(this, 3);
        gp.ui.interactChoice = 0;
    }
}
public void speak() {

    super.speak();
    facePlayer();
    startDialogue(this, dialogueSet);
}

}