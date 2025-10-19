package npc;

import java.awt.Graphics2D;

import entity.Entity;
import main.GamePanel;


public class NPC_Sofa extends Entity {

    GamePanel gp;

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
}

public void setAction() {}

public void speak() {

    super.speak();
    startDialogue(this, 0);
}

@Override
public void draw(Graphics2D g2) {

    image = down1;
    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    g2.drawImage(image, screenX, screenY, null);
}
}