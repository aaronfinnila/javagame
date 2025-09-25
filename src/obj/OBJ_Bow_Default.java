package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bow_Default extends Entity {

    public final static String objName = "Wooden Bow";

    public OBJ_Bow_Default(GamePanel gp) {
        super(gp);

        name = objName;
        down1 = setup("/res/objects/defaultbow",gp.tileSize,gp.tileSize);
        image = down1;
        attackValue = 1;
        knockBackPower = 1;
        type = type_shoot;
        price = 50;
        description = name + "\n\nSolid wooden bow made of\nacacia.";
    }
}
