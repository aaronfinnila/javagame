package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_House3 extends Entity {

    public final static String objName = "House3";

    GamePanel gp;

    public OBJ_House3(GamePanel gp) {
        
        super(gp);

        this.gp = gp;
        name = objName;
        type = type_static_object;
        down1 = setup("/res/objects/house3", 87*3, 124*3);
        collision = true;
        solidArea.height = 124*3;
        solidArea.width = 87*3;
    }

    public void use(Entity entity) {
    }
}