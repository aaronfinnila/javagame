package main;

import entity.NPC_Rubert;
import obj.OBJ_Boots;
import obj.OBJ_Chest;
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

        gp.obj[2] = new OBJ_Questionmark(gp);
        gp.obj[2].worldX = 10 * gp.tileSize;
        gp.obj[2].worldY = 40 * gp.tileSize;

        gp.obj[3] = new OBJ_Questionmark2(gp);
        gp.obj[3].worldX = 1000 * gp.tileSize;
        gp.obj[3].worldY = 1000 * gp.tileSize;

        gp.obj[4] = new OBJ_Boots(gp);
        gp.obj[4].worldX = 20 * gp.tileSize;
        gp.obj[4].worldY = 20 * gp.tileSize;

    }

    public void setNPC() {
        gp.npc[0] = new NPC_Rubert(gp);
        gp.npc[0].worldX = 35 * gp.tileSize;
        gp.npc[0].worldY = 35 * gp.tileSize;
    }
}