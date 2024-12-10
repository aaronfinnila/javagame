package obj;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_DoorUpper extends SuperObject {

    GamePanel gp;

    public OBJ_DoorUpper(GamePanel gp) {

        this.gp = gp;

        name = "DoorUpper";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door_upper.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
