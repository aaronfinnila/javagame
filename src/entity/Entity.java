package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public int maxHealth;
    public int health;

    public BufferedImage attackDown1, attackDown2, attackLeft1, attackLeft2, attackUp1,
    attackUp2, attackRight1, attackRight2;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction = "down";

    public int moveCounter = 0;
    public int standCounter = 0;
    public int attackCounter = 0;
    public int attackNum = 1;
    public int spriteNum = 1;
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public String dialogues[] = new String[30];
    public int dialogueIndex = 0;
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public boolean usedObject = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public int invincibleCounter = 0;
    public int type;

    public Entity(GamePanel gp) {
        this.gp = gp;
        worldY = 0;
    }

    public void setAction() {}

    public void speak() {

        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }

        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
    
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

    public void update() {

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer == true) {
            if (gp.player.invincible == false) {
                gp.player.health--;
                gp.player.invincible = true;
            }
        }

        if (this.type == 1 && gp.gameState == gp.dialogueState) {
            if (gp.keyH.spacePressed == true) {
                dialogueIndex++;
            }
        }

        // IF COLLISION IS FALSE, NPC CAN MOVE
        
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
            if (invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
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

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                switch(direction) {
                    case "up":
                        if(spriteNum == 1) {image = up1;}
                        if (spriteNum == 2) {image = up2;}
                        break;
                    case "down":
                        if(spriteNum == 1) {image = down1;}
                        if (spriteNum == 2) {image = down2;}
                        break;
                    case "left": 
                        if (spriteNum == 1) {image = left1;}
                        if (spriteNum == 2) {image = left2;}
                        break;
                    case "right":
                        if (spriteNum == 1) {image = right1;}
                        if (spriteNum == 2) {image = right2;}
                        break;
                }
            }

            if (invincible == true) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }

            g2.drawImage(image, screenX, screenY, null);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }