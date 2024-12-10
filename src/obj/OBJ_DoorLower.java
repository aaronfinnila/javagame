package obj;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_DoorLower extends SuperObject {

    GamePanel gp;

    public OBJ_DoorLower(GamePanel gp) {

        this.gp = gp;

        name = "DoorLower";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door_lower.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
