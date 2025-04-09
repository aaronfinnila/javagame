package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Hitbox1 extends Entity {

    public OBJ_Hitbox1(GamePanel gp) {
        super(gp);
        name = "Hitbox1";
        collision = true;
        type = 8;
        solidArea.width = 5*gp.tileSize;
        solidArea.height = 2*gp.tileSize;
    }

}
