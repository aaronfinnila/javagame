package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {

    public final static String objName = "Heart";

    public OBJ_Heart(GamePanel gp) {
        super(gp);

        name = objName;
        image = setup("/res/objects/heart1", gp.tileSize, gp.tileSize);
        image2 = setup("/res/objects/heart2", gp.tileSize, gp.tileSize);
        image3 = setup("/res/objects/heart3", gp.tileSize, gp.tileSize);
    }
}