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
        down2 = setup("/res/objects/table2", 65*2, 55*2);
        collision = true;
        speed = 0;
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
        dialogues[0] = "xd";
        dialogues[1] = "argarg";
        dialogues[2] = "argarg";
        dialogues[3] = "ragragargr";
        dialogues[4] = "argargargarg";
    }
}