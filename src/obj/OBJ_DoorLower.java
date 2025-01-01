package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_DoorLower extends Entity {

    public OBJ_DoorLower(GamePanel gp) {
        super(gp);

        name = "DoorLower";
        down1 = setup("/res/objects/door_lower");
        collision = true;
    }
}