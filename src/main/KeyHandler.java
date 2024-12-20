package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed;

    // DEBUG

    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();

        // PLAY STATE

        if (gp.gameState == gp.playState) {

            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
    
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
    
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
    
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
    
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
                gp.stopMusic();
                gp.stopSE();
            }

            if (code == KeyEvent.VK_E) {
                ePressed = true;
            }

            if (code == KeyEvent.VK_M) {
                if (gp.musicPlaying == true) {
                    gp.stopMusic();
                } else if (gp.musicPlaying == false) {
                    gp.playMusic(0);
                }
            }

            if (code == KeyEvent.VK_N) {
                if (gp.player.speed < 10) {
                    gp.player.speed = 12;
                } else if (gp.player.speed >= 12){
                    gp.player.speed = 4;
                }
            }
    
            // DEBUG

            if (code == KeyEvent.VK_T) {
                if (checkDrawTime == false) {
                    checkDrawTime = true;
                }
                else if (checkDrawTime == true) {
                    checkDrawTime = false;
                }
            }
        }

        // PAUSE STATE
        
        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
                gp.playMusic(0);
                gp.musicPlaying = true;
            }


        }

        // DIALOGUE STATE

        if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_SPACE) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        // PLAY STATE 

        if (gp.gameState == gp.playState) {

            if (code == KeyEvent.VK_W) {
                upPressed = false;
            }
    
            if (code == KeyEvent.VK_S) {
                downPressed = false;
            }
    
            if (code == KeyEvent.VK_A) {
                leftPressed = false;
            }
    
            if (code == KeyEvent.VK_D) {
                rightPressed = false;
            }

            if (code == KeyEvent.VK_E) {
                ePressed = false;
            }
        }

        // PAUSE STATE

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }

        // DIALOGUE STATE 
        
        if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_W) {
                upPressed = false;
            }
    
            if (code == KeyEvent.VK_S) {
                downPressed = false;
            }
    
            if (code == KeyEvent.VK_A) {
                leftPressed = false;
            }
    
            if (code == KeyEvent.VK_D) {
                rightPressed = false;
            }

            if (code == KeyEvent.VK_E) {
                ePressed = false;
            }
        }

    }

}
