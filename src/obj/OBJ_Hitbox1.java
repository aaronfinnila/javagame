package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Hitbox1 extends Entity {

    public final static String objName = "Hitbox1";

    public OBJ_Hitbox1(GamePanel gp) {
        super(gp);
        name = objName;
        collision = true;
        type = type_static_object;
        solidArea.width = 5*gp.tileSize;
        solidArea.height = 2*gp.tileSize;
    }

}
