package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern_Inv extends Entity {
    GamePanel gp;

    public OBJ_Lantern_Inv(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_light;
        name = "Lantern";
        attackValue = 10;
        down1 = setup("/res/objects/lantern1", 12*2, 18*2);
        image = setup("/res/objects/lantern1", gp.tileSize-10, gp.tileSize-5);
        description = name + "\n\nA common lantern. Useful in\ndark places.";
        price = 50;
        lightRadius = 250;
    }
}
