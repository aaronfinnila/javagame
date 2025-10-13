package npc;

import java.awt.Graphics2D;
import entity.Entity;
import main.GamePanel;

public class NPC_Edward extends Entity {

    GamePanel gp;

    public NPC_Edward(GamePanel gp) {

        super(gp);
        this.gp = gp;
        speed = 0;
        soundNum = 33;
        type = type_npc;
        name = "Edward";
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

    down1 = setup("/res/npc/edward_down1", gp.tileSize, gp.tileSize);
}

public void setDialogue() {

    dialogues[0][0] = "Hello and welcome to the\nGolden Estate Casino!";
    dialogues[0][1] = "Would you like to play?";
    dialogues[0][2] = "";

    dialogues[1][0] = "Roulette it is!";

    dialogues[2][0] = "They say that you miss\nevery shot you don't take...";
    dialogues[2][1] = "But maybe you'll hit it\nnext time!";

}

public void getInteractChoices() {

    if (gp.ui.interactChoice == 1) {
        gp.keyH.spacePressed = false;
        startDialogue(this, 1);
        gp.ui.interactChoice = 0;
    } else if (gp.ui.interactChoice == 2) {
        gp.keyH.spacePressed = false;
        startDialogue(this, 2);
        gp.ui.interactChoice = 0;
    }
}

public String getInteractText() {
    return "Play roulette? (15 gold bet)";
}

public void speak() {

    super.speak();
    startDialogue(this, dialogueSet);
}

@Override
public void draw(Graphics2D g2) {

    image = down1;
    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    g2.drawImage(image, screenX, screenY, null);
}
}