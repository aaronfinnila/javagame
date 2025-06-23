package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Questionmark extends Entity {

    public final static String objName = "Questionmark";

    public OBJ_Questionmark(GamePanel gp) {
        super(gp);

        name = objName;
        down1 = setup("/res/objects/questionmark", gp.tileSize, gp.tileSize);
        collision = true;
    }
}
