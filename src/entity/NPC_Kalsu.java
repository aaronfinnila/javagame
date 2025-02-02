package entity;

import java.awt.Graphics2D;

import main.GamePanel;

public class NPC_Kalsu extends Entity {

    public NPC_Kalsu(GamePanel gp) {

        super(gp);
        speed = 0;
        direction = "still";
        getImage();
        setDialogue();
    }

    public void getImage() {
    down1 = setup("/res/npc/kalsu", gp.tileSize, gp.tileSize);
}

public void setDialogue() {
    dialogues[0] = "You gotta lock in bro";
    dialogues[1] = "Elos vaan kale";
}

public void setAction() {}

public void speak() {
    super.speak();
}

@Override
public void draw(Graphics2D g2) {

    image = down1;
    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    g2.drawImage(image, screenX, screenY, null);
    }
}