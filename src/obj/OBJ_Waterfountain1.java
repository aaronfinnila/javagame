package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Waterfountain1 extends Entity {

    GamePanel gp;
    public OBJ_Waterfountain1(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = "Waterfountain1";
        type = 8;
        down1 = setup("/res/objects/waterfountain1", 96*3, 69*3);
        down2 = setup("/res/objects/waterfountain2", 96*3, 69*3);
        collision = true;
        direction = "down";
        solidArea.height = 69*3;
        solidArea.width = 96*3;
    }

    public void use(Entity entity) {
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