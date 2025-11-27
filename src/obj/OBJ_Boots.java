package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity {

    public final static String objName = "Boots";

    GamePanel gp;

    public OBJ_Boots(GamePanel gp) {
        
        super(gp);

        this.gp = gp;
        type = type_pickup_only;
        name = objName;
        down1 = setup("/res/objects/boots", gp.tileSize, gp.tileSize);
        collision = true;
    }

    public void use(Entity entity) {

        entity.speed += 2;
        gp.ui.showMessage("You found Creamor Boots!");
        gp.ui.showGoldMessage("Your speed increased by 2!");
        gp.playSE(2);
    }
}