package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {

    GamePanel gp;

    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);

        this.gp = gp;
        type = 6;
        name = "Red Potion";
        healingValue = 4;
        down1 = setup("/res/objects/redpotion", gp.tileSize, gp.tileSize);
        image = down1;
        description = "Replenishes your health.";
    }

    public void use(Entity entity) {
        
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\nYour health has been\nreplenished by " + healingValue + ".";
        entity.health += healingValue;
        if (gp.player.maxHealth < gp.player.health) {
            gp.player.health = gp.player.maxHealth;
        }
        gp.playSE(2);
    }

}
