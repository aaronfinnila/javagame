package obj;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Slimeball extends Projectile {

    public final static String objName = "Slimeball";

    GamePanel gp;

    public OBJ_Slimeball(GamePanel gp) {

        super(gp);
        this.gp = gp;

        name = objName;
        speed = 6;
        maxHealth = 80;
        health = maxHealth;
        attack = 1;
        type = type_projectile;
        alive = false;
        up1 = setup("/res/projectile/slimeball", 32, 28);
        up2 = setup("/res/projectile/slimeball", 32, 28);
        down1 = setup("/res/projectile/slimeball", 32, 28);
        down2 = setup("/res/projectile/slimeball", 32, 28);
        right1 = setup("/res/projectile/slimeball", 32, 28);
        right2 = setup("/res/projectile/slimeball", 32, 28);
        left1 = setup("/res/projectile/slimeball", 32, 28);
        left2 = setup("/res/projectile/slimeball", 32, 28);
    }

    public void use(Entity entity) {}

    public Color getParticleColor() {
        Color color = new Color(57, 255, 20);
        return color;
    }

    public int getParticleSize() {
        int size = 5; // 5 pixels
        return size;
    }

    public int getParticleSpeed() {
        int speed = 1;
        return speed;
    }

    public int getParticleMaxHealth() {
        int maxHealth = 20;
        return maxHealth;
    }
}