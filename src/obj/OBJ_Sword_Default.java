package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Default extends Entity {

    public final static String objName = "Badgers Scimitar";
    public OBJ_Sword_Default(GamePanel gp) {
        super(gp);

        name = objName;
        down1 = setup("/res/objects/defaultsword",gp.tileSize,gp.tileSize);
        image = down1;
        speed = 1;
        knockBackPower = 2;
        attackArea.height = 36;
        attackArea.width = 36;
        price = 30;
        attackValue = 1;
        type = type_sword;
        description = name + "\n\nTrusty old scimitar.";
    }
}
