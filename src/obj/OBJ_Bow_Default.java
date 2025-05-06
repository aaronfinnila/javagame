package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bow_Default extends Entity {

    public OBJ_Bow_Default(GamePanel gp) {
        super(gp);

        name = "Wooden Bow";
        down1 = setup("/res/objects/defaultbow",gp.tileSize,gp.tileSize);
        image = down1;
        attackValue = 1;
        type = 5;
        price = 50;
        description = name + "\n\nSolid wooden bow made of\nacacia.";
    }
}
