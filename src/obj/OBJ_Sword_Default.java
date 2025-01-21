package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Default extends Entity {

    public OBJ_Sword_Default(GamePanel gp) {
        super(gp);

        name = "Wood";
        down1 = setup("/res/objects/sword0",gp.tileSize,gp.tileSize);
        attackValue = 1;
    }
}
