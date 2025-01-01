package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_DoorUpper extends Entity {

    public OBJ_DoorUpper(GamePanel gp) {
        super(gp);

        name = "DoorUpper";
        down1 = setup("/res/objects/door_upper");
        collision = true;
    }
}
