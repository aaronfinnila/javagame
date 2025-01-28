package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import obj.OBJ_Shield_Default;
import obj.OBJ_Sword_Default;
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
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.height = 36;
        attackArea.width = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }
    public void setDefaultValues() {

        worldX = 1150;
        worldY = 1150;
        direction = "down";
        
        speed = 4;
        maxHealth = 6;
        health = maxHealth;
        level = 1;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        gold = 0;
        currentWeapon = new OBJ_Sword_Default(gp);
        currentShield = new OBJ_Shield_Default(gp);
        attack = getAttack();
        defense = getDefense();
    }

    public int getAttack() {
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }

public void getPlayerImage() {

    up1 = setup("/res/player/boy_up_1", gp.tileSize, gp.tileSize);
    up2 = setup("/res/player/boy_up_2", gp.tileSize, gp.tileSize);
    down1 = setup("/res/player/boy_down_1", gp.tileSize, gp.tileSize);
    down2 = setup("/res/player/boy_down_2", gp.tileSize, gp.tileSize);
    left1 = setup("/res/player/boy_left_1", gp.tileSize, gp.tileSize);
    left2 = setup("/res/player/boy_left_2", gp.tileSize, gp.tileSize);
    right1 = setup("/res/player/boy_right_1", gp.tileSize, gp.tileSize);
    right2 = setup("/res/player/boy_right_2", gp.tileSize, gp.tileSize);
}

public void getPlayerAttackImage() {
    attackUp1 = setup("/res/player/boy_attack_up1", gp.tileSize, gp.tileSize*2);
    attackUp2 = setup("/res/player/boy_attack_up2", gp.tileSize, gp.tileSize*2);
    attackLeft1 = setup("/res/player/boy_attack_left1", gp.tileSize*2, gp.tileSize);
    attackLeft2 = setup("/res/player/boy_attack_left2", gp.tileSize*2, gp.tileSize);
    attackDown1 = setup("/res/player/boy_attack_down1", gp.tileSize, gp.tileSize*2);
    attackDown2 = setup("/res/player/boy_attack_down2", gp.tileSize, gp.tileSize*2);
    attackRight1 = setup("/res/player/boy_attack_right1", gp.tileSize*2, gp.tileSize);
    attackRight2 = setup("/res/player/boy_attack_right2", gp.tileSize*2, gp.tileSize);
}

    public void update() {

        if (attacking == true) {
            attacking();
        }

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true
         || keyH.ePressed == true) {

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

         // CHECK LEVELUP

         checkLevelUp();

         // CHECK TILE COLLISION

         collisionOn = false;
         gp.cChecker.checkTile(this);

         // CHECK OBJECT COLLISION 

         int objIndex = gp.cChecker.checkObject(this, true);
         pickUpObject(objIndex);

         // CHECK NPC COLLISION

         int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
         interactNPC(npcIndex);

         // CHECK MONSTER COLLISION 

         int monsterIndex =gp.cChecker.checkEntity(this, gp.monster);
         contactMonster(monsterIndex);

         // CHECK EVENT COLLISION

         gp.eHandler.checkEvent();

         // IF COLLISION IS FALSE, PLAYER CAN MOVE

         if (collisionOn == false && keyH.ePressed == false) {
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

         gp.keyH.ePressed = false;

         moveCounter++;

         if (moveCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            }
            else if (spriteNum == 2) {
                spriteNum = 1;
            }
            moveCounter = 0;
        }
    }
         else {
            standCounter++;

            if(standCounter == 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking() {

        attackCounter++;

        if (attackCounter > 0 && attackCounter < 2) {
            gp.playSE(5);
        }
        if (attackCounter <= 5) {
            attackNum = 1;
        }
        if (attackCounter > 5 && attackCounter <= 25) {
            attackNum = 2;

            // Save current worldX, worldY, solidArea

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX and worldY for attackArea

            switch(direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            // solidArea becomes attackArea

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // Check monster collision with the updated worldX, worldY and solidArea

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            // After checking collision, restore original values
    
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.height = solidAreaHeight;
            solidArea.width = solidAreaWidth;

        }
        if (attackCounter > 25) {
            attackNum = 1;
            attackCounter = 0;
            attacking = false;
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
                    gp.obj[4].worldX = 10 * gp.tileSize;
                    gp.obj[4].worldY = 7 * gp.tileSize + 10;
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
                case "Coin":
                    gp.playSE(1);
                    gold += 5;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You found a coin!");
                    gp.ui.showGoldMessage("You got 5 gold!");
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
                        if (gp.player.level > 1) {
                            gp.npc[1].dialogues[5] = "You're stronger.\nI suppose you're ready to hear more.";
                            gp.npc[1].dialogues[6] = "A long, long time ago, a strong warrior\ncame to this island.";
                            gp.npc[1].dialogues[7] = "He was from Midland, and he came\nto search for the legendary\ntreasure he had heard so much about.";
                            gp.npc[1].dialogues[8] = "With sword and bow in tow, he begun\nhis adventure, filled with excitement,\ntreasure gleaming in his eyes.";
                            gp.npc[1].dialogues[9] = "He headed for that town.\nBut when he got there, he noticed\nsomething strange...";
                            gp.npc[1].dialogues[10] = "That's enough for now. Increase your\nstrength even more if you want to\nhear the rest.";
                        }
                        if (gp.player.level > 2) {
                            gp.npc[1].dialogues[11] = "You seem stronger. I think you're ready\nto hear some more...";
                            gp.npc[1].dialogues[12] = "After spending some more time in the town,\nthe warrior noticed that something\nwasn't right.";
                            gp.npc[1].dialogues[13] = "The people were all really friendly,\nthe place was beautiful and the scenery\nwas out of this world.";
                            gp.npc[1].dialogues[14] = "But one night, when he suddenly woke up,\nhe heard strange noises.";
                            gp.npc[1].dialogues[15] = "It sounded like they were coming from\nthe town square. The following day he\ndecided to ask someone about it.";
                            gp.npc[1].dialogues[16] = "However, when he mentioned it to\nany of the townsfolk, they started\nacting strange.";
                            gp.npc[1].dialogues[17] = "It was like they were avoiding the subject.";
                            gp.npc[1].dialogues[18] = "That town is not what it seems...";
                            gp.npc[1].dialogues[19] = "But I suppose you have no other\nchoice than to go there and\nsee for yourself.";
                            gp.npc[1].dialogues[20] = "Best of luck kiddo...\n";
                            gp.npc[1].dialogues[21] = "Judging from the way you look,\nyou're gonna need it...";
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

    public void contactMonster(int i) {

        if (i != 999) {
            if (invincible == false) {
                gp.playSE(6);
                int damage = gp.monster[i].attack - defense;
                if (damage <= 0) {
                    damage = 1;
                }
                health -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i) {

        if (i != 999) {
            if(gp.monster[i].invincible == false) {
                gp.playSE(7);
                int damage = attack - gp.monster[i].defense;
                if (damage < 0) {
                    damage = 0;
                }
                gp.monster[i].health -= damage;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();
            }

            if (gp.monster[i].health <= 0) {
                gp.monster[i].dying = true;
            }
        }
    }

    public void checkLevelUp() {

        if (exp >= nextLevelExp) {
            level++;
            maxHealth += 2;
            health = maxHealth;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();
            gp.ui.currentDialogue = "You are now level " + level + "!\nYou feel stronger than before!";
            gp.gameState = gp.dialogueState;
            gp.stopMusic();
            gp.playSE(9);
            exp = exp - nextLevelExp;
            nextLevelExp = nextLevelExp*2;
        }
    }
    
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        // DEBUG attackArea

/* 		tempScreenX = screenX + solidArea.x;
		tempScreenY = screenY + solidArea.y;		

		switch(direction) {
		case "up": tempScreenY = screenY - attackArea.height; break;
		case "down": tempScreenY = screenY + attackArea.height; break; 
		case "left": tempScreenX = screenX - attackArea.width; break;
		case "right": tempScreenX = screenX + attackArea.width; break;
		}				
		g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(1));
		g2.drawRect(tempScreenX, tempScreenY, gp.tileSize, gp.tileSize);

        tempScreenX = screenX;
        tempScreenY = screenY;
 */
        switch(direction) {
            case "up":
                if (attacking == false) {
                    if(spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }
                if (attacking == true) {
                    tempScreenY = screenY - gp.tileSize;
                    if(attackNum == 1) {image = attackUp1;}
                    if (attackNum == 2) {image = attackUp2;}
                }
                break;
            case "down":
                if (attacking == false) {
                    if(spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                }
                if (attacking == true) {
                    if(attackNum == 1) {image = attackDown1;}
                    if (attackNum == 2) {image = attackDown2;}
                }
                break;
            case "left": 
                if (attacking == false) {
                    if(spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                }
                if (attacking == true) {
                    tempScreenX = tempScreenX - gp.tileSize;
                    if(attackNum == 1) {image = attackLeft1;}
                    if (attackNum == 2) {image = attackLeft2;}
                }
                break;
            case "right":
                if (attacking == false) {
                    if(spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                }
                if (attacking == true) {
                    if(attackNum == 1) {image = attackRight1;}
                    if (attackNum == 2) {image = attackRight2;}
                }
                break;
        }
        g2.drawImage(image, tempScreenX, tempScreenY,null);
    }
}
