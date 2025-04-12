package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_House2_Interior extends Entity {

    GamePanel gp;
    public OBJ_House2_Interior(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = "House2_Interior";
        type = 8;
        down1 = setup("/res/objects/house2_interior", 184*3, 240*3);
        collision = false;
    }

    public void use(Entity entity) {
    }
}