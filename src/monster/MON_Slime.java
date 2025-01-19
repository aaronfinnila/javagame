package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_Slime extends Entity {
    
    GamePanel gp;

    public MON_Slime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = 2;
        name = "Slime";
        speed = 1;
        maxHealth = 20;
        health = maxHealth;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }

    public void getImage() {

        up1 = setup("/res/monster/slime_down1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/slime_down2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/slime_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/slime_down2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/slime_down1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/slime_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/slime_down1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/slime_down2", gp.tileSize, gp.tileSize);

        death1 = setup("/res/monster/slime_death1", gp.tileSize*2, gp.tileSize*2);
        death2 = setup("/res/monster/slime_death2", gp.tileSize*2, gp.tileSize*2);
        death3 = setup("/res/monster/slime_death3", gp.tileSize*2, gp.tileSize*2);
        death4 = setup("/res/monster/slime_death4", gp.tileSize*2, gp.tileSize*2);
        death5 = setup("/res/monster/slime_death5", gp.tileSize*2, gp.tileSize*2);
    }

    public void setAction() {
        
    actionLockCounter++;

    if (actionLockCounter == 120) {
        Random random = new Random();
        int i = random.nextInt(100) + 1;
    
        if (i <= 25) {
            direction = "up";
        }
        if (i > 25 && i <= 50) {
            direction = "down";
        }
        if (i > 50 && i <= 75) {
            direction = "left";
        }
        if (i > 75 && i <= 100) {
            direction = "right";
        }

        actionLockCounter = 0;
    }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        String pdir = gp.player.direction;
        switch (pdir) {
            case "down":
                direction = "up";
                break;
            case "up":
                direction = "down";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
}
