package tile_interactive;

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
}