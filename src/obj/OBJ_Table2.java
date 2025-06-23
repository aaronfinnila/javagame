package obj;
import entity.Entity;
import main.GamePanel;

public class OBJ_Table2 extends Entity {

    public final static String objName = "Table2";

    GamePanel gp;

    public OBJ_Table2(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = objName;
        speed = 0;
        type = type_static_object;
        solidArea.height = 20;
        solidArea.width = 20;
        setDialogue();
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
        dialogues[0] = "Bhi Her\nThe Art of Bar";
    }
}