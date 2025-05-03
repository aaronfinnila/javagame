package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Store_Interior extends Entity {

    GamePanel gp;
    public OBJ_Store_Interior(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = "Store_Interior";
        type = 8;
        down1 = setup("/res/objects/store_interior", 232*3, 240*3);
        collision = true;
    }

    public void use(Entity entity) {
    }
}