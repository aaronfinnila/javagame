package obj;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_DoorUpper extends SuperObject {

    public OBJ_DoorUpper() {

        name = "DoorUpper";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door_upper.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
