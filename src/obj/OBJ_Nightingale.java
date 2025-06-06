package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Nightingale extends Entity {

    public OBJ_Nightingale(GamePanel gp) {
        super(gp);

        name = "Nightingale";
        down1 = setup("/res/objects/nightingale",gp.tileSize,gp.tileSize);
        image = down1;
        attackValue = 5;
        knockBackPower = 0;
        attackArea.width = 36;
        attackArea.height = 60;
        type = type_sword;
        speed = 1;
        price = 250;
        description = name + "\n\nMade of refined sapphire.\nHas greatly increased range\ncompared to regular swords.";
        //TODO player attack animations (copy longsword) + new hit sound
    }
}
