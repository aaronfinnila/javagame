package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp) {
        super(gp);

        name = "Key";
        down1 = setup("/res/objects/key", gp.tileSize, gp.tileSize);
        image = down1;
        collision = true;
        description = name + "\n\nIt opens a door.";
    }
}
