package npc;

import entity.Entity;
import main.GamePanel;

public class NPC_Percival extends Entity {

    GamePanel gp;
    boolean destinationReached = false;

    public NPC_Percival(GamePanel gp) {

        super(gp);
        this.gp = gp;
        speed = 0;
        soundNum = 0;
        type = type_npc;
        name = "Percival";
        direction = "down";
        getImage();
        setDialogue();
    }

public void getImage() {

    down1 = setup("/res/npc/percival_down1", gp.tileSize, gp.tileSize);
}

public void setDialogue() {

    dialogues[0][0] = "...";
    dialogues[0][1] = "Go away, I'm busy.";

    dialogues[1][0] = "...";
    dialogues[1][1] = "...";
    dialogues[1][2] = "You're still here.";
    dialogues[1][3] = "What do you want?";
}

public void setAction() {

    if (destinationReached == false) {
        searchPath(getGoalCol(this)+4, getGoalRow(this));
        destinationReached = true;
    } else {
        searchPath(getGoalCol(this)-4, getGoalRow(this));
        destinationReached = false;
    }
}

public void speak() {

    super.speak();
    startDialogue(this, dialogueSet);
}


public void update() {

}
}