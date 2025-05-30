package entity;

import main.GamePanel;
import monster.MON_Slime;
import obj.OBJ_Rowboat;


public class NPC_Rock extends Entity {

    boolean spawn1;
    boolean spawn2;

    public NPC_Rock(GamePanel gp) {

        super(gp);
        speed = 0;
        type = type_npc;
        direction = "still";
        setDialogue();
        spawn1 = false;
        spawn2 = false;
    }

    public void getImage() {}

public void setDialogue() {
    dialogues[0] = "What brings you to this place? I hope it's not that \ntreasure...";
    dialogues[1] = "I've seen plenty youngins just like you, greener than\nthe grass of Midland.";
    dialogues[2] = "Most of their stories don't end too well...";
    dialogues[3] = "That place is beautiful on the surface, but underneath...";
    dialogues[4] = "I can tell you more when you're ready. Start with defeating\nthose slimes south of here.";
}

public void setAction() {}

public void speak() {

    if (gp.player.level == 2) {
        dialogues[5] = "You're stronger. I suppose you're ready to hear more.";
        dialogues[6] = "A long, long time ago, a strong warrior came to this island.";
        dialogues[7] = "He was from Midland, and he came to search for the\nlegendary treasure he had heard so much about.";
        dialogues[8] = "With sword and bow in tow, he begun his adventure, filled\nwith excitement, treasure gleaming in his eyes.";
        dialogues[9] = "He headed for that town. But when he got there, he noticed\nsomething strange...";
        dialogues[10] = "That's enough for now. Increase your strength even more if\nyou want to hear the rest.";
    }
    if (gp.player.level == 3) {
        dialogues[11] = "You seem stronger. I think you're ready to hear some more...";
        dialogues[12] = "After spending some more time in the town, the warrior\nnoticed that something wasn't right.";
        dialogues[13] = "The people were all really friendly, and the place\nwas beautiful.";
        dialogues[14] = "But one night, when he suddenly woke up, he heard strange\nnoises.";
        dialogues[15] = "It sounded like they were coming from the mines.\nThe following day he decided to ask someone about it.";
        dialogues[16] = "However, when he mentioned it to any of the townsfolk,\nthey started acting strange.";
        dialogues[17] = "It was like they were avoiding the subject.";
        dialogues[18] = "That place is dangerous...";
        dialogues[19] = "But I suppose you have no other choice than to go there and\nsee for yourself.";
        dialogues[20] = "There should be a boat for you at the southeastern\ncorner of this island.";
        dialogues[21] = "You can use it to get to the other island.";
        dialogues[22] = "Best of luck kiddo...";
        dialogues[23] = "You're gonna need it...";
    }

    if (dialogueIndex == 4 && spawn1 == false) {
        gp.monster[0][0] = new MON_Slime(gp);
        gp.monster[0][0].worldX = 20*gp.tileSize;
        gp.monster[0][0].worldY = 32*gp.tileSize;
    
        gp.monster[0][1] = new MON_Slime(gp);
        gp.monster[0][1].worldX = 22*gp.tileSize;
        gp.monster[0][1].worldY = 32*gp.tileSize;
        
        gp.monster[0][2] = new MON_Slime(gp);
        gp.monster[0][2].worldX = 24*gp.tileSize;
        gp.monster[0][2].worldY = 32*gp.tileSize;
        
        spawn1= true;
    }
    if (dialogueIndex == 10 && spawn2 == false) {
        gp.monster[0][0] = new MON_Slime(gp);
        gp.monster[0][0].worldX = 20*gp.tileSize;
        gp.monster[0][0].worldY = 32*gp.tileSize;
        
        gp.monster[0][1] = new MON_Slime(gp);
        gp.monster[0][1].worldX = 22*gp.tileSize;
        gp.monster[0][1].worldY = 32*gp.tileSize;
        
        gp.monster[0][2] = new MON_Slime(gp);
        gp.monster[0][2].worldX = 24*gp.tileSize;
        gp.monster[0][2].worldY = 32*gp.tileSize;
        
        gp.monster[0][3] = new MON_Slime(gp);
        gp.monster[0][3].worldX = 26*gp.tileSize;
        gp.monster[0][3].worldY = 32*gp.tileSize;
        
        gp.monster[0][4] = new MON_Slime(gp);
        gp.monster[0][4].worldX = 28*gp.tileSize;
        gp.monster[0][4].worldY = 32*gp.tileSize;
        
        spawn2 = true;
    }

    if (dialogueIndex == 23) {
        gp.obj[0][0] = new OBJ_Rowboat(gp);
        gp.obj[0][0].worldX = 33*gp.tileSize;
        gp.obj[0][0].worldY = 31*gp.tileSize;
    }

    if (dialogues[dialogueIndex] == null) {
        dialogueIndex = 0;
    }
    if (dialogueIndex < 5 && gp.player.level == 2) {
        dialogueIndex = 5;
    }
    if (dialogueIndex < 11 && gp.player.level == 3) {
        dialogueIndex = 11;
    }
    
    gp.ui.currentDialogue = dialogues[dialogueIndex];
    dialogueIndex++;
}
}