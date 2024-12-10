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
        gp.obj[0].worldX = 1 * gp.tileSize;
        gp.obj[0].worldY = 21 * gp.tileSize; 

        gp.obj[1] = new OBJ_Chest(gp);
        gp.obj[1].worldX = 1 * gp.tileSize;
        gp.obj[1].worldY = 1 * gp.tileSize; 

        gp.obj[2] = new OBJ_DoorUpper(gp);
        gp.obj[2].worldX = 4 * gp.tileSize;
        gp.obj[2].worldY = 5 * gp.tileSize; 

        gp.obj[3] = new OBJ_DoorLower(gp);
        gp.obj[3].worldX = 4 * gp.tileSize;
        gp.obj[3].worldY = 6 * gp.tileSize;
        
        gp.obj[4] = new OBJ_Boots(gp);
        gp.obj[4].worldX = 9 * gp.tileSize;
        gp.obj[4].worldY = 24 * gp.tileSize;

        gp.obj[5] = new OBJ_DoorUpper(gp);
        gp.obj[5].worldX = 6 * gp.tileSize;
        gp.obj[5].worldY = 35 * gp.tileSize; 

        gp.obj[6] = new OBJ_DoorLower(gp);
        gp.obj[6].worldX = 6 * gp.tileSize;
        gp.obj[6].worldY = 36 * gp.tileSize;

        gp.obj[7] = new OBJ_DoorUpper(gp);
        gp.obj[7].worldX = 12 * gp.tileSize;
        gp.obj[7].worldY = 21 * gp.tileSize; 

        gp.obj[8] = new OBJ_DoorLower(gp);
        gp.obj[8].worldX = 12 * gp.tileSize;
        gp.obj[8].worldY = 22 * gp.tileSize;

        gp.obj[9] = new OBJ_Questionmark(gp);
        gp.obj[9].worldX = 5 * gp.tileSize;
        gp.obj[9].worldY = 42 * gp.tileSize;

        gp.obj[10] = new OBJ_Questionmark2(gp);
        gp.obj[10].worldX = 1000 * gp.tileSize;
        gp.obj[10].worldY = 1000 * gp.tileSize;

    }
}