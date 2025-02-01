package obj;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Arrow extends Projectile {

    GamePanel gp;

    public OBJ_Arrow(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Arrow";
        speed = 8;
        maxHealth = 80;
        health = maxHealth;
        attack = 1;
        alive = false;
        up1 = setup("/res/projectile/arrow_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/projectile/arrow_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/projectile/arrow_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/projectile/arrow_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/projectile/arrow_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/projectile/arrow_right_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/projectile/arrow_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/projectile/arrow_left_2", gp.tileSize, gp.tileSize);

    }
}
