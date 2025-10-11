package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Casino extends Entity {

    public final static String objName = "Casino";

    GamePanel gp;

    public OBJ_Casino(GamePanel gp) {

        super(gp);

        this.gp = gp;
        name = objName;
        type = type_animated_object;
        collision = true;
        direction = "down";
        getImage();
        solidArea.height = 125*3;
        solidArea.width = 141*3;
    }

    public void getImage() {
        
        down1 = setup("/res/objects/casino1", 141*3, 173*3);
        down2 = setup("/res/objects/casino2", 141*3, 173*3);
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