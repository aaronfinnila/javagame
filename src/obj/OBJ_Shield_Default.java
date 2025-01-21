package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Default extends Entity {

    public OBJ_Shield_Default(GamePanel gp) {
        super(gp);

        name = "Wood";
        down1 = setup("/res/objects/shield0",gp.tileSize,gp.tileSize);
        defenseValue = 1;
    }
}