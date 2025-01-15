package main;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][];

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }

        }
        eventRect[38][10].x = 0;
        eventRect[38][10].y = 16; 
        eventRect[38][10].width = 32;
    }

    public void checkEvent(){
        if (hit(38,14,"any") == true) {damagePit(38, 14, gp.dialogueState);}
        if (hit(38, 10, "up") == true && gp.keyH.ePressed == true && eventRect[38][10].eventDone == false) {healingStatue(38, 10, gp.dialogueState);}
        if (hit(10, 10, "any") == true) {teleportPlayer(10, 10, gp.dialogueState);}
    }

    public boolean hit(int col, int row, String reqDirection){

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;

        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.equals("any")) {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void damagePit(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.health--;
        eventRect[col][row].eventDone = true;
        }

    public void healingStatue(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "The goddess statue fills you with joy.\nYour health has been replenished.";

        gp.player.health = gp.player.maxHealth;
        eventRect[col][row].eventDone = true;
    }

    public void teleportPlayer(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.player.worldX = 1150;
        gp.player.worldY = 1150;
        gp.ui.currentDialogue = "You teleported!";
        eventRect[col][row].eventDone = true;
    }
}