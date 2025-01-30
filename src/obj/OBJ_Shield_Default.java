package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Default extends Entity {

    public OBJ_Shield_Default(GamePanel gp) {
        super(gp);

        name = "Wooden Shield";
        down1 = setup("/res/objects/shield0",gp.tileSize,gp.tileSize);
        image = down1;
        defenseValue = 1;
        description = name + "\n\nWeary old wooden shield.\nHas a slight rotten scent\nto it.";
    }
}