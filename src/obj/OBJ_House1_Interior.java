package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_House1_Interior extends Entity {

    GamePanel gp;
    public OBJ_House1_Interior(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = "House1_Interior";
        type = type_static_object;
        down1 = setup("/res/objects/house1_interior", 184*3, 240*3);
        collision = false;
    }

    public void use(Entity entity) {
    }
}