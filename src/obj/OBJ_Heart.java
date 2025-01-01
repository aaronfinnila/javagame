package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp) {
        super(gp);

        name = "Heart";
        image = setup("/res/objects/heart1");
        image2 = setup("/res/objects/heart2");
        image3 = setup("/res/objects/heart3");
    }
}