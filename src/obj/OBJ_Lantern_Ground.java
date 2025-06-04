package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern_Ground extends Entity {
    GamePanel gp;

    public OBJ_Lantern_Ground(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_animated_object;
        name = "Lantern_Ground";
        attackValue = 10;
        down1 = setup("/res/objects/lantern1", 12*2, 18*2);
        down2 = setup("/res/objects/lantern2", 12*2, 18*2);
        solidArea.width = 12*2;
        solidArea.height = 18*2;
        collision = true;
        lightRadius = 250;
    }

    public void update() {
        moveCounter++;
        if (moveCounter <= 60) {
            spriteNum = 1;
        }
        else if (moveCounter > 60 && moveCounter <= 120) {
            spriteNum = 2;
        } 
        else if (moveCounter > 120) {
            moveCounter = 0;
        }
    }
}
