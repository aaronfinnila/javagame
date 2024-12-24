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
        maxHealth = 6;
        health = maxHealth;
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

         // CHECK EVENT COLLISION

         gp.eHandler.checkEvent();

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
                if (hasKey != 0 && gp.keyH.ePressed == true) {
                    gp.playSE(1);
                    gp.ui.showMessage("You found an item!");
                    gp.obj[5].worldX = 10 * gp.tileSize;
                    gp.obj[5].worldY = 7 * gp.tileSize;
                    gp.obj[i].usedObject = true;
                    hasKey--;
                } else {
                    if (gp.obj[i].usedObject == false && hasKey == 0 && keyH.ePressed == true) {
                        gp.ui.showMessage("You need a key!");
                    }
                }
                break;
                case "Boots":
                    gp.playSE(2);
                    speed += 1;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got boots!");
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
                gp.soundeffectActive = true;
                }
                break;
                }
            }
        }

        public void interactNPC(int i) {

            if (i != 999) {
                
                if (i == 1) {
                    if (gp.keyH.ePressed == true) {
                        gp.gameState = gp.dialogueState;
                        gp.npc[i].speak();
                        if (gp.player.speed > 4) {
                            gp.npc[1].dialogues[5] = "So you increased your speed! Impressive.\nI suppose you're ready to hear more.";
                            gp.npc[1].dialogues[6] = "A long, long time ago, a strong warrior\ncame to this island.";
                            gp.npc[1].dialogues[7] = "He was from Midland, and he came\nto search for the legendary\ntreasure he had heard so much about.";
                            gp.npc[1].dialogues[8] = "With sword and bow in tow,\nhe begun his adventure.\nBut very soon he realized...";
                            gp.npc[1].dialogues[9] = "That this was no ordinary island.";
                            gp.npc[1].dialogues[10] = "That's enough for now.\nIncrease your speed even more,\nand I might tell you the rest.";
                        }
                        if (gp.player.speed > 5) {
                            gp.npc[1].dialogues[11] = "You seem faster. I think you're ready\nto hear some more...";
                            gp.npc[1].dialogues[12] = "After spending some time on the island,\nthe warrior noticed that something\nwasn't right.";
                            gp.npc[1].dialogues[13] = "The people were all really friendly,\nthe place was beautiful and the scenery\nwas out of this world.";
                            gp.npc[1].dialogues[14] = "But soon he noticed something strange.";
                            gp.npc[1].dialogues[15] = "One night when he suddenly woke up,\nhe heard strange noises.";
                            gp.npc[1].dialogues[16] = "It sounded like they were coming from\nthe town square. The following day he\ndecided to ask someone about it.";
                            gp.npc[1].dialogues[17] = "However, when he mentioned it to\nany of the townsfolk, they started\nacting strange.";
                            gp.npc[1].dialogues[18] = "It was like they were avoiding the subject.";
                            gp.npc[1].dialogues[19] = "Like they were hiding something.";
                            gp.npc[1].dialogues[20] = "Alright, that's it for now.\nCome talk to me again when you've\nimproved your speed.";
                        }
                    }
                }
                else if (gp.keyH.ePressed == true) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
            gp.keyH.ePressed = false;
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
