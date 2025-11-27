package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Diamond_Heart extends Entity {

    public final static String objName = "Diamond Heart";
    
    public OBJ_Diamond_Heart(GamePanel gp) {

        super(gp);

        name = objName;
        down1 = setup("/res/objects/diamondheart",gp.tileSize,gp.tileSize);
        image = down1;
        type = type_heart;
        price = 1200;
        description = name + "\n\nThe legendary Heart of Rilk.\nAccording to legend, only\nthree of these exist.";
    }
}
