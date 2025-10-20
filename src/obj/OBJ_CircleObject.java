package obj;

import java.awt.Graphics2D;
import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class OBJ_CircleObject extends Entity {

    public final static String objName = "CircleObject";

    GamePanel gp;
    int row = 0;
    Random random = new Random();
    int result = random.nextInt(60);

    public OBJ_CircleObject(GamePanel gp) {
        
        super(gp);

        this.gp = gp;
        name = objName;
        speed = 4;
        type = type_nocollision_object;
        collision = false;
        direction = "still";
        down1 = setup("/res/objects/circleobject", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        
        if (onPath == true) {
            direction = "right";
            if (getGoalCol(this) > 56 && row < 5) {
                row++;
                worldX = (48*gp.tileSize)-24;
                worldY += gp.tileSize;
            }
            if (worldX-(48*48)+(row*48)*10 == (result*48)-24) {
                onPath = false;
                direction = "still";
                if (gp.ui.interactChoice == 3 && result % 14 == 0 && result != 0) {
                    gp.ui.goldWon = 15*14;
                    gp.player.gold += 15*13;
                    gp.ui.winOrLose = "win";
                } else if (gp.ui.interactChoice == 1 && ((result % 2 == 0 && row % 2 == 0) ||
                (result % 2 != 0 && row % 2 != 0) )) {
                    gp.ui.goldWon = 30;
                    gp.player.gold += 15;
                    gp.ui.winOrLose = "win";
                } else if (gp.ui.interactChoice == 2 && ((result % 2 != 0 && row % 2 == 0) ||
                (result % 2 == 0 && row % 2 != 0))) {
                    gp.ui.goldWon = 30;
                    gp.player.gold += 15;
                    gp.ui.winOrLose = "win";
                } else {
                    gp.player.gold -= 15;
                    gp.ui.winOrLose = "lose";
                }
                result = random.nextInt(60);
                row = 0;
                gp.ui.interactChoice = 0;
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