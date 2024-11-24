package main;

import obj.OBJ_Boots;
import obj.OBJ_Chest;
import obj.OBJ_DoorLower;
import obj.OBJ_DoorUpper;
import obj.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 17 * gp.tileSize;
        gp.obj[0].worldY = 10 * gp.tileSize; 

        gp.obj[1] = new OBJ_Chest();
        gp.obj[1].worldX = 10 * gp.tileSize;
        gp.obj[1].worldY = 14 * gp.tileSize; 

        gp.obj[2] = new OBJ_DoorUpper();
        gp.obj[2].worldX = 3 * gp.tileSize;
        gp.obj[2].worldY = 2 * gp.tileSize; 

        gp.obj[3] = new OBJ_DoorLower();
        gp.obj[3].worldX = 3 * gp.tileSize;
        gp.obj[3].worldY = 3 * gp.tileSize;
        
        gp.obj[4] = new OBJ_Boots();
        gp.obj[4].worldX = 5 * gp.tileSize;
        gp.obj[4].worldY = 5 * gp.tileSize;

        gp.obj[5] = new OBJ_DoorUpper();
        gp.obj[5].worldX = 30 * gp.tileSize;
        gp.obj[5].worldY = 13 * gp.tileSize;  
        
        gp.obj[6] = new OBJ_DoorLower();
        gp.obj[6].worldX = 30 * gp.tileSize;
        gp.obj[6].worldY = 14 * gp.tileSize;
    }

}