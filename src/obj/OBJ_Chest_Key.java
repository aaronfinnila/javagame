package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest_Key extends Entity {

    public final static String objName = "Chest Key";

    public OBJ_Chest_Key(GamePanel gp) {
        
        super(gp);

        name = objName;
        down1 = setup("/res/objects/chestkey", gp.tileSize, gp.tileSize);
        image = down1;
        type = type_pickup_only;
        collision = true;
        price = 25;
        description = name + "\n\nCan be used to open a\nchest.";
    }
}
