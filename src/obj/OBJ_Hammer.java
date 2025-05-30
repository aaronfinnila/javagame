package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Hammer extends Entity {

    public OBJ_Hammer(GamePanel gp) {
        super(gp);

        name = "Hammer";
        down1 = setup("/res/objects/hammer",gp.tileSize,gp.tileSize);
        image = down1;
        speed = 1;
        attackValue = 1;
        attackArea.width = 34;
        attackArea.height = 36;
        knockBackPower = 4;
        type = type_hammer;
        price = 60;
        description = name + "\n\nSolid hammer. Often used\nfor breaking rocks.";
    }
}
