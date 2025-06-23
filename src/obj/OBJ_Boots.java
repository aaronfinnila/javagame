package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity {

    public final static String objName = "Boots";

    GamePanel gp;

    public OBJ_Boots(GamePanel gp) {
        super(gp);

        type = type_pickup_only;
        name = objName;
        down1 = setup("/res/objects/boots", gp.tileSize, gp.tileSize);
        collision = true;
    }
}