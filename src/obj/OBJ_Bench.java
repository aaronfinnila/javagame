package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bench extends Entity {

    GamePanel gp;
    public OBJ_Bench(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = "Bench";
        type = 8;
        down1 = setup("/res/objects/bench", 95, 79);
        collision = true;
        solidArea.height = 60;
        solidArea.width = 100;
    }

    public void use(Entity entity) {}
}