package main;

import entity.Entity;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = false;
    int tempMap, tempCol, tempRow;
    public Entity eventMaster;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventMaster = new Entity(gp);
        eventMaster.name = "eventMaster";
/*         eventMaster.dialogueSet = 0; */


        eventRect = new EventRect[gp.maxMap][gp.maxMapSize][gp.maxMapSize];

        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxMapSize && row < gp.maxMapSize) {

            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxMapSize) {
                col = 0;
                row++;
                if (row == gp.maxMapSize) {
                    row = 0;
                    map++;
                }
            }
        }

        setDialogue();
    }

    public void setDialogue() {
        
        eventMaster.dialogues[0][0] = "You fall into a pit!";
        eventMaster.dialogues[1][0] = "    The goddess statue fills you with joy.\n    Your health has been replenished.\n    (Progress has been saved)";
    }

    public void checkEvent(){

        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }
        if (canTouchEvent == true) {
            
            // INTRO ISLAND
            
            if (hit(gp.introislandMap,16, 26, "up") == true && gp.keyH.ePressed == true) {
                healingStatue(gp.dialogueState);
            }
            if (hit(gp.introislandMap,17, 26, "up") == true && gp.keyH.ePressed == true) {
                healingStatue(gp.dialogueState);
            }

            // TREASURE ISLAND

            if (hit(gp.treasureislandMap,42,33,"up") == true && gp.keyH.ePressed == true) {
                teleportPlayer(gp.house1Map, 50, 56, gp.inside);
            }
            if (hit(gp.treasureislandMap,48,33,"up") == true && gp.keyH.ePressed == true) {
                teleportPlayer(gp.house2Map, 50, 56, gp.inside);
            }
            if (hit(gp.treasureislandMap,40,9,"up") == true && gp.keyH.ePressed == true) {
                teleportPlayer(gp.house3Map, 50, 56, gp.inside);
            }
            if (hit(gp.treasureislandMap,66,27,"up") == true && gp.keyH.ePressed == true) {
                if (gp.dayState().equals("night")) {
                    if (gp.player.hasCreamorKey == true) {
                        gp.player.lightUpdated = true;
                        teleportPlayer(gp.storeMapNight, 47, 56, gp.dungeon);
                    } else {
                        gp.playSE(23);
                    }
                } else {
                    teleportPlayer(gp.storeMap, 47, 56, gp.inside);
                }
            }
            if (hit(gp.treasureislandMap,35,52,"up") == true && gp.keyH.ePressed == true) {
                teleportPlayer(gp.casinoMap, 52, 56, gp.inside);
            }
            
            // DUNGEON
            
            if (hit(gp.dungeonMap,47,56,"down") == true && gp.keyH.ePressed == true) {
                teleportPlayer(gp.treasureislandMap, 74, 7, gp.outside);
            }

            // HOUSE 1

            if (hit(gp.house1Map,50,56,"down") == true && gp.keyH.ePressed == true) {
                teleportPlayer(gp.treasureislandMap, 42, 33, gp.outside);
            }

            // HOUSE2

            if (hit(gp.house2Map,50,56,"down") == true && gp.keyH.ePressed == true) {
                teleportPlayer(gp.treasureislandMap, 48, 33, gp.outside);
            }

            // HOUSE3

            if (hit(gp.house3Map,50,56,"down") == true && gp.keyH.ePressed == true) {
                teleportPlayer(gp.treasureislandMap, 40, 10, gp.outside);
            }

            // STORE

            if (hit(gp.storeMap,47,56,"down") == true && gp.keyH.ePressed == true) {
                teleportPlayer(gp.treasureislandMap, 66, 27, gp.outside);
            }
            
            // STORE NIGHT
            
            if (hit(gp.storeMapNight,47,56,"down") == true && gp.keyH.ePressed == true) {
                teleportPlayer(gp.treasureislandMap, 66, 27, gp.outside);
            }
            if (hit(gp.storeMapNight,46,45,"up") == true && gp.keyH.ePressed == true) {
                teleportPlayer(gp.storeMapSecret, 47, 56, gp.dungeon);
            }
            
            // STORE SECRET
            
            if (hit(gp.storeMapSecret,47,56,"down") == true && gp.keyH.ePressed == true) {
                teleportPlayer(gp.storeMapNight, 45, 45, gp.dungeon);
            }

            // CASINO

            if (hit(gp.casinoMap,52,56,"down") == true && gp.keyH.ePressed == true) {
                teleportPlayer(gp.treasureislandMap, 35, 52 , gp.outside);
            }
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection){

        boolean hit = false;

        if (map == gp.currentMap) {  
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;
    
            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.equals("any")) {
                    hit = true;
                }
            }
    
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public void damagePit(int map, int col, int row, int gameState) {

        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster, 0);
        gp.player.health--;
        eventRect[map][col][row].eventDone = true;
    }

    public void healingStatue(int gameState) {

        gp.gameState = gameState;

        eventMaster.startDialogue(eventMaster, 1);
        gp.player.health = gp.player.maxHealth;
        gp.aSetter.setMonster();
        gp.saveLoad.save();
    }

    public void teleportPlayer(int map, int col, int row, int area) {

        gp.nextArea = area;
        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        gp.player.lightUpdated = true;
        canTouchEvent = false;
    }
}