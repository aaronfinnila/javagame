package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity {

    GamePanel gp;

    public OBJ_Boots(GamePanel gp) {
        super(gp);

        type = type_pickup_only;
        name = "Boots";
        down1 = setup("/res/objects/boots", gp.tileSize, gp.tileSize);
        collision = true;
    }
}