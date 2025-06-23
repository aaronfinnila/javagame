package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity {

    public final static String objName = "Coin";

    GamePanel gp;

    public OBJ_Coin(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = objName;
        type = type_pickup_only;
        down1 = setup("/res/objects/coin", gp.tileSize - 25, gp.tileSize - 25);
        collision = true;
    }
    public void use(Entity entity) {
        entity.gold += 5;
        gp.ui.showMessage("You found a Coin!");
        gp.ui.showGoldMessage("You received 5 gold!");
        gp.playSE(1);
    }
}