package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity {
    GamePanel gp;

    public OBJ_Lantern(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_light;
        name = "Lantern";
        down1 = setup("/res/objects/lantern1", 12*2, 18*2);
        description = "A common lantern. Useful in\ndark places.";
        price = 50;
        lightRadius = 250;
    }
}
