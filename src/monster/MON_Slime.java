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
        int goalCol = (gp.player.worldX + gp.player.solidArea.x + gp.player.solidArea.width/2)/gp.tileSize;
        int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
        searchPath(goalCol, goalRow);

        // PROJECTILE

        int i = new Random().nextInt(100)+1;
        if (i > 99 && projectile.alive == false && shotAvailableCounter == 30) {
            projectile.set(worldX+12, worldY+20, direction, true, this);
            for (int j=0;j<gp.projectile[1].length;j++) {
                if (gp.projectile[gp.currentMap][j] == null) {
                    gp.projectile[gp.currentMap][j] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    } else {
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
}
    // MONSTER PROJECTILE

    public void update() {

        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (yDistance + xDistance)/gp.tileSize;

        if (onPath == false && tileDistance < 5) {

            int i = new Random().nextInt(101);
            if (i > 50) {
                onPath = true;
            }
        }
        if (onPath == true && tileDistance > 15) {
            onPath = false;
        }

    }

    public void damageReaction() {
        actionLockCounter = 0;
        onPath = true;
        }

    public void checkDrop() {
        
        int i = new Random().nextInt(101);
    
        if (i < 2) {
            dropItem(new OBJ_Coin(gp));
        }
    }
}