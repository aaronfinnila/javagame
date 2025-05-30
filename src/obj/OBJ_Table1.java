package obj;
import entity.Entity;
import main.GamePanel;

public class OBJ_Table1 extends Entity {

    GamePanel gp;
    public OBJ_Table1(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = "Table1";
        down1 = setup("/res/objects/table1", 65*2, 55*2);
        down2 = setup("/res/objects/table1.2", 65*2, 55*2);
        collision = true;
        speed = 0;
        type = type_animated_object;
        solidArea.height = 50*2;
        solidArea.width = 65*2;
        setDialogue();
    }

    public void update() {
        moveCounter++;
        if (moveCounter <= 60) {
            spriteNum = 1;
        }
        else if (moveCounter > 60 && moveCounter <= 120) {
            spriteNum = 2;
        } 
        else if (moveCounter > 120) {
            moveCounter = 0;
        }
    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
    }

    public void setAction() {}

    public void setDialogue() {
        dialogues[0] = "25th of July, 2025\nMonday\n10:30pm";
        dialogues[1] = "Dear diary, today was an interesting day.";
        dialogues[2] = "While I was out for a walk near the water fountain, I\noverheard someone talk of a secret room at\nthe local store.";
        dialogues[3] = "They said that the shopkeeper stored secret items and\npossessions there, those that he didn't want others\nto find.";
        dialogues[4] = "Personally, I think that's complete rubbish. I mean, I've\nknown Michael since forever, and he never seemed like\nthe type to hide things.";
        dialogues[5] = "I mean, we were best friends for goodness sake!\nIf there's someone he would have told about some cool stuff\nhe's found, it's me.";
        dialogues[6] = "Though, we haven't really been close like that in a while..\nnot after the incident...";
        dialogues[7] = "Poor Michael, he's really been through a lot...";
    }
}