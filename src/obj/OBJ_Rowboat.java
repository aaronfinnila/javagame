package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Rowboat extends Entity {

    public final static String objName = "Rowboat";

    GamePanel gp;
    public OBJ_Rowboat(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = objName;
        type = type_static_object;
        down1 = setup("/res/objects/rowboat", gp.tileSize*2, 74);
        collision = true;
        solidArea.height = 74;
        solidArea.width = gp.tileSize*2;
    }

    public void use(Entity entity) {
        if (gp.currentMap == 0 && gp.keyH.ePressed == true) {
            gp.eHandler.teleportPlayer(1, 14, 38, gp.outside);
        }
        if (gp.currentMap == 1 && gp.keyH.ePressed == true) {
            gp.eHandler.teleportPlayer(0, 32, 31, gp.outside);
        }
    }
}