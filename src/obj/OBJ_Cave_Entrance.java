package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Cave_Entrance extends Entity {

    public final static String objName = "House1";

    GamePanel gp;
    public OBJ_Cave_Entrance(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = objName;
        type = type_static_object;
        down1 = setup("/res/objects/cave_entrance", 272*3, 112*3);
        collision = true;
        solidArea.width = 272*3;
        solidArea.height = 107*3;
    }

    public void use(Entity entity) {
        if (gp.keyH.ePressed == true) {
            gp.eHandler.teleportPlayer(gp.dungeonMap, 47, 56, gp.dungeon);
        }

    }
}