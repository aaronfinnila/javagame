package tile_interactive;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class IT_SmallRock extends InteractiveTile {

    GamePanel gp;

    public IT_SmallRock(GamePanel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        
        name = "IT_SmallRock";
        health = 3;
        down1 = setup("/res/tiles_interactive/itrock1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/tiles_interactive/itrock1broken", gp.tileSize, gp.tileSize);
        destructible = true;
    }

        public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;

        if (entity.currentWeapon.type == 9) {
            isCorrectItem = true;
        }
        return isCorrectItem;
    }

        public Color getParticleColor() {
            Color color = new Color(128, 128, 128);
            return color;
        }

        public int getParticleSize() {
            int size = 6; // 6 pixels
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