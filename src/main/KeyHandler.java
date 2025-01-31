package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed, spacePressed,
    qPressed;
    boolean showDebug = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();

        if (gp.gameState == gp.playState) {
            playState(code);
        }
        else if (gp.gameState == gp.titleState) {
            titleState(code);
        }
        else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        else if (gp.gameState == gp.characterState) {
            characterState(code);
        }
    }

    public void titleState(int code) {
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNumber++;
            if (gp.ui.commandNumber > 2) {
                gp.ui.commandNumber = 0;
            }
        }

        if (code == KeyEvent.VK_W) {
            gp.ui.commandNumber--;
            if (gp.ui.commandNumber < 0) {
                gp.ui.commandNumber = 2;
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNumber == 0) {
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            if (gp.ui.commandNumber == 2) {
                System.exit(0);
            }
        }
    }

    public void playState(int code) {
        if (code == KeyEvent.VK_Q) {
            gp.player.attacking = true;
        }

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
        
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        }

        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
            gp.stopMusic();
            gp.stopSE();
        }

        if (code == KeyEvent.VK_E) {
            ePressed = true;
        }

        // ADMIN KEYBINDS

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

        if (code == KeyEvent.VK_T) {
            if (showDebug == false) {
                showDebug = true;
            }
            else if (showDebug == true) {
                showDebug = false;
            }
        }

        if (code == KeyEvent.VK_Y) {
            gp.tileM.loadMap("/res/maps/worldmap02.txt");
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
            gp.playMusic(0);
            gp.musicPlaying = true;
        }
    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_SPACE) {
            if (gp.ui.currentDialogue.equals("You are now level " + gp.player.level + "!\nYou feel stronger than before!")) {
                gp.playMusic(0);
            }
            gp.gameState = gp.playState;
    }

    }
    public void characterState(int code) {
        if (code == KeyEvent.VK_C) {
            gp.ui.slotCol = 0;
            gp.ui.slotRow = 0;
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_W) {
            if (gp.ui.slotRow != 0) {
                gp.ui.slotRow--;
                gp.playSE(10);
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.slotCol != 0) {
                gp.ui.slotCol--;
                gp.playSE(10);
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.slotRow != 4) {
                gp.ui.slotRow++;
                gp.playSE(10);
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.slotCol != 4) {
                gp.ui.slotCol++;
                gp.playSE(10);
            }
        }
        if (code == KeyEvent.VK_SPACE) {
            gp.player.selectItem();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        // PLAY STATE 

        if (gp.gameState == gp.playState) {

            if (code == KeyEvent.VK_Q) {

            }

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

            if (code == KeyEvent.VK_SPACE) {
                spacePressed = false;
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

            if (code == KeyEvent.VK_SPACE) {
                spacePressed = false;
            }
        }

    }

}
