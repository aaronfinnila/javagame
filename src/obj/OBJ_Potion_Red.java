package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {

    GamePanel gp;

    public final static String objName = "Red Potion";

    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);

        this.gp = gp;
        type = type_consumable;
        name = objName;
        healingValue = 4;
        price = 20;
        down1 = setup("/res/objects/redpotion", gp.tileSize, gp.tileSize);
        image = down1;
        description = name + "\n\nHelps to heal your wounds.";
    }

    public void use(Entity entity) {
        
        dialogues[0][0] = " You drink the " + name + "!\n Your health has been replenished by " + healingValue + ".";
        gp.keyH.spacePressed = false;
        gp.playSE(2);
        entity.health += healingValue;
        startDialogue(this, 0);
    }

}
