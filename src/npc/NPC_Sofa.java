package npc;

import java.awt.Graphics2D;

import entity.Entity;
import main.GamePanel;

public class NPC_Sofa extends Entity {

    GamePanel gp;
    public boolean spawn1 = false;

    public NPC_Sofa(GamePanel gp) {

        super(gp);
        this.gp = gp;
        speed = 0;
        type = type_npc;
        name = "Sofa";
        direction = "down";
        setDialogue();
        getImage();
        soundNum = 35;
        solidArea.height = 29*3;
        solidArea.width = 53*3;
    }

public void getImage() {

    down1 = setup("/res/npc/sofa", 53*3, 29*3);
}

public void setDialogue() {

    dialogues[0][0] = "I am the almighty sofa.";
    dialogues[0][1] = "And I shall bring you something\nvery, very valuable...";
    dialogues[0][2] = "What I am talking about is,\nof course...";
    dialogues[0][3] = "YOUR DOOM!";

    dialogues[2][0] = "Wait, you're...";
    dialogues[2][1] = "ALIVE???";
    dialogues[2][2] = "Nobody has ever survived my trap\nbefore...";
    dialogues[2][3] = "I suppose you ought to be\nrewarded.";

}

public void setAction() {}

public void speak() {

    super.speak();
    startDialogue(this, 0);
    
    boolean monsterExists = false;
    for (int i = 0; i < 10; i++) {
        if (gp.monster[gp.currentMap][i] != null) {
            monsterExists = true;
        }
    }
    if (interactionHappened == true && monsterExists == false) {
        dialogueSet = 2;
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