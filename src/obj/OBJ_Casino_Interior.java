package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Casino_Interior extends Entity {

    public final static String objName = "Casino_Interior";

    GamePanel gp;

    public OBJ_Casino_Interior(GamePanel gp) {
        
        super(gp);

        this.gp = gp;
        name = objName;
        type = type_static_object;
        collision = false;
        down1 = setup("/res/objects/casino_interior", 216*3, 240*3);
    }

    public void use(Entity entity) {
    }
}