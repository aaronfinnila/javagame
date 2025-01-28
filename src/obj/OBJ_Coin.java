package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity {

    public OBJ_Coin(GamePanel gp) {
        super(gp);

        name = "Coin";
        down1 = setup("/res/objects/coin", gp.tileSize - 25, gp.tileSize - 25);
        collision = true;
    }
}