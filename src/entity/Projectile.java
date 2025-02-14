package entity;

import main.GamePanel;

public class Projectile extends Entity {

    Entity user;

    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {

        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.health = maxHealth;
    }

    public void update() {

        if (user == gp.player) {
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            if (monsterIndex != 999) {
                gp.player.damageMonster(monsterIndex, attack);
                alive = false;
            }
        } else {
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if (gp.player.invincible == false && contactPlayer == true) {
                damagePlayer(attack);
                alive = false;
            }
        }

        switch(direction) {
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }

        health--;
        if (health <= 0) {
            alive = false;
        }
        moveCounter++;
        if (moveCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else {
                spriteNum = 1;
            }
        }
    }
    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        return haveResource;
    }
    public void subtractResource(Entity user) {}
}
