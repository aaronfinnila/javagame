package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Store extends Entity {

    GamePanel gp;
    public OBJ_Store(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = "Store";
        type = 8;
        down1 = setup("/res/objects/store", 141*3, 170*3);
        collision = true;
        direction = "down";
        solidArea.height = 125*3;
        solidArea.width = 141*3;
    }

    public void use(Entity entity) {
    }
}