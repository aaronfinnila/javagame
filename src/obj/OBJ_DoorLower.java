package obj;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_DoorLower extends SuperObject {

    public OBJ_DoorLower() {

        name = "DoorLower";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door_lower.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
