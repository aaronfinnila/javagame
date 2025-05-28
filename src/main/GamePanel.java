package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import ai.PathFinder;
import entity.Entity;
import entity.Player;
import tile.TileManager;
import tile_interactive.InteractiveTile;

public class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTINGS

    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    
    // WORLD SETTINGS
    
    public int maxWorldCol;
    public int maxWorldRow;
    public final int maxMap = 10;
    public final int maxMapSize = 100;
    public int currentMap = 0;

    // FOR FULLSCREEN

    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    // FPS

    int FPS = 60;

    // SYSTEM

    public long starttime;
    public boolean actionActive;
    public boolean soundeffectActive;
    public boolean musicPlaying;
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public EventHandler eHandler = new EventHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Config config = new Config(this);
    public PathFinder pFinder = new PathFinder(this);
    Thread gameThread;

    // ENTITY AND OBJECT

    public Player player = new Player(this,keyH,tileM);
    public Entity obj[][] = new Entity[maxMap][50];
    public Entity npc[][] = new Entity[maxMap][50];
    public Entity monster[][] = new Entity[maxMap][50];
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();

        if (fullScreenOn == true) {
            setFullscreen();
        }
    }
    
    public void retry() {

        player.setDefaultPositions();
        player.restoreHealth();
        aSetter.setNPC();
        aSetter.setMonster();
        playMusic(0);
    }

    public void restart() {

        player.setDefaultValues();
        player.setItems();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
    }

    public void setFullscreen() {

        // GET LOCAL SCREEN DEVICE

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        // GET FULLSCREEN WIDTH AND HEIGHT

        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }


    public void run() {

    double drawInterval = 1000000000/FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    while(gameThread != null) {

        currentTime = System.nanoTime();

        delta += (currentTime - lastTime) / drawInterval;
        lastTime = currentTime;
        
        if (delta >= 1) {
            update();
            drawToTempScreen(); // draw everything to the buffered image
            drawToScreen(); // draw the buffered image to the screen
            delta--;
        }
        }
    }

    public void update() {

        if (gameState == playState) {

            // PLAYER

            player.update();

            // NPC 

            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }

            // MONSTER

            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
                        monster[currentMap][i].update();
                    }
                    if (monster[currentMap][i].alive == false) {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }

            // PROJECTILE

            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive == true) {
                        projectileList.get(i).update();
                    }
                    if (projectileList.get(i).alive == false) {
                        projectileList.remove(i);
                    }
                }
            }

            // PARTICLE

            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    if (particleList.get(i).alive == true) {
                        particleList.get(i).update();
                    }
                    if (particleList.get(i).alive == false) {
                        particleList.remove(i);
                    }
                }
            }

            // INTERACTIVE TILE

            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].update();
                }
            }

            // ANIMATED OBJECT

            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null && obj[currentMap][i].type == 11) {
                    obj[currentMap][i].update();
                }
            }
        }
    }

    public void drawToTempScreen() {

        // DEBUG

        long drawStart = 0;
        if (keyH.showDebug == true) {
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN
     
        if (gameState == titleState) {
            ui.draw(g2);
        }

        // OTHERS 

        else {

            // TILE
            
            tileM.draw(g2);

            // INTERACTIVE TILE

            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2);
                }
            }

            // ADD PLAYER TO LIST

            entityList.add(player);

            // ADD NPC'S TO LIST

            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }

            // ADD OBJECTS TO LIST

            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            
            // ADD MONSTERS TO LIST

            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }

            // ADD PROJECTILES TO LIST

            for (Entity e : projectileList) {
                if (e != null) {
                    entityList.add(e);
                }
            }

            // ADD PARTICLES TO LIST
            
            for (Entity e : particleList) {
                if (e != null) {
                    entityList.add(e);
                }
            }

            // SORT

            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }

            });

            // DRAW ENTITIES

            for (Entity entity : entityList) {
                entity.draw(g2);
            }

            // CLEAR ENTITY LIST

            entityList.clear();

            // UI
            
            ui.draw(g2);
             
            // DEBUG 
            
            if (keyH.showDebug == true) {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                int x = 10;
                int y = 400;
                int lineHeight = 20;
                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
                g2.setColor(Color.white);
                g2.drawString("Draw Time: " + passed, x, y); y += lineHeight;
                g2.drawString("WorldX " + player.worldX, x, y); y += lineHeight;
                g2.drawString("WorldY " + player.worldY, x, y); y += lineHeight;
                g2.drawString("Col " + (player.worldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
                g2.drawString("Row " + (player.worldY + player.solidArea.y)/tileSize, x, y); y += lineHeight;
                g2.drawString("maxWorldCol " + maxWorldCol, x, y); y += lineHeight;
                g2.drawString("currentmap " + currentMap, x, y); y += lineHeight;
            }
    }
    }

    public void drawToScreen() {

        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

        // MUSIC METHODS

    public void playMusic(int i) {

        if (this.music != null) {
            music.setFile(i);
            music.play();
            music.loop();
            musicPlaying = true;
        }
        System.out.println("musicplay " + i );
    }

    public void stopMusic() {
        if (this.music != null) {
            music.stop();
            musicPlaying = false;
        }
        System.out.println("musicstop");
    }
    public void changeMusic(int i, int delay) {
        stopMusic();
        playMusic(i);
        System.out.println("musicswitch");
    }
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
    public void stopSE() {
        if (soundeffectActive == true) {
            se.stop();
            soundeffectActive = false;
        } else {}
    }
}