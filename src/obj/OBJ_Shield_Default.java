package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Default extends Entity {

    public final static String objName = "Wooden Shield";
    public OBJ_Shield_Default(GamePanel gp) {
        super(gp);

        name = objName;
        down1 = setup("/res/objects/defaultshield",gp.tileSize,gp.tileSize);
        image = down1;
        defenseValue = 1;
        type = type_shield;
        price = 30;
        description = name + "\n\nWeary old wooden shield.\nHas a slight rotten scent\nto it.";
    }
}