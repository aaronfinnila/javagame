package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Questionmark2 extends Entity {

    public final static String objName = "Questionmark2";

    public OBJ_Questionmark2(GamePanel gp) {
        super(gp);

        name = objName;
        down1 = setup("/res/extra/questionable", gp.screenWidth, gp.screenHeight);
        collision = true;
    }
}