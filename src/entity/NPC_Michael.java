package entity;

import java.awt.Graphics2D;

import main.GamePanel;
import obj.OBJ_Bow_Default;
import obj.OBJ_Hammer;
import obj.OBJ_Longsword;
import obj.OBJ_Potion_Red;

public class NPC_Michael extends Entity {

    public NPC_Michael(GamePanel gp) {

        super(gp);
        speed = 0;
        type = type_npc;
        name = "Michael";
        direction = "still";
        getImage();
        setDialogue();
        setItems();
    }

public void getImage() {
    down1 = setup("/res/npc/michael", gp.tileSize, gp.tileSize);
}

public void setDialogue() {
    dialogues[0][0] = "Hello there! Welcome to my store!";

    dialogues[1][0] = "Oh, Claire sent you? I see...\nI will have to thank her for that!\nHehe";
    dialogues[1][1] = "Did... did she talk about me?\nI mean, about the shop, did she\nmention the shop?";
    dialogues[1][2] = "(well, since you're here, I suppose she\ndid...) Anyway, I guess I should give\nyou a bit of a discount...";
    dialogues[1][3] = "Everything will cost 5 gold less for you.\nJust because you're a friend of Claire's.";
    
    dialogues[2][0] = "Thank you, please come again!";
    
    dialogues[3][0] = "You need more gold!";
    
    dialogues[4][0] = "You cannot carry any more!";
    
    dialogues[5][0] = "";
    
    dialogues[6][0] = "You cannot sell an equipped item!";
    
    dialogues[7][0] = "";

    dialogues[8][0] = "What would you like today?";

    dialogues[9][0] = "Got some nice offers for you!";
}

public void setAction() {}

public void speak() {
    dialogueSet = 0;
    gp.gameState = gp.tradeState;
    gp.ui.npc = this;

}

public void setItems() {
    inventory.add(new OBJ_Potion_Red(gp));
    inventory.add(new OBJ_Potion_Red(gp));
    inventory.add(new OBJ_Bow_Default(gp));
    inventory.add(new OBJ_Longsword(gp));
    inventory.add(new OBJ_Hammer(gp));
}

@Override
public void draw(Graphics2D g2) {

    image = down1;
    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    g2.drawImage(image, screenX, screenY, null);
    }
}