package npc;
import entity.Entity;
import main.GamePanel;

public class NPC_DialogueHolder extends Entity {

    public final static String objName = "DialogueHolder";

    GamePanel gp;

    public NPC_DialogueHolder(GamePanel gp) {
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

        if (dialogues[dialogueSet][dialogueIndex] == null) {
            dialogueSet = 0;
        }

        startDialogue(this, dialogueSet);
    }

    public void setAction() {}

    public void setDialogue() {

        dialogues[0][0] = "Bhi Her\nThe Art of Bar";
        dialogues[0][0] = "Jonathan Rilk\nAdvanced Esoteric Alchemy";
    }
}