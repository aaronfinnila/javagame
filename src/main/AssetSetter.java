package main;

import monster.MON_Slime;
import npc.NPC_Claire;
import npc.NPC_DialogueHolder;
import npc.NPC_Edward;
import npc.NPC_Fear;
import npc.NPC_Kalsu;
import npc.NPC_Michael;
import npc.NPC_Percival;
import npc.NPC_Rock;
import npc.NPC_Rubert;
import obj.OBJ_Bench;
import obj.OBJ_Bookshelf;
import obj.OBJ_Bow_Default;
import obj.OBJ_Casino;
import obj.OBJ_Cave_Entrance;
import obj.OBJ_Hitbox;
import obj.OBJ_House1;
import obj.OBJ_House1_Interior;
import obj.OBJ_House2;
import obj.OBJ_House2_Interior;
import obj.OBJ_House3;
import obj.OBJ_House3_Interior;
import obj.OBJ_Lantern_Inv;
import obj.OBJ_Longsword;
import obj.OBJ_Nightingale;
import obj.OBJ_Rowboat;
import obj.OBJ_Store;
import obj.OBJ_Store_Interior;
import obj.OBJ_Table;
import obj.OBJ_Waterfountain;
import tile_interactive.IT_SmallRock;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        int mapNum = 0;
        int i = 0;

        // INTRO ISLAND

        gp.obj[mapNum][i] = new OBJ_Rowboat(gp);
        gp.obj[mapNum][i].worldX = 33*gp.tileSize;
        gp.obj[mapNum][i].worldY = 31*gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Bow_Default(gp);
        gp.obj[mapNum][i].worldX = 30*gp.tileSize;
        gp.obj[mapNum][i].worldY = 31*gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Lantern_Inv(gp);
        gp.obj[mapNum][i].worldX = 30*gp.tileSize;
        gp.obj[mapNum][i].worldY = 32*gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Nightingale(gp);
        gp.obj[mapNum][i].worldX = 30*gp.tileSize;
        gp.obj[mapNum][i].worldY = 24*gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Longsword(gp);
        gp.obj[mapNum][i].worldX = 31*gp.tileSize;
        gp.obj[mapNum][i].worldY = 24*gp.tileSize;
        i++;

        mapNum++;
        i = 0;

        // TREASURE ISLAND

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
        gp.obj[mapNum][i].worldX = 65*gp.tileSize;
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
        // hitboxes for store
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 68*gp.tileSize-10;
        gp.obj[mapNum][i].worldY = 27*gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 65*gp.tileSize-50;
        gp.obj[mapNum][i].worldY = 27*gp.tileSize;
        gp.obj[mapNum][i].solidArea.width = 20;
        gp.obj[mapNum][i].solidArea.height = gp.tileSize*2+20;
        i++;
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 69*gp.tileSize-50;
        gp.obj[mapNum][i].worldY = 27*gp.tileSize;
        gp.obj[mapNum][i].solidArea.width = 20;
        gp.obj[mapNum][i].solidArea.height = gp.tileSize*2+20;
        i++;
        gp.obj[mapNum][i] = new OBJ_Cave_Entrance(gp);
        gp.obj[mapNum][i].worldX = 66*gp.tileSize;
        gp.obj[mapNum][i].worldY = 2;
        i++;
        gp.obj[mapNum][i] = new OBJ_House3(gp);
        gp.obj[mapNum][i].worldX = 38*gp.tileSize;
        gp.obj[mapNum][i].worldY = 2*gp.tileSize - 20;
        i++;
        gp.obj[mapNum][i] = new OBJ_Casino(gp);
        gp.obj[mapNum][i].worldX = 33*gp.tileSize;
        gp.obj[mapNum][i].worldY = 44*gp.tileSize;
        i++;
        // hitboxes for casino
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 37*gp.tileSize-10;
        gp.obj[mapNum][i].worldY = 52*gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 34*gp.tileSize-50;
        gp.obj[mapNum][i].worldY = 52*gp.tileSize;
        gp.obj[mapNum][i].solidArea.width = 20;
        gp.obj[mapNum][i].solidArea.height = gp.tileSize*2+20;
        i++;
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 38*gp.tileSize-50;
        gp.obj[mapNum][i].worldY = 52*gp.tileSize;
        gp.obj[mapNum][i].solidArea.width = 20;
        gp.obj[mapNum][i].solidArea.height = gp.tileSize*2+20;
        i++;


        mapNum++;
        i = 0;

        // HOUSE1

        gp.obj[mapNum][i] = new OBJ_House1_Interior(gp);
        gp.obj[mapNum][i].worldX = 45*gp.tileSize-12;
        gp.obj[mapNum][i].worldY = 42*gp.tileSize-12;
        i++;
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 47*gp.tileSize-95;
        gp.obj[mapNum][i].worldY = 45*gp.tileSize-32;
        gp.obj[mapNum][i].solidArea.width = 40;
        gp.obj[mapNum][i].solidArea.height = gp.tileSize+20;
        i++;
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 47*gp.tileSize-55;
        gp.obj[mapNum][i].worldY = 45*gp.tileSize-32;
        gp.obj[mapNum][i].solidArea.width = 40;
        gp.obj[mapNum][i].solidArea.height = 10+gp.tileSize*2;
        i++;
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 48*gp.tileSize-32;
        gp.obj[mapNum][i].worldY = 45*gp.tileSize-32;
        gp.obj[mapNum][i].solidArea.width = 15;
        gp.obj[mapNum][i].solidArea.height = gp.tileSize;
        i++;

        mapNum++;
        i = 0;

        // HOUSE2

        gp.obj[mapNum][i] = new OBJ_House2_Interior(gp);
        gp.obj[mapNum][i].worldX = 45*gp.tileSize-12;
        gp.obj[mapNum][i].worldY = 42*gp.tileSize-12;
        i++;
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 54*gp.tileSize+9;
        gp.obj[mapNum][i].worldY = 44*gp.tileSize;
        gp.obj[mapNum][i].solidArea.width = 40;
        gp.obj[mapNum][i].solidArea.height = 70;
        i++;

        mapNum++;
        i = 0;
        
        // STORE
        
        gp.obj[mapNum][i] = new OBJ_Store_Interior(gp);
        gp.obj[mapNum][i].worldX = 45*gp.tileSize-12;
        gp.obj[mapNum][i].worldY = 42*gp.tileSize-12;
        i++;
        // shelves
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 52*gp.tileSize;
        gp.obj[mapNum][i].worldY = 45*gp.tileSize;
        gp.obj[mapNum][i].solidArea.width = 120*3;
        gp.obj[mapNum][i].solidArea.height = 27;
        i++;
        // plant
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 57*gp.tileSize+30;
        gp.obj[mapNum][i].worldY = 45*gp.tileSize;
        gp.obj[mapNum][i].solidArea.width = 30*3;
        gp.obj[mapNum][i].solidArea.height = 37*3;
        i++;
        // plant table
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 53*gp.tileSize+18;
        gp.obj[mapNum][i].worldY = 49*gp.tileSize-40;
        gp.obj[mapNum][i].solidArea.width = 172;
        gp.obj[mapNum][i].solidArea.height = 100;
        i++;
        // meat table
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 53*gp.tileSize+18;
        gp.obj[mapNum][i].worldY = 52*gp.tileSize-19;
        gp.obj[mapNum][i].solidArea.width = 172;
        gp.obj[mapNum][i].solidArea.height = 100;
        i++;
        // flour shelf
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 47*gp.tileSize+12;
        gp.obj[mapNum][i].worldY = 52*gp.tileSize-19;
        gp.obj[mapNum][i].solidArea.width = 172;
        gp.obj[mapNum][i].solidArea.height = 100;
        i++;
        // flower shelf
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 47*gp.tileSize+12;
        gp.obj[mapNum][i].worldY = 48*gp.tileSize+10;
        gp.obj[mapNum][i].solidArea.width = 172;
        gp.obj[mapNum][i].solidArea.height = 100;
        i++;
        // botright flower
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 58*gp.tileSize;
        gp.obj[mapNum][i].worldY = 54*gp.tileSize+16;
        gp.obj[mapNum][i].solidArea.width = 16*3;
        gp.obj[mapNum][i].solidArea.height = 37*3;
        i++;
        // botleft flower
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 45*gp.tileSize+16;
        gp.obj[mapNum][i].worldY = 52*gp.tileSize+28;
        gp.obj[mapNum][i].solidArea.width = 16*2;
        gp.obj[mapNum][i].solidArea.height = 37*2;
        i++;
        // topleft flower
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 45*gp.tileSize+31;
        gp.obj[mapNum][i].worldY = 48*gp.tileSize+16;
        gp.obj[mapNum][i].solidArea.width = 32;
        gp.obj[mapNum][i].solidArea.height = 74;
        i++;
        // desk
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 47*gp.tileSize-70;
        gp.obj[mapNum][i].worldY = 45*gp.tileSize+25;
        gp.obj[mapNum][i].solidArea.width = 305;
        gp.obj[mapNum][i].solidArea.height = 49;
        i++;
        
        mapNum++;
        i = 0;
        
        // STOREMAPNIGHT
        
        gp.obj[mapNum][i] = new OBJ_Store_Interior(gp);
        gp.obj[mapNum][i].worldX = 45*gp.tileSize-12;
        gp.obj[mapNum][i].worldY = 42*gp.tileSize-12;
        i++;
        // shelves
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 51*gp.tileSize;
        gp.obj[mapNum][i].worldY = 45*gp.tileSize;
        gp.obj[mapNum][i].solidArea.width = 150*3;
        gp.obj[mapNum][i].solidArea.height = 27;
        i++;
        // plant
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 57*gp.tileSize+30;
        gp.obj[mapNum][i].worldY = 45*gp.tileSize;
        gp.obj[mapNum][i].solidArea.width = 30*3;
        gp.obj[mapNum][i].solidArea.height = 37*3;
        i++;
        // plant table
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 53*gp.tileSize+18;
        gp.obj[mapNum][i].worldY = 49*gp.tileSize-40;
        gp.obj[mapNum][i].solidArea.width = 172;
        gp.obj[mapNum][i].solidArea.height = 100;
        i++;
        // meat table
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 53*gp.tileSize+18;
        gp.obj[mapNum][i].worldY = 52*gp.tileSize-19;
        gp.obj[mapNum][i].solidArea.width = 172;
        gp.obj[mapNum][i].solidArea.height = 100;
        i++;
        // flour shelf
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 47*gp.tileSize+12;
        gp.obj[mapNum][i].worldY = 52*gp.tileSize-19;
        gp.obj[mapNum][i].solidArea.width = 172;
        gp.obj[mapNum][i].solidArea.height = 100;
        i++;
        // flower shelf
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 47*gp.tileSize+12;
        gp.obj[mapNum][i].worldY = 48*gp.tileSize+10;
        gp.obj[mapNum][i].solidArea.width = 172;
        gp.obj[mapNum][i].solidArea.height = 100;
        i++;
        // botright flower
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 58*gp.tileSize;
        gp.obj[mapNum][i].worldY = 54*gp.tileSize+16;
        gp.obj[mapNum][i].solidArea.width = 16*3;
        gp.obj[mapNum][i].solidArea.height = 37*3;
        i++;
        // botleft flower
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 45*gp.tileSize+16;
        gp.obj[mapNum][i].worldY = 52*gp.tileSize+28;
        gp.obj[mapNum][i].solidArea.width = 16*2;
        gp.obj[mapNum][i].solidArea.height = 37*2;
        i++;
        // topleft flower
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 45*gp.tileSize+31;
        gp.obj[mapNum][i].worldY = 48*gp.tileSize+16;
        gp.obj[mapNum][i].solidArea.width = 32;
        gp.obj[mapNum][i].solidArea.height = 74;
        i++;
        // desk
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 47*gp.tileSize-50;
        gp.obj[mapNum][i].worldY = 45*gp.tileSize+35;
        gp.obj[mapNum][i].solidArea.width = 280;
        gp.obj[mapNum][i].solidArea.height = 45;
        i++;

        mapNum++;
        i = 0;

        // STOREMAPSECRET

        mapNum++;
        i = 0;

        // DUNGEON

        mapNum++;
        i = 0;
        
        // HOUSE3;

        gp.obj[mapNum][i] = new OBJ_House3_Interior(gp);
        gp.obj[mapNum][i].worldX = 45*gp.tileSize-12;
        gp.obj[mapNum][i].worldY = 42*gp.tileSize-12;
        i++;
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 47*gp.tileSize-95;
        gp.obj[mapNum][i].worldY = 45*gp.tileSize-32;
        gp.obj[mapNum][i].solidArea.width = 40;
        gp.obj[mapNum][i].solidArea.height = gp.tileSize+20;
        i++;
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 47*gp.tileSize-55;
        gp.obj[mapNum][i].worldY = 45*gp.tileSize-32;
        gp.obj[mapNum][i].solidArea.width = 40;
        gp.obj[mapNum][i].solidArea.height = 10+gp.tileSize*2;
        i++;
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 48*gp.tileSize-32;
        gp.obj[mapNum][i].worldY = 45*gp.tileSize-32;
        gp.obj[mapNum][i].solidArea.width = 15;
        gp.obj[mapNum][i].solidArea.height = gp.tileSize;
        i++;
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 45*gp.tileSize-12;
        gp.obj[mapNum][i].worldY = 52*gp.tileSize+15;
        gp.obj[mapNum][i].solidArea.width = 45;
        gp.obj[mapNum][i].solidArea.height = 60;
        i++;
        gp.obj[mapNum][i] = new OBJ_Hitbox(gp);
        gp.obj[mapNum][i].worldX = 54*gp.tileSize+28;
        gp.obj[mapNum][i].worldY = 54*gp.tileSize+30;
        gp.obj[mapNum][i].solidArea.width = 60;
        gp.obj[mapNum][i].solidArea.height = 60;
        i++;
        
        mapNum++;
        i = 0;
    }
    
    public void setNPC() {
        
        int mapNum = 0;
        int i = 0;
        
        // INTRO ISLAND

        gp.npc[mapNum][i] = new NPC_Rock(gp);
        gp.npc[mapNum][i].worldX = 24 * gp.tileSize;
        gp.npc[mapNum][i].worldY = 21 * gp.tileSize;
        i++;
        
        mapNum++;
        i = 0;

        // TREASURE ISLAND

        mapNum++;
        i=0;

        // HOUSE1

        gp.npc[mapNum][i] = new NPC_Claire(gp);
        gp.npc[mapNum][i].worldX = 47 * gp.tileSize;
        gp.npc[mapNum][i].worldY = 50 * gp.tileSize;
        i++;
        gp.npc[mapNum][i] = new NPC_Rubert(gp);
        gp.npc[mapNum][i].worldX = 50 * gp.tileSize;
        gp.npc[mapNum][i].worldY = 50 * gp.tileSize;
        i++;
        gp.npc[mapNum][i] = new OBJ_Table(gp);
        gp.npc[mapNum][i].worldX = 53*gp.tileSize;
        gp.npc[mapNum][i].worldY = 44*gp.tileSize-15;
        i++;
        gp.npc[mapNum][i] = new NPC_DialogueHolder(gp);
        gp.npc[mapNum][i].worldX = 47*gp.tileSize;
        gp.npc[mapNum][i].worldY = 45*gp.tileSize;
        gp.npc[mapNum][i].dialogueSet = 0;
        i++;

        mapNum++;
        i=0;

        // HOUSE2

        gp.npc[mapNum][i] = new NPC_Kalsu(gp);
        gp.npc[mapNum][i].worldX = 50 * gp.tileSize;
        gp.npc[mapNum][i].worldY = 45 * gp.tileSize;
        i++;

        mapNum++;
        i=0;

        // STORE

        gp.npc[mapNum][i] = new NPC_Michael(gp);
        gp.npc[mapNum][i].worldX = 48 * gp.tileSize;
        gp.npc[mapNum][i].worldY = 45 * gp.tileSize-15;
        gp.npc[mapNum][i].solidArea.height = 90;
        i++;

        mapNum++;
        i=0;

        // STOREMAPNIGHT

        mapNum++;
        i=0;
        
        // STOREMAPSECRET

        mapNum++;
        i=0;
        
        // DUNGEON
        
        gp.npc[mapNum][i] = new NPC_Fear(gp);
        gp.npc[mapNum][i].worldX = 55 * gp.tileSize;
        gp.npc[mapNum][i].worldY = 30 * gp.tileSize;
        
        mapNum++;
        i=0;
        
        // HOUSE3

        gp.npc[mapNum][i] = new OBJ_Bookshelf(gp);
        gp.npc[mapNum][i].worldX = 53*gp.tileSize-30;
        gp.npc[mapNum][i].worldY = 42*gp.tileSize+25;
        i++;
        
        gp.npc[mapNum][i] = new NPC_DialogueHolder(gp);
        gp.npc[mapNum][i].worldX = 47*gp.tileSize;
        gp.npc[mapNum][i].worldY = 45*gp.tileSize;
        gp.npc[mapNum][i].dialogueSet = 1;
        i++;

        gp.npc[mapNum][i] = new NPC_Percival(gp);
        gp.npc[mapNum][i].worldX = 47*gp.tileSize;
        gp.npc[mapNum][i].worldY = 46*gp.tileSize;
        i++;

        gp.npc[mapNum][i] = new OBJ_Table(gp);
        gp.npc[mapNum][i].worldX = 49*gp.tileSize;
        gp.npc[mapNum][i].worldY = 44*gp.tileSize-15;
        // set other table variant by changing direction
        gp.npc[mapNum][i].direction = "up";
        gp.npc[mapNum][i].dialogueSet = 1;
        i++;

        mapNum++;
        i = 0;

        // CASINO

        gp.npc[mapNum][i] = new NPC_Edward(gp);
        gp.npc[mapNum][i].worldX = 51*gp.tileSize;
        gp.npc[mapNum][i].worldY = 46*gp.tileSize;
        i++;
    }

    public void setMonster() {

        int mapNum = 0;
        int i = 0;

        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = 33 * gp.tileSize;
        gp.monster[mapNum][i].worldY = 22 * gp.tileSize;
        i++;

        mapNum++;
        i = 0;
        
        mapNum++;
        i = 0;

        mapNum++;
        i = 0;
    }

    public void setInteractiveTile() {

        int mapNum = 0;
        int i = 0;

        gp.iTile[mapNum][i] = new IT_SmallRock(gp, 17, 30);
        i++;
        gp.iTile[mapNum][i] = new IT_SmallRock(gp, 17, 29);
        i++;
        gp.iTile[mapNum][i] = new IT_SmallRock(gp, 17, 28);
        i++;
    }
}