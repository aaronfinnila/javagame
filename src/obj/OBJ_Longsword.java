package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Longsword extends Entity {

    public OBJ_Longsword(GamePanel gp) {
        super(gp);

        name = "Longsword";
        down1 = setup("/res/objects/longsword",gp.tileSize,gp.tileSize);
        image = down1;
        attackValue = 2;
        attackArea.width = 46;
        attackArea.height = 46;
        type = 3;
        description = name + "\n\nMade of solid steel. Has\nincreased range compared\nto regular swords.";
    }
}
