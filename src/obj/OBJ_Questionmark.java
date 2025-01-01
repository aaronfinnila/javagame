package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Questionmark extends Entity {

    public OBJ_Questionmark(GamePanel gp) {
        super(gp);

        name = "Questionmark";
        down1 = setup("/res/objects/questionmark");
        collision = true;
    }
}
