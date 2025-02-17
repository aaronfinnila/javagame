package main;

import entity.NPC_Kalsu;
import entity.NPC_Rock;
import entity.NPC_Rubert;
import monster.MON_Slime;
import obj.OBJ_Arrow;
import obj.OBJ_Bow_Default;
import obj.OBJ_Chest;
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

        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 41 * gp.tileSize;
        gp.obj[0].worldY = 42 * gp.tileSize; 

        gp.obj[1] = new OBJ_Chest(gp);
        gp.obj[1].worldX = 8 * gp.tileSize;
        gp.obj[1].worldY = 42 * gp.tileSize; 

        gp.obj[2] = new OBJ_Longsword(gp);
        gp.obj[2].worldX = 41 * gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;

        gp.obj[3] = new OBJ_Bow_Default(gp);
        gp.obj[3].worldX = 35 * gp.tileSize;
        gp.obj[3].worldY = 7 * gp.tileSize;

        gp.obj[4] = new OBJ_Potion_Red(gp);
        gp.obj[4].worldX = 33 * gp.tileSize;
        gp.obj[4].worldY = 7 * gp.tileSize;

        gp.obj[5] = new OBJ_Key(gp);
        gp.obj[5].worldX = 39 * gp.tileSize;
        gp.obj[5].worldY = 42 * gp.tileSize;

        gp.obj[6] = new OBJ_Arrow(gp);
        gp.obj[6].worldX = 35 * gp.tileSize;
        gp.obj[6].worldY = 42 * gp.tileSize; 
    }

    public void setNPC() {

        gp.npc[0] = new NPC_Rubert(gp);
        gp.npc[0].worldX = 35 * gp.tileSize;
        gp.npc[0].worldY = 35 * gp.tileSize;

        gp.npc[1] = new NPC_Rock(gp);
        gp.npc[1].worldX = 22 * gp.tileSize;
        gp.npc[1].worldY = 11 * gp.tileSize;

        gp.npc[2] = new NPC_Kalsu(gp);
        gp.npc[2].worldX = 41 * gp.tileSize;
        gp.npc[2].worldY = 33 * gp.tileSize;
    }

    public void setMonster() {
        
        gp.monster[0] = new MON_Slime(gp);
        gp.monster[0].worldX = gp.tileSize*13;
        gp.monster[0].worldY = gp.tileSize*32;

        gp.monster[1] = new MON_Slime(gp);
        gp.monster[1].worldX = gp.tileSize*16;
        gp.monster[1].worldY = gp.tileSize*32;

        gp.monster[2] = new MON_Slime(gp);
        gp.monster[2].worldX = gp.tileSize*19;
        gp.monster[2].worldY = gp.tileSize*32;

        gp.monster[3] = new MON_Slime(gp);
        gp.monster[3].worldX = gp.tileSize*22;
        gp.monster[3].worldY = gp.tileSize*32;

        gp.monster[4] = new MON_Slime(gp);
        gp.monster[4].worldX = gp.tileSize*24;
        gp.monster[4].worldY = gp.tileSize*32;
    }

    public void setInteractiveTile() {

        int i = 0;
        gp.iTile[i] = new IT_SmallRock(gp,20,20);i++;
        gp.iTile[i] = new IT_SmallRock(gp,21,20);i++;
        gp.iTile[i] = new IT_SmallRock(gp,22,20);i++;
    }
}