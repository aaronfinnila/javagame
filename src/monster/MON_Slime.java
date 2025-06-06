package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import obj.OBJ_Coin;
import obj.OBJ_Slimeball;

public class MON_Slime extends Entity {
    
    GamePanel gp;

    public MON_Slime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = 2;
        name = "Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxHealth = 4;
        health = maxHealth;
        attack = 3;
        defense = 0;
        exp = 2;
        Random random = new Random();
        gold = random.nextInt(1, 3);
        projectile = new OBJ_Slimeball(gp);

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
        
        if (onPath == true) {

            // CHECK CHASE

            checkStopChasingOrNot(gp.player, 15, 50);

            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

            // PROJECTILE

            checkShootOrNot(100, 30);

    } else {

        // CHECK CHASE

        checkStartChasingOrNot(gp.player, 5, 25);

        // GIVE RANDOM DIRECTION

        getRandomDirection();

    }
}

    public void damageReaction() {
        actionLockCounter = 0;
        onPath = true;
        }

    public void checkDrop() {
        
        int i = new Random().nextInt(100);
    
        if (i == 0) {
            dropItem(new OBJ_Coin(gp));
        }
    }
}