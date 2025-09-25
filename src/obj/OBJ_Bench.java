package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bench extends Entity {

    public final static String objName = "Bench";

    GamePanel gp;
    
    public OBJ_Bench(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = objName;
        type = type_static_object;
        down1 = setup("/res/objects/bench", 95, 79);
        collision = true;
        solidArea.height = 60;
        solidArea.width = 100;
    }

    public void use(Entity entity) {}
}