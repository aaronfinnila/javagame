package main;

import entity.NPC_Rock;
import obj.OBJ_Rowboat;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int mapNum = 0;
        int i = 0;

        gp.obj[mapNum][i] = new OBJ_Rowboat(gp);
        gp.obj[mapNum][i].worldX = 33*gp.tileSize;
        gp.obj[mapNum][i].worldY = 31*gp.tileSize;
        
/*         gp.obj[mapNum][i] = new OBJ_Bow_Default(gp);
        gp.obj[mapNum][i].worldX = 24*gp.tileSize;
        gp.obj[mapNum][i].worldY = 24*gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Hammer(gp);
        gp.obj[mapNum][i].worldX = 23*gp.tileSize;
        gp.obj[mapNum][i].worldY = 24*gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Longsword(gp);
        gp.obj[mapNum][i].worldX = 22*gp.tileSize;
        gp.obj[mapNum][i].worldY = 24*gp.tileSize; */

    }

    public void setNPC() {

        int mapNum = 0;

        gp.npc[mapNum][0] = new NPC_Rock(gp);
        gp.npc[mapNum][0].worldX = 24 * gp.tileSize;
        gp.npc[mapNum][0].worldY = 21 * gp.tileSize;

    }

    public void setMonster() {

/*         int mapNum = 0; */
    }

    public void setInteractiveTile() {

/*         int mapNum = 0;
        int i = 0; */
    }
}