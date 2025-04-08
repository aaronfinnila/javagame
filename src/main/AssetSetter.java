package main;

import entity.NPC_Kalsu;
import entity.NPC_Rock;
import entity.NPC_Rubert;
import obj.OBJ_Bench;
import obj.OBJ_House1;
import obj.OBJ_House2;
import obj.OBJ_Rowboat;
import obj.OBJ_Store;
import obj.OBJ_Waterfountain;

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
        gp.obj[mapNum][i].worldY = 26*gp.tileSize - 20;
        i++;
        gp.obj[mapNum][i] = new OBJ_House2(gp);
        gp.obj[mapNum][i].worldX = 46*gp.tileSize;
        gp.obj[mapNum][i].worldY = 26*gp.tileSize - 20;
        i++;
        gp.obj[mapNum][i] = new OBJ_Waterfountain(gp);
        gp.obj[mapNum][i].worldX = 67*gp.tileSize;
        gp.obj[mapNum][i].worldY = 36*gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Bench(gp);
        gp.obj[mapNum][i].worldX = 56*gp.tileSize;
        gp.obj[mapNum][i].worldY = 36*gp.tileSize-20;
        i++;
        gp.obj[mapNum][i] = new OBJ_Store(gp);
        gp.obj[mapNum][i].worldX = 64*gp.tileSize;
        gp.obj[mapNum][i].worldY = 19*gp.tileSize;
        i++;
    }

    public void setNPC() {

        int mapNum = 0;
        int i = 0;

        gp.npc[mapNum][i] = new NPC_Rock(gp);
        gp.npc[mapNum][i].worldX = 24 * gp.tileSize;
        gp.npc[mapNum][i].worldY = 21 * gp.tileSize;
        i++;

        mapNum++;
        i = 0;

        mapNum++;
        i=0;

        gp.npc[mapNum][i] = new NPC_Rubert(gp);
        gp.npc[mapNum][i].worldX = 50 * gp.tileSize;
        gp.npc[mapNum][i].worldY = 50 * gp.tileSize;
        i++;

        mapNum++;
        i=0;

        gp.npc[mapNum][i] = new NPC_Kalsu(gp);
        gp.npc[mapNum][i].worldX = 50 * gp.tileSize;
        gp.npc[mapNum][i].worldY = 45 * gp.tileSize;
    }

    public void setMonster() {

/*         int mapNum = 0; */
    }

    public void setInteractiveTile() {

/*         int mapNum = 0;
        int i = 0; */
    }
}