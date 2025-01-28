package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTINGS

    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //FPS

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
    Thread gameThread;

    // ENTITY AND OBJECT

    public Player player = new Player(this,keyH,tileM);
    public Entity obj[] = new Entity[20];
    public Entity npc[] = new Entity[20];
    public Entity monster[] = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;


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
        gameState = titleState;
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
            repaint();
            delta--;
        }
        }
    }

    public void update() {

        if (gameState == playState) {

            // PLAYER

            player.update();

            // NPC 

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }

            // MONSTER

            for (int i=0;i<monster.length;i++) {
                if (monster[i] != null) {
                    if (monster[i].alive == true && monster[i].dying == false) {
                        monster[i].update();
                    }
                    if (monster[i].alive == false) {
                        monster[i] = null;
                    }
                }
            }

            // QUESTIONMARK OBJECT
            
            if (actionActive == true) {
                long elapsedTime = System.currentTimeMillis() - starttime;
                if (elapsedTime > 13000 && gameState == playState) {
                    obj[3].worldX = 1000 * tileSize;
                    obj[3].worldY = 1000 * tileSize;
                    if (musicPlaying != true) {
                        playMusic(0); 
                    }
                    actionActive = false;
                }
            }
        }
        
        if (gameState == pauseState) {
    
        }
    }
    
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

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

            // ADD PLAYER TO LIST

            entityList.add(player);

            // ADD NPC'S TO LIST

            for (Entity e : npc) {
                if (e != null) {
                    entityList.add(e);
                }
            }

            // ADD OBJECTS TO LIST

            for (Entity e : obj) {
                if (e != null) {
                    entityList.add(e);
                }
            }
            
            // ADD MONSTERS TO LIST

            for (Entity e : monster) {
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
            }
                    
            g2.dispose();
        }
    }

        // MUSIC METHODS

    public void playMusic(int i) {

        music.setFile(i);
        music.play();
        music.loop();
        musicPlaying = true;
    }
    public void stopMusic() {

        music.stop();
        musicPlaying = false;
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