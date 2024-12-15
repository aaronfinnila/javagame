package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;

public class Player extends Entity {
    
    KeyHandler keyH;
    TileManager tileM;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    int standCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH, TileManager tileM) {

        super(gp);
        this.keyH = keyH;
        this.tileM = tileM;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {

        worldX = 1150;
        worldY = 1150;
        speed = 4;
        direction = "down";
    }
public void getPlayerImage() {

    up1 = setup("/res/player/boy_up_1");
    up2 = setup("/res/player/boy_up_2");
    down1 = setup("/res/player/boy_down_1");
    down2 = setup("/res/player/boy_down_2");
    left1 = setup("/res/player/boy_left_1");
    left2 = setup("/res/player/boy_left_2");
    right1 = setup("/res/player/boy_right_1");
    right2 = setup("/res/player/boy_right_2");
}

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

        if (keyH.upPressed == true) {
            direction = "up";
         }
         else if (keyH.downPressed == true) {
            direction = "down";
         }
         else if (keyH.leftPressed == true) {
            direction = "left";
         }
         else if (keyH.rightPressed == true) {
            direction = "right";
         }

         // CHECK TILE COLLISION

         collisionOn = false;
         gp.cChecker.checkTile(this);

         // CHECK OBJECT COLLISION 

         int objIndex = gp.cChecker.checkObject(this, true);
         pickUpObject(objIndex);

         // CHECK NPC COLLISION

         int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
         interactNPC(npcIndex);

         // IF COLLISION IS FALSE, PLAYER CAN MOVE

         if (collisionOn == false) {
            switch(direction) {
                case "up": worldY -= speed;
                    break;
                case "down": worldY += speed;
                    break;
                case "left": worldX -= speed;
                    break;
                case "right": worldX += speed;
                    break;
            }
         }

         spriteCounter++;
         if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            }
            else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
         else {
            standCounter++;

            if(standCounter == 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        
        if (i != 999) {
            
            String objectName = gp.obj[i].name;

            switch(objectName) {

                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key!");
                    break;
                case "Door":
                    break;

                case "DoorLower":
                    break;

                case "DoorUpper":
                    break;

                case "Chest":
                if (hasKey != 0) {
                    gp.playSE(1);
                    gp.ui.showMessage("You got an item!");
                    gp.obj[i].usedObject = true;
                    hasKey--;
                } else {
                    if (gp.obj[i].usedObject == false) {
                        gp.ui.showMessage("You need a key!");
                    }
                }
                break;
                case "Boots":
                    gp.playSE(2);
                    speed += 1;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed up!");
                    break;
                case "Questionmark":
                if (!gp.actionActive) {
                gp.obj[i] = null;
                gp.starttime = System.currentTimeMillis();
                gp.playSE(4);
                gp.stopMusic();
                gp.obj[3].worldX = 3 * gp.tileSize;
                gp.obj[3].worldY = 37 * gp.tileSize;
                gp.actionActive = true;
                }
                break;
                }
            }
        }

        public void interactNPC(int i) {

            if (i != 999) {
            }
        }
    
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
            if(spriteNum == 1) {
                image = down1;
            }
            if (spriteNum == 2) {
                image = down2;
            }
                break;
            case "left": 
            if (spriteNum == 1) {
                image = left1;
            }
            if (spriteNum == 2) {
                image = left2;
            }
                break;
            case "right":
            if (spriteNum == 1) {
                image = right1;
            }
            if (spriteNum == 2) {
                image = right2;
            }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize ,null);
    }
}
