package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp) {
        super(gp);

        name = "Creamor Key";
        down1 = setup("/res/objects/key", gp.tileSize, gp.tileSize);
        image = down1;
        type = type_pickup_only;
        collision = true;
        price = 100;
        description = name + "\n\nCan be used to something.";
    }
}
