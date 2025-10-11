package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public BufferedImage death1, death2, death3, death4, death5;
    public BufferedImage attackDown1, attackDown2, attackLeft1, attackLeft2, attackUp1,
    attackUp2, attackRight1, attackRight2;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage image, image2, image3;
    public BufferedImage shootDown1, shootDown2, shootDown3, shootDown4, shootLeft1, shootLeft2, shootLeft3, shootLeft4,
    shootUp1, shootUp2, shootUp3, shootUp4, shootRight1, shootRight2, shootRight3, shootRight4;
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public String dialogues[][] = new String[30][30];
    
    // STATE
    
    public String direction = "down";
    public boolean collisionOn = false;
    public boolean usedObject = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean shooting = false;
    public boolean dying = false;
    public boolean alive = true;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;
    public boolean interactionHappened = false;
    
    // COUNTERS
    
    public int actionLockCounter = 0;
    public int moveCounter = 0;
    public int standCounter = 0;
    public int attackCounter = 0;
    public int shootCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    public int attackNum = 1;
    public int shootNum = 1;
    public int spriteNum = 1;
    public int dialogueIndex = 0;
    public int dialogueSet = -1;
    public int dyingCounter = 0;
    public int hasKey = 0;
    public int hpBarCounter;
    public int attackAvailableCounter = 0;
    public int knockBackCounter = 0;
    public int soundNum = 0;
    
    // ATTRIBUTES
    
    public int knockBackPower;
    public int defaultSpeed;
    public int speed;
    public String name;
    public boolean collision = false;
    public int maxHealth;
    public int health;
    public int level;
    public int strength;
    public int dexterity;
    public int healingValue;
    public int defense;
    public int attack;
    public int exp;
    public int nextLevelExp;
    public int gold;
    public int arrows;
    public int useCost;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentShoot;
    public Entity currentLight;
    public Projectile projectile;
    
    // ITEM ATTRIBUTES
    
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 25;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int price;
    public int lightRadius;
    
    // TYPE

    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_shield = 4;
    public final int type_shoot = 5;
    public final int type_consumable = 6;
    public final int type_pickup_only = 7;
    public final int type_static_object = 8;
    public final int type_hammer = 9;
    public final int type_interactive_tile = 10;
    public final int type_animated_object = 11;
    public final int type_projectile = 12;
    public final int type_light = 13;

    public Entity(GamePanel gp) {
        this.gp = gp;
        worldY = 0;
    }

    public void damageReaction() {}

    public int getXdistance(Entity target) {
        int xDistance = Math.abs(worldX - target.worldX);
        return xDistance;
    }

    public int getYdistance(Entity target) {
        int yDistance = Math.abs(worldY - target.worldY);
        return yDistance;
    }

    public int getTileDistance(Entity target) {
        int tileDistance = (getXdistance(target) + getYdistance(target))/gp.tileSize;
        return tileDistance;
    }

    public int getGoalCol(Entity target) {
        int goalCol = (target.worldX+16 + target.solidArea.x)/gp.tileSize;
        return goalCol;
    }
    public int getGoalRow(Entity target) {
        int goalRow = (target.worldY + target.solidArea.y)/gp.tileSize;
        return goalRow;
    }

    public void getAttackImage() {}

    public String getInteractText() {return "";}

    public void getInteractChoices() {}

    public void getShootImage() {}

    public void getImage() {}

    public void setAction() {}

    public void use(Entity entity) {}

    public void checkDrop() {}

    public void startStalking(Entity target, int distance) {}

    public void checkStopChasingOrNot(Entity target, int distance, int rate) {

        if (getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = false;
            }
        }
    }

    public void checkStartChasingOrNot(Entity target, int distance, int rate) {

        if (getTileDistance(target) < distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = true;
            }
        }
    }

    public void checkShootOrNot(int rate, int shotInterval) {

        int i = new Random().nextInt(rate);
        if (i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval) {
            projectile.set(worldX+12, worldY+20, direction, true, this);
            for (int j=0;j<gp.projectile[1].length;j++) {
                if (gp.projectile[gp.currentMap][j] == null) {
                    gp.projectile[gp.currentMap][j] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }

    public void checkAttackOrNot(int rate, int straight, int horizontal) {

        boolean targetInRange = false;
        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);

        switch (direction) {
            case "up":
            if (gp.player.worldY < worldY && yDis < straight && xDis < horizontal) {
                targetInRange = true;
            }
            break;
            case "down":
            if (gp.player.worldY > worldY && yDis < straight && xDis < horizontal) {
                targetInRange = true;
            }
            break;
            case "left":
            if (gp.player.worldX < worldX && xDis < straight && yDis < horizontal) {
                targetInRange = true;
            }
            break;
            case "right":
            if (gp.player.worldX > worldX && xDis < straight && yDis < horizontal) {
                targetInRange = true;
            }
            break;
        }

        if (targetInRange == true) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                attacking = true;
                spriteNum = 1;
                attackCounter = 0;
            }
        }
    }

    public void getRandomDirection() {

        actionLockCounter++;

        if (actionLockCounter == 120) {

        Random random = new Random();
        int i = random.nextInt(100) + 1;
        
        if (i <= 25) {direction = "up";}
        if (i > 25 && i <= 50) {direction = "down";}
        if (i > 50 && i <= 75) {direction = "left";}
        if (i > 75 && i <= 100) {direction = "right";}

        actionLockCounter = 0;
        }
    }

    public Color getParticleColor() {
        Color color = null;
        return color;
    }

    public int getParticleSize() {
        int size = 0;
        return size;
    }

    public int getParticleSpeed() {
        int speed = 0;
        return speed;
    }

    public int getParticleMaxHealth() {
        int maxHealth = 0;
        return maxHealth;
    }

    public void generateParticle(Entity generator, Entity target) {

        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxHealth = generator.getParticleMaxHealth();

        Particle p1 = new Particle(gp, target, color, size, speed, maxHealth, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxHealth, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxHealth, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxHealth, 2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }

    public void dropItem(Entity droppedItem) {

        for (int i=0;i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public void speak() {

        dialogueSet++;

        if (dialogues[dialogueSet][dialogueIndex] == null) {
            dialogueSet = 0;
        }
    }

    public void facePlayer() {

        gp.ui.tempDirection = direction;

        switch(gp.player.direction) {
            case "up": 
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void startDialogue(Entity entity, int setNum) {

        gp.keyH.spacePressed = false;
        dialogueIndex = 0;
        gp.ui.charIndex = 0;
        gp.ui.combinedText = "";
        dialogueSet = setNum;
        gp.ui.npc = entity;
        gp.gameState = gp.dialogueState;
    }

    public void resetDialogue() {
        
        gp.ui.npc.dialogueSet = 0;
        gp.ui.npc.dialogueIndex++;
        gp.ui.combinedText = "";
        gp.ui.charIndex = 0;
    }

    public void checkCollision() {

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == type_monster && contactPlayer == true) {
            damagePlayer(attack);
        }
    }

    public void update() {

        if (knockBack == true) {

            checkCollision();
            if (collisionOn == true) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            } else if (collisionOn == false) {
                switch(direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
            knockBackCounter++;
            if (knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        } else if (attacking == true) {
                attacking();
            } else {
            checkCollision();
            setAction();

            if (collisionOn == false) {
                switch(direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
             }
        }
        
        // IF COLLISION IS FALSE, NPC CAN MOVE

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

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 35) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
    }

    public void attacking() {
        
        attackCounter++;

        if (attackCounter > 0 && attackCounter < 2) {
            if (currentWeapon.name == "Badgers Scimitar") {gp.playSE(5);}
            if (currentWeapon.name == "Longsword") {gp.playSE(5);}
            if (currentWeapon.name == "Nightingale") {gp.playSE(22);}
            if (currentWeapon.name == "Hammer") {gp.playSE(14);}
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

            // Adjust entity's worldX and worldY for attackArea

            switch(direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            // solidArea becomes attackArea

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            
            if (type == type_monster) {
                if (gp.cChecker.checkPlayer(this) == true) {
                    damagePlayer(attack);
                }
            } else {
                
                // PLAYER STUFF

                // Check monster collision with the updated worldX, worldY and solidArea

                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex, attack, currentWeapon.knockBackPower, direction);
    
                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);
    
                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }

            // After checking collision, restore original values
    
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.height = solidAreaHeight;
            solidArea.width = solidAreaWidth;

        }
        if (attackCounter > 25) {
            attackNum = 1;
            attackCounter = 0;
            attackAvailableCounter = 0;
            attacking = false;
        }
    }

    public void damagePlayer(int attack) {

        if (gp.player.invincible == false) {
            int damage = attack - gp.player.defense;
            if (damage <= 0) {
                damage = 1;
            }
            gp.player.health -= damage;
            gp.playSE(6);
            gp.player.invincible = true;
        }
    }

    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.screenWidth > gp.player.worldX - gp.player.screenX &&
            worldX - gp.screenWidth < gp.player.worldX + gp.player.screenX && 
            worldY + gp.screenHeight > gp.player.worldY - gp.player.screenY && 
            worldY - gp.screenHeight < gp.player.worldY + gp.player.screenY) {

            int tempScreenX = screenX;
            int tempScreenY = screenY;

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
        
        // Monster HP bar

            if (type == 2 && hpBarOn == true) {

                double oneScale = (double)gp.tileSize/maxHealth;
                double hpBarValue = oneScale*health;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);
                
                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
                
                hpBarCounter++;
                
                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            // Invincible

            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.3f);
            }

            // Dying
            
            if (dying == true) {
                changeAlpha(g2, 1f);
                dyingCounter++;
                
                if (dyingCounter >= 0 && dyingCounter <= 10) {image = death1;}
                if (dyingCounter > 0 && dyingCounter < 2) {
                    gp.playSE(8);
                    gp.ui.showExpMessage("You received " + exp + " exp!");
                    gp.ui.showGoldMessage("You received " + gold + " gold!");
                    gp.player.gold += gold;
                }
                if (dyingCounter >= 10 && dyingCounter <= 15) {image = death2;}
                if (dyingCounter >= 15 && dyingCounter <= 20) {image = death3;}
                if (dyingCounter >= 20 && dyingCounter <= 25) {image = death4;}
                if (dyingCounter >= 25 && dyingCounter <= 30) {image = death5;}
                if (dyingCounter >= 30) {
                    if (alive == true) {
                        gp.player.exp += exp;
                    }
                    alive = false;
                };
                tempScreenX = worldX - gp.player.worldX + gp.player.screenX - gp.tileSize/2;
                tempScreenY = worldY - gp.player.worldY + gp.player.screenY - gp.tileSize/2;
            }
            
            g2.drawImage(image, tempScreenX, tempScreenY, null);
            
            changeAlpha(g2, 1f);
            }
        }
        
        public void changeAlpha(Graphics2D g2, float alpha) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        }

        public void searchPath(int goalCol, int goalRow) {

            int startCol = (worldX + solidArea.x)/gp.tileSize;
            int startRow = (worldY + solidArea.y)/gp.tileSize;

            gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

            if (gp.pFinder.search() == true) {

                // NEXT WORLDX AND WORLDY

                int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
                int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

                // PATHFINDING

                if (nextX > worldX + speed && direction == "right" || nextY > worldY + speed && direction == "down"
                || nextY < worldY + speed && direction == "up" || nextX < worldX && direction == "left") {
                        // direction stays the same
                    }
                    
                else if (nextY > worldY + speed) {
                    direction = "down";
                }
                else if (nextY < worldY + speed){
                    direction = "up";
                }
                else if (nextX < worldX) {
                    direction = "left";
                }
                else if (nextX>worldX) {
                    direction = "right";
                }

                // ENDING THE PATHFINDING

/*                 int nextCol = gp.pFinder.pathList.get(0).col;
                int nextRow = gp.pFinder.pathList.get(0).row;
                if (nextCol == goalCol && nextRow == goalRow) {
                    onPath = false;
                    System.out.println("end");
                } */
            }
        }
    }