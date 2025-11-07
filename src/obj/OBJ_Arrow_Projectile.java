package obj;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Arrow_Projectile extends Projectile {

    public final static String objName = "Arrow_Projectile";

    GamePanel gp;

    public OBJ_Arrow_Projectile(GamePanel gp) {
        
        super(gp);
        this.gp = gp;

        name = objName;
        speed = 8;
        maxHealth = 80;
        health = maxHealth;
        attack = 1;
        useCost = 1;
        price = 15;
        type = type_projectile;
        alive = false;
        getImage();
    }
    
    public void getImage() {

        up1 = setup("/res/projectile/arrow_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/projectile/arrow_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/projectile/arrow_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/projectile/arrow_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/projectile/arrow_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/projectile/arrow_right_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/projectile/arrow_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/projectile/arrow_left_2", gp.tileSize, gp.tileSize);
        image = setup("/res/objects/arrow", 30, 30);
    }

    public boolean haveResource(Entity user) {

        boolean haveResource = false;
        if (user.arrows >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }

    public void subtractResource(Entity user) {

        user.arrows -= useCost;
    }

    public void use(Entity entity) {
        
        gp.player.arrows += 5;
        gp.ui.showMessage("You found Arrows!");
        gp.ui.showGoldMessage("You received 5 Arrows!");
        gp.playSE(13);
    }
}
