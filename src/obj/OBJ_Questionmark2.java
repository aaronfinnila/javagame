package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Questionmark2 extends Entity {

    public OBJ_Questionmark2(GamePanel gp) {
        super(gp);

        name = "Questionmark2";
        down1 = setup("/res/extra/questionable");
        collision = true;
    }
}