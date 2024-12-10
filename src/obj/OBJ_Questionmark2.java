package obj;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Questionmark2 extends SuperObject {

    GamePanel gp;

    public OBJ_Questionmark2(GamePanel gp) {

        this.gp = gp;

        name = "Questionmark2";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/extra/questionable.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }

    @Override
        public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, 16*gp.tileSize, 12*gp.tileSize, null);
        }
    }
}