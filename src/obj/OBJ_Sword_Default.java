package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Default extends Entity {

    public OBJ_Sword_Default(GamePanel gp) {
        super(gp);

        name = "Badgers Scimitar";
        down1 = setup("/res/objects/sword0",gp.tileSize,gp.tileSize);
        image = down1;
        attackValue = 1;
        type = 3;
        description = name + "\n\nTrusty old scimitar.";
    }
}
