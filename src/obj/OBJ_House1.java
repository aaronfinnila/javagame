package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_House1 extends Entity {

    GamePanel gp;
    public OBJ_House1(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = "House1";
        type = 8;
        down1 = setup("/res/objects/house1", 87*3, 124*3);
        collision = true;
        solidArea.height = 124*3;
        solidArea.width = 87*3;
    }

    public void use(Entity entity) {
    }
}