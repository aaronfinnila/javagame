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
        description = name + "\n\nReplenishes your health.";
    }

    public void use(Entity entity) {
        
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = " You drink the " + name + "!\n Your health has been replenished by " + healingValue + ".";
        entity.health += healingValue;
        gp.playSE(2);
    }

}
