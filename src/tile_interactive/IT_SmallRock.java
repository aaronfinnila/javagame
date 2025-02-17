package tile_interactive;

import main.GamePanel;

public class IT_SmallRock extends InteractiveTile {

    GamePanel gp;

    public IT_SmallRock(GamePanel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        
        down1 = setup("/tiles_interactive/stone", gp.tileSize, gp.tileSize);
        destructible = true;
    }
}

