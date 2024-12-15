package main;

import obj.OBJ_Boots;
import obj.OBJ_Chest;
import obj.OBJ_DoorLower;
import obj.OBJ_DoorUpper;
import obj.OBJ_Key;
import obj.OBJ_Questionmark;
import obj.OBJ_Questionmark2;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 8 * gp.tileSize;
        gp.obj[0].worldY = 15 * gp.tileSize; 

        gp.obj[1] = new OBJ_Chest(gp);
        gp.obj[1].worldX = 8 * gp.tileSize;
        gp.obj[1].worldY = 7 * gp.tileSize; 

        gp.obj[9] = new OBJ_Questionmark(gp);
        gp.obj[9].worldX = 10 * gp.tileSize;
        gp.obj[9].worldY = 40 * gp.tileSize;

        gp.obj[10] = new OBJ_Questionmark2(gp);
        gp.obj[10].worldX = 1000 * gp.tileSize;
        gp.obj[10].worldY = 1000 * gp.tileSize;

    }
}