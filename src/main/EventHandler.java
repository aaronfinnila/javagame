package main;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = false;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

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
    }

    public void checkEvent(){

        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }
        if (canTouchEvent == true) {
            if (hit(1,42,33,"up") == true && gp.keyH.ePressed == true) {
                gp.tileM.loadMap(2);
                teleportPlayer(2, 50, 56);
            }
            if (hit(2,50,56,"down") == true && gp.keyH.ePressed == true) {
                gp.tileM.loadMap(1);
                teleportPlayer(1, 42, 33);
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
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.health--;
        eventRect[map][col][row].eventDone = true;
        }

    public void healingStatue(int map, int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "The goddess statue fills you with joy.\nYour health has been replenished.";

        gp.player.health = gp.player.maxHealth;
        gp.aSetter.setMonster();
        eventRect[map][col][row].eventDone = true;
    }

    public void teleportPlayer(int map, int col, int row) {

        gp.currentMap = map;
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;
        previousEventX = gp.player.worldX;
        previousEventY = gp.player.worldY;
        canTouchEvent = false;
    }
}