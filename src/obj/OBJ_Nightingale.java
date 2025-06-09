package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Nightingale extends Entity {

    public final static String objName = "Nightingale";
    public OBJ_Nightingale(GamePanel gp) {
        super(gp);

        name = objName;
        down1 = setup("/res/objects/nightingale",gp.tileSize,gp.tileSize);
        image = down1;
        attackValue = 5;
        knockBackPower = 1;
        attackArea.width = 50;
        attackArea.height = 50;
        type = type_sword;
        speed = 1;
        price = 250;
        description = name + "\n\nMade of refined sapphire.\nHas greatly increased range\ncompared to regular swords.";
    }
}
