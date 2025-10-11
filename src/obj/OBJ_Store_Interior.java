package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Store_Interior extends Entity {

    public final static String objName = "Store_Interior";

    GamePanel gp;

    public OBJ_Store_Interior(GamePanel gp) {
        
        super(gp);

        this.gp = gp;
        name = objName;
        type = type_animated_object;
        down1 = setup("/res/objects/store_interior", 232*3, 240*3);
        down2 = setup("/res/objects/store_interior_night", 232*3, 240*3);

        collision = true;
    }

    public void use(Entity entity) {
    }

    public void update() {
        
        if (gp.currentMap == gp.storeMapNight) {
            spriteNum = 2;
        } else {
            spriteNum = 1;
        }
    }
}