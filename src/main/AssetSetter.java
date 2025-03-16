package main;

import entity.NPC_Rock;
import obj.OBJ_House1;
import obj.OBJ_House2;
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
        i++;
        mapNum += 1;

        gp.obj[mapNum][i] = new OBJ_Rowboat(gp);
        gp.obj[mapNum][i].worldX = 12*gp.tileSize;
        gp.obj[mapNum][i].worldY = 38*gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_House1(gp);
        gp.obj[mapNum][i].worldX = 40*gp.tileSize;
        gp.obj[mapNum][i].worldY = 26*gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_House2(gp);
        gp.obj[mapNum][i].worldX = 46*gp.tileSize;
        gp.obj[mapNum][i].worldY = 26*gp.tileSize;
        i++;
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