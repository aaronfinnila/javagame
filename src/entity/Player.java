package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import obj.OBJ_Arrow;
import obj.OBJ_Shield_Default;
import obj.OBJ_Sword_Default;
import tile.TileManager;

public class Player extends Entity {
    
    KeyHandler keyH;
    TileManager tileM;

    public final int screenX;
    public final int screenY;

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
        setItems();
    }

    public void setDefaultValues() {

        worldX = 1150;
        worldY = 1150;
        direction = "down";
        
        speed = 4;
        maxHealth = 6;
        health = maxHealth;
        arrows = 5;
        level = 1;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        gold = 0;
        currentWeapon = new OBJ_Sword_Default(gp);
        currentShield = new OBJ_Shield_Default(gp);
        projectile = new OBJ_Arrow(gp);
        attack = getAttack();
        defense = getDefense();
    }

    public void setDefaultPositions() {

        worldX = 1150;
        worldY = 1150;
        direction = "down";
    }

    public void restoreHealth() {

        health = maxHealth;
        invincible = false;
    }

    public void setItems() {

        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
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

    if (currentWeapon.name == "Badgers Scimitar") {
        attackArea.height = currentWeapon.attackArea.height;
        attackArea.width = currentWeapon.attackArea.width;
        attackUp1 = setup("/res/player/boy_scimitar_up1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/res/player/boy_scimitar_up2", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/res/player/boy_scimitar_left1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/res/player/boy_scimitar_left2", gp.tileSize*2, gp.tileSize);
        attackDown1 = setup("/res/player/boy_scimitar_down1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/res/player/boy_scimitar_down2", gp.tileSize, gp.tileSize*2);
        attackRight1 = setup("/res/player/boy_scimitar_right1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/res/player/boy_scimitar_right2", gp.tileSize*2, gp.tileSize);
    }
    if (currentWeapon.name == "Longsword") {
        attackArea.height = currentWeapon.attackArea.height;
        attackArea.width = currentWeapon.attackArea.width;
        attackUp1 = setup("/res/player/boy_scimitar_up1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/res/player/boy_longsword_up", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/res/player/boy_scimitar_left1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/res/player/boy_longsword_left", gp.tileSize*2, gp.tileSize);
        attackDown1 = setup("/res/player/boy_scimitar_down1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/res/player/boy_longsword_down", gp.tileSize, gp.tileSize*2);
        attackRight1 = setup("/res/player/boy_scimitar_right1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/res/player/boy_longsword_right", gp.tileSize*2, gp.tileSize);
    }

    if (currentWeapon.name == "Hammer") {
        attackArea.height = currentWeapon.attackArea.height;
        attackArea.width = currentWeapon.attackArea.width;
        attackUp1 = setup("/res/player/boy_hammer_up1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/res/player/boy_hammer_up2", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/res/player/boy_hammer_left1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/res/player/boy_hammer_left2", gp.tileSize*2, gp.tileSize);
        attackDown1 = setup("/res/player/boy_hammer_down1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/res/player/boy_hammer_down2", gp.tileSize, gp.tileSize*2);
        attackRight1 = setup("/res/player/boy_hammer_right1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/res/player/boy_hammer_right2", gp.tileSize*2, gp.tileSize);
    }
}

public void getPlayerShootImage() {

    if (currentShoot.name == "Wooden Bow") {
        shootDown1 = setup("/res/player/boy_bow_down1", gp.tileSize, gp.tileSize);
        shootDown2 = setup("/res/player/boy_bow_down2", gp.tileSize, gp.tileSize);
        shootDown3 = setup("/res/player/boy_bow_down3", gp.tileSize, gp.tileSize);
        shootDown4 = setup("/res/player/boy_bow_down4", gp.tileSize, gp.tileSize);
        shootLeft1 = setup("/res/player/boy_bow_left1", gp.tileSize, gp.tileSize);
        shootLeft2 = setup("/res/player/boy_bow_left2", gp.tileSize, gp.tileSize);
        shootLeft3 = setup("/res/player/boy_bow_left3", gp.tileSize, gp.tileSize);
        shootLeft4 = setup("/res/player/boy_bow_left4", gp.tileSize, gp.tileSize);
        shootUp1 = setup("/res/player/boy_bow_up1", gp.tileSize, gp.tileSize);
        shootUp2 = setup("/res/player/boy_bow_up2", gp.tileSize, gp.tileSize);
        shootUp3 = setup("/res/player/boy_bow_up3", gp.tileSize, gp.tileSize);
        shootUp4 = setup("/res/player/boy_bow_up4", gp.tileSize, gp.tileSize);
        shootRight1 = setup("/res/player/boy_bow_right1", gp.tileSize, gp.tileSize);
        shootRight2 = setup("/res/player/boy_bow_right2", gp.tileSize, gp.tileSize);
        shootRight3 = setup("/res/player/boy_bow_right3", gp.tileSize, gp.tileSize);
        shootRight4 = setup("/res/player/boy_bow_right4", gp.tileSize, gp.tileSize);
    }
}

    public void update() {
        
        if (gp.keyH.attackKeyPressed == true && shooting != true) {
            attacking = true;
        }

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

         // CHECK INTERACTIVE TILE COLLISION

         gp.cChecker.checkEntity(this, gp.iTile);

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
       
        if (gp.keyH.shootKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30 && projectile.haveResource(this) == true
        && currentShoot != null && attacking == false) {
            shooting = true;
        }

        if (shooting == true) {
            shooting();
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }

        if (health > maxHealth) {
            health = maxHealth;
        }

        if (health <= 0) {
            attacking = false;
            shooting = false;
            gp.gameState = gp.gameOverState;
            gp.playSE(16);
            gp.stopMusic();
        }
    }

    public void shooting() {
        
        shootCounter++;

        if (shootCounter <= 5 && shootCounter >= 0) {
            shootNum = 1;
        }
        if (shootCounter > 5 && shootCounter <= 15) {
            shootNum = 2;
        }
        if (shootCounter > 15 && shootCounter < 25) {
            shootNum = 3;
        }
        if (shootCounter > 25 && gp.keyH.shootKeyPressed == false) {
            shootNum = 4;
            projectile.set(worldX, worldY+5, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
            gp.playSE(11);
            projectile.subtractResource(this);
            shootCounter = -22;
        }
        if (shootCounter == -10) {
            shootCounter = 0;
            shooting = false;
        }
    }

    public void attacking() {

        attackCounter++;

        if (attackCounter > 0 && attackCounter < 2) {
            if (currentWeapon.type == 3) {gp.playSE(5);}
            if (currentWeapon.type == 9) {gp.playSE(14);}
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
            damageMonster(monsterIndex, attack);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

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
            int type = gp.obj[gp.currentMap][i].type;
            String text;
            String objectName = gp.obj[gp.currentMap][i].name;

            // PICKUP ONLY ITEMS

            if (type == 7) {
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }

            // STATIC OBJECTS

            else if (type == 8) {
                gp.obj[gp.currentMap][i].use(this);
            }

            else if (type == 11) {
                
            }

            // INVENTORY ITEMS

            else if (type == 3 || type == 4 || type == 5 || type == 6 || type == 9) {
                if (inventory.size() != inventorySize) {
                    if (objectName == "Creamor Key") {hasKey++;}
                    inventory.add(gp.obj[gp.currentMap][i]);
                    gp.playSE(1);
                    text = "You got a " + objectName + "!";
                } else {
                    text = "You cannot carry any more!";
                }
                gp.ui.showMessage(text);
                gp.obj[gp.currentMap][i] = null;
            }
            }
        }

        public void interactNPC(int i) {

            if (i != 999) {
                
                if (i == 0) {
                    if (gp.keyH.ePressed == true) {
                        gp.gameState = gp.dialogueState;
                        gp.npc[gp.currentMap][i].speak();
                    }
                }
                else if (gp.keyH.ePressed == true) {
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
                }
        }
    }

    public void contactMonster(int i) {

        if (i != 999) {
            if (invincible == false && gp.monster[gp.currentMap][i].dying == false) {
                gp.playSE(6);
                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if (damage <= 0) {
                    damage = 1;
                }
                health -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i, int attack) {

        if (i != 999) {
            if(gp.monster[gp.currentMap][i].invincible == false) {
                gp.playSE(7);
                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }
                gp.monster[gp.currentMap][i].health -= damage;
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();
            }
            if (gp.monster[gp.currentMap][i].health <= 0) {
                gp.monster[gp.currentMap][i].health = 0;
                gp.monster[gp.currentMap][i].dying = true;
            }
        }
    }

    public void damageInteractiveTile(int i) {
        if (i != 999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].isCorrectItem(this) == true && gp.iTile[gp.currentMap][i].invincible == false) {
            gp.iTile[gp.currentMap][i].health--;
            gp.iTile[gp.currentMap][i].invincible = true;

            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
            if (gp.iTile[gp.currentMap][i].name.equals("IT_SmallRock")) {
                gp.playSE(15);
                if (gp.iTile[gp.currentMap][i].health < 2) {
                    gp.iTile[gp.currentMap][i].down1 = gp.iTile[gp.currentMap][i].down2;
                }
            }
            if (gp.iTile[gp.currentMap][i].health <= 0) {
                gp.iTile[gp.currentMap][i] = null;
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

    public void selectItem() {

        int itemIndex = gp.ui.itemIndexOnSlot;

        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == 3 || selectedItem.type == 9) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if (selectedItem.type == 4) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == 5) {
                currentShoot = selectedItem;
                getPlayerShootImage();
            }
            if (selectedItem.type == 6) {
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
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
                if (attacking == false && shooting == false) {
                    if(spriteNum == 1) {image = up1;}
                    if(spriteNum == 2) {image = up2;}
                }
                if (attacking == true && shooting == false) {
                    tempScreenY = screenY - gp.tileSize;
                    if(attackNum == 1) {image = attackUp1;}
                    if (attackNum == 2) {image = attackUp2;}
                }
                if (shooting == true && attacking == false) {
                    if(shootNum == 1) {image = shootUp1;}
                    if(shootNum == 2) {image = shootUp2;}
                    if(shootNum == 3) {image = shootUp3;}
                    if(shootNum == 4) {image = shootUp4;}
                }
                break;
            case "down":
                if (attacking == false && shooting == false) {
                    if(spriteNum == 1) {image = down1;}
                    if(spriteNum == 2) {image = down2;}
                }
                if (attacking == true && shooting == false) {
                    if(attackNum == 1) {
                        image = attackDown1;
                        if (currentWeapon.name.equals("Hammer")) {
                            tempScreenY -= gp.tileSize;
                        }
                    }
                    if(attackNum == 2) {
                        image = attackDown2;
                    }
                }
                if (shooting == true && attacking == false) {
                    if(shootNum == 1) {image = shootDown1;}
                    if(shootNum == 2) {image = shootDown2;}
                    if(shootNum == 3) {image = shootDown3;}
                    if(shootNum == 4) {image = shootDown4;}
                }
                break;
            case "left":
                if (attacking == false && shooting == false) {
                    if(spriteNum == 1) {image = left1;}
                    if(spriteNum == 2) {image = left2;}
                }
                if (attacking == true && shooting == false) {
                    tempScreenX = tempScreenX - gp.tileSize;
                    if(attackNum == 1) {image = attackLeft1;}
                    if(attackNum == 2) {image = attackLeft2;}
                }
                if (shooting == true && attacking == false) {
                    if(shootNum == 1) {image = shootLeft1;}
                    if(shootNum == 2) {image = shootLeft2;}
                    if(shootNum == 3) {image = shootLeft3;}
                    if(shootNum == 4) {image = shootLeft4;}
                }
                break;
            case "right":
                if (attacking == false && shooting == false) {
                    if(spriteNum == 1) {image = right1;}
                    if(spriteNum == 2) {image = right2;}
                }
                if (attacking == true && shooting == false) {
                    if(attackNum == 1) {image = attackRight1;}
                    if(attackNum == 2) {image = attackRight2;}
                }
                if (shooting == true && attacking == false) {
                    if(shootNum == 1) {image = shootRight1;}
                    if(shootNum == 2) {image = shootRight2;}
                    if(shootNum == 3) {image = shootRight3;}
                    if(shootNum == 4) {image = shootRight4;}
                }
                break;
        }
        g2.drawImage(image, tempScreenX, tempScreenY,null);
    }
}
