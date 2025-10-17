package obj;

import java.awt.Graphics2D;

import entity.Entity;
import main.GamePanel;

public class OBJ_CircleObject extends Entity {

    public final static String objName = "CircleObject";

    GamePanel gp;
    int row = 0;

    public OBJ_CircleObject(GamePanel gp) {
        
        super(gp);

        this.gp = gp;
        name = objName;
        speed = 4;
        type = type_animated_object;
        collision = false;
        direction = "still";
        down1 = setup("/res/objects/circleobject", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        
        if (onPath == true) {
            System.out.println(direction);
            direction = "right";
            System.out.println(getGoalCol(this));
            if (getGoalCol(this) > 56 && row < 6) {
                row++;
                worldX = 48*gp.tileSize;
                worldY += gp.tileSize;
            }
            if (row == 6) {
                System.out.println("circle STOPPED!!!");
                onPath = false;
                direction = "still";
            }
        } else {

        }
    }

    @Override
    public void draw(Graphics2D g2) {
    image = down1;
    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    g2.drawImage(image, screenX, screenY, null);
    }
}