package main;

import entity.NPC_Kalsu;
import entity.NPC_Rock;
import entity.NPC_Rubert;
import monster.MON_Slime;
import obj.OBJ_Arrow;
import obj.OBJ_Bow_Default;
import obj.OBJ_Chest;
import obj.OBJ_Hammer;
import obj.OBJ_Key;
import obj.OBJ_Longsword;
import obj.OBJ_Potion_Red;
import tile_interactive.IT_SmallRock;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        int mapNum = 0;

        gp.obj[mapNum][0] = new OBJ_Key(gp);
        gp.obj[mapNum][0].worldX = 41 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 42 * gp.tileSize; 

        gp.obj[mapNum][1] = new OBJ_Chest(gp);
        gp.obj[mapNum][1].worldX = 8 * gp.tileSize;
        gp.obj[mapNum][1].worldY = 42 * gp.tileSize; 

        gp.obj[mapNum][2] = new OBJ_Longsword(gp);
        gp.obj[mapNum][2].worldX = 41 * gp.tileSize;
        gp.obj[mapNum][2].worldY = 7 * gp.tileSize;

        gp.obj[mapNum][3] = new OBJ_Bow_Default(gp);
        gp.obj[mapNum][3].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][3].worldY = 7 * gp.tileSize;

        gp.obj[mapNum][4] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][4].worldX = 33 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 7 * gp.tileSize;

        gp.obj[mapNum][5] = new OBJ_Key(gp);
        gp.obj[mapNum][5].worldX = 39 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 42 * gp.tileSize;

        gp.obj[mapNum][6] = new OBJ_Arrow(gp);
        gp.obj[mapNum][6].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][6].worldY = 42 * gp.tileSize;

        gp.obj[mapNum][7] = new OBJ_Hammer(gp);
        gp.obj[mapNum][7].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][7].worldY = 42 * gp.tileSize;
    }

    public void setNPC() {

        int mapNum = 0;

        gp.npc[mapNum][0] = new NPC_Rubert(gp);
        gp.npc[mapNum][0].worldX = 35 * gp.tileSize;
        gp.npc[mapNum][0].worldY = 35 * gp.tileSize;

        gp.npc[mapNum][1] = new NPC_Rock(gp);
        gp.npc[mapNum][1].worldX = 22 * gp.tileSize;
        gp.npc[mapNum][1].worldY = 11 * gp.tileSize;

        gp.npc[mapNum][2] = new NPC_Kalsu(gp);
        gp.npc[mapNum][2].worldX = 41 * gp.tileSize;
        gp.npc[mapNum][2].worldY = 33 * gp.tileSize;
    }

    public void setMonster() {

        int mapNum = 0;
        
        gp.monster[mapNum][0] = new MON_Slime(gp);
        gp.monster[mapNum][0].worldX = gp.tileSize*13;
        gp.monster[mapNum][0].worldY = gp.tileSize*32;

        gp.monster[mapNum][1] = new MON_Slime(gp);
        gp.monster[mapNum][1].worldX = gp.tileSize*16;
        gp.monster[mapNum][1].worldY = gp.tileSize*32;

        gp.monster[mapNum][2] = new MON_Slime(gp);
        gp.monster[mapNum][2].worldX = gp.tileSize*19;
        gp.monster[mapNum][2].worldY = gp.tileSize*32;

        gp.monster[mapNum][3] = new MON_Slime(gp);
        gp.monster[mapNum][3].worldX = gp.tileSize*22;
        gp.monster[mapNum][3].worldY = gp.tileSize*32;

        gp.monster[mapNum][4] = new MON_Slime(gp);
        gp.monster[mapNum][4].worldX = gp.tileSize*24;
        gp.monster[mapNum][4].worldY = gp.tileSize*32;
    }

    public void setInteractiveTile() {

        int mapNum = 0;
        int i = 0;

        gp.iTile[mapNum][i] = new IT_SmallRock(gp,20,20);i++;
        gp.iTile[mapNum][i] = new IT_SmallRock(gp,21,20);i++;
        gp.iTile[mapNum][i] = new IT_SmallRock(gp,22,20);i++;
    }
}