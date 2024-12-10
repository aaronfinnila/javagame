package obj;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Questionmark extends SuperObject {

    GamePanel gp;

    public OBJ_Questionmark(GamePanel gp) {

        this.gp = gp;

        name = "Questionmark";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/questionmark.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}