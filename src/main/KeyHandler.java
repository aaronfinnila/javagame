package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class KeyHandler implements KeyListener{

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed, spacePressed, shootKeyPressed, attackKeyPressed, escPressed;
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
        else if (gp.gameState == gp.optionsState) {
            optionsState(code);
        }
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
        else if (gp.gameState == gp.tradeState) {
            tradeState(code);
        }
        else if (gp.gameState == gp.interactState) {
            interactState(code);
        }
    }

    public void titleState(int code) {
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNumber++;
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNumber > 2) {
                    gp.ui.commandNumber = 0;
                }
            } else {
                if (gp.ui.commandNumber > 1) {
                    gp.ui.commandNumber = 0;
                }
            }
        }

        if (code == KeyEvent.VK_W) {
            gp.ui.commandNumber--;
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNumber < 0) {
                    gp.ui.commandNumber = 2;
                }
            } else {
                if (gp.ui.commandNumber < 0) {
                    gp.ui.commandNumber = 1;
                }
            }
        }

        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
            if (gp.ui.commandNumber == 1 && gp.ui.subState == 0) {
                gp.saveLoad.load();
                gp.gameState = gp.playState;
                switch (gp.currentMap) {
                    case 0: gp.playMusic(0); break;
                    case 1: gp.playMusic(17); break;
                    case 2: gp.playMusic(18); break;
                    case 3: gp.playMusic(19); break;
                    case 4: gp.playMusic(20); break;
                    case 7: gp.playMusic(31); break;
                }
            }
            if (gp.ui.commandNumber == 2) {
                System.exit(0);
            }
        }

        if (code == KeyEvent.VK_ESCAPE) {
            escPressed = true;
        }
    }

    public void playState(int code) {
        if (code == KeyEvent.VK_Q) {
            attackKeyPressed = true;
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

        if (code == KeyEvent.VK_R) {
            shootKeyPressed = true;
        }

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionsState;
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
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P || code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
            switch (gp.currentMap) {
                case 0: gp.playMusic(0); break;
                case 1: gp.playMusic(17); break;
                case 2: gp.playMusic(18); break;
                case 3: gp.playMusic(19); break;
                case 4: gp.playMusic(20); break;
                case 5: gp.playMusic(20); break;
                case 6: gp.playMusic(20); break;
                case 7: gp.playMusic(31); break;
            }
        }
    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
            if (gp.ui.currentDialogue.equals("You are now level " + gp.player.level + "!\nYou feel stronger than before!")) {
                if (gp.currentMap == gp.introislandMap) {
                    if (gp.dayState().equals("day")) {gp.changeMusic(0);}
                    else if (gp.dayState().equals("night")) {gp.changeMusic(21);}
                    else {}
                }
                else if (gp.currentMap == gp.treasureislandMap) {
                    if (gp.dayState().equals("day")) {gp.changeMusic(17);}
                    else if (gp.dayState().equals("night")) {gp.changeMusic(21);}
                    else {}
                }
                else if (gp.currentMap == gp.house1Map) {
                    gp.changeMusic(18);
                }
                else if (gp.currentMap == gp.house2Map) {
                    gp.changeMusic(19);
                }
                else if (gp.currentMap == gp.storeMap) {
                    gp.changeMusic(20);
                }
                else if (gp.currentMap == gp.dungeonMap) {
                    gp.changeMusic(31);
                }
            }
            if (gp.ui.subState != 0) {
                gp.gameState = gp.tradeState;
                spacePressed = false;
            }
    }

    if (code == KeyEvent.VK_ESCAPE) {
        gp.gameState = gp.playState;
        gp.ui.npc.resetDialogue();
    }

    }
    public void characterState(int code) {
        if (code == KeyEvent.VK_C || code == KeyEvent.VK_ESCAPE) {
            gp.ui.playerSlotCol = 0;
            gp.ui.playerSlotRow = 0;
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_SPACE) {
            gp.player.selectItem();
        }
        playerInventory(code);
    }

    public void optionsState(int code) {

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }

        int maxCommandNum = 0;
        switch(gp.ui.subState) {
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNumber++;
            gp.playSE(10);
            if (gp.ui.commandNumber > maxCommandNum) {
                gp.ui.commandNumber = 0;
            }
        }
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNumber--;
            gp.playSE(10);
            if (gp.ui.commandNumber < 0) {
                gp.ui.commandNumber = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNumber == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(10);
                }
                if (gp.ui.commandNumber == 2 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                    gp.playSE(10);
                }
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNumber == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(10);
                }
                if (gp.ui.commandNumber == 2 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++;
                    gp.playSE(10);
                }
            }
        }
    }

    public void gameOverState(int code) {

        if (code == KeyEvent.VK_W) {
            gp.ui.commandNumber--;
            if (gp.ui.commandNumber < 0) {
                gp.ui.commandNumber = 1;
            }
            gp.playSE(10);
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNumber++;
            if (gp.ui.commandNumber > 1) {
                gp.ui.commandNumber = 0;
            }
            gp.playSE(10);
        }
        if (code == KeyEvent.VK_SPACE) {

            // RETRY

            if (gp.ui.commandNumber == 0) {
                if (gp.saveExists == true) {
                    gp.saveLoad.load();
                    gp.gameState = gp.playState;
                    switch (gp.currentMap) {
                        case 0: gp.playMusic(0); break;
                        case 1: gp.playMusic(17); break;
                        case 2: gp.playMusic(18); break;
                        case 3: gp.playMusic(19); break;
                        case 4: gp.playMusic(20); break;
                    }
                } else {
                    gp.currentMap = gp.introislandMap;
                    gp.player.worldX = 1150;
                    gp.player.worldY = 1150;
                }
            }

            // QUIT

            else if (gp.ui.commandNumber == 1) {
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }
    }

    public void tradeState(int code) {

        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }

        if (code == KeyEvent.VK_ESCAPE) {
            escPressed = true;
        }

        if (gp.ui.subState == 0) {
            int max;

            // CHECK FOR INTERACT SHOP EVENT

            if (gp.ui.interactShop == true) {
                max = 3;
            } else {
                max = 2;
            }

            if (code == KeyEvent.VK_W) {
                gp.ui.commandNumber--;
                if (gp.ui.commandNumber < 0) {
                    gp.ui.commandNumber = max;
                }
                gp.playSE(10);
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNumber++;
                if (gp.ui.commandNumber > max) {
                    gp.ui.commandNumber = 0;
                }
                gp.playSE(10);
            }
        }

        if (gp.ui.subState == 1) {
            npcInventory(code);
            if (escPressed) {
                gp.ui.subState = 0;
                gp.ui.npc.dialogueSet = 0;
                Random random = new Random();
                int dialogueNum = random.nextInt(3);
                gp.ui.npc.dialogueIndex = dialogueNum;
                gp.ui.combinedText = "";
                gp.ui.charIndex = 0;
                gp.ui.npcSlotCol = 0;
                gp.ui.npcSlotRow = 0;
                escPressed = false;
            }
        }
        
        if (gp.ui.subState == 2) {
            playerInventory(code);
            if (escPressed) {
                gp.ui.subState = 0;
                gp.ui.npc.dialogueSet = 0;
                Random random = new Random();
                int dialogueNum = random.nextInt(3);
                gp.ui.npc.dialogueIndex = dialogueNum;
                gp.ui.combinedText = "";
                gp.ui.charIndex = 0;
                gp.ui.playerSlotCol = 0;
                gp.ui.playerSlotRow = 0;
                escPressed = false;
            }
        }
    }

    public void playerInventory(int code) {
        if (code == KeyEvent.VK_W) {
            if (gp.ui.playerSlotRow != 0) {
                gp.ui.playerSlotRow--;
                gp.playSE(10);
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.playerSlotCol != 0) {
                gp.ui.playerSlotCol--;
                gp.playSE(10);
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.playerSlotRow != 4) {
                gp.ui.playerSlotRow++;
                gp.playSE(10);
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.playerSlotCol != 4) {
                gp.ui.playerSlotCol++;
                gp.playSE(10);
            }
        }
    }

    public void npcInventory(int code) {
        if (code == KeyEvent.VK_W) {
            if (gp.ui.npcSlotRow != 0) {
                gp.ui.npcSlotRow--;
                gp.playSE(10);
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.npcSlotCol != 0) {
                gp.ui.npcSlotCol--;
                gp.playSE(10);
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.npcSlotRow != 4) {
                gp.ui.npcSlotRow++;
                gp.playSE(10);
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.npcSlotCol != 4) {
                gp.ui.npcSlotCol++;
                gp.playSE(10);
            }
        }
    }

    public void interactState(int code) {
        if (code == KeyEvent.VK_A) {
            if (gp.ui.interactCol != 0) {
                gp.ui.interactCol--;
                gp.playSE(10);
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.interactCol != 1) {
                gp.ui.interactCol++;
                gp.playSE(10);
            }
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if (spacePressed) {
            if(gp.ui.interactCol == 0) {
                gp.ui.interactChoice = 1;
            } else {
                gp.ui.interactChoice = 2;
            }    
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

            if (code == KeyEvent.VK_Q) {
                attackKeyPressed = false;
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

            if (code == KeyEvent.VK_R) {
                shootKeyPressed = false;
            }

            if (code == KeyEvent.VK_ESCAPE) {
                escPressed = false;
            }
    }
}