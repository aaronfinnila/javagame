package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Waterfountain extends Entity {

    public final static String objName = "Waterfountain";

    GamePanel gp;

    public OBJ_Waterfountain(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = objName;
        type = type_animated_object;
        down1 = setup("/res/objects/waterfountain1", 96*3, 69*3);
        down2 = setup("/res/objects/waterfountain2", 96*3, 69*3);
        collision = true;
        direction = "down";
        solidArea.height = 55*3;
        solidArea.width = 76*3;
        solidAreaDefaultX = 30;
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