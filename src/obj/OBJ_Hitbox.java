package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Hitbox extends Entity {

    public final static String objName = "Hitbox";

    public OBJ_Hitbox(GamePanel gp) {
        
        super(gp);
        name = objName;
        collision = true;
        type = type_static_object;
        solidArea.width = 5*gp.tileSize;
        solidArea.height = 2*gp.tileSize;
    }

}
