package entity;

import main.GamePanel;

public class NPC_Rock extends Entity {

    public NPC_Rock(GamePanel gp) {

        super(gp);
        speed = 0;
        direction = "still";
        setDialogue();
    }

    public void getImage() {}

public void setDialogue() {
    dialogues[0] = "What brings you to this place? \nI hope it's not that treasure...";
    dialogues[1] = "I've seen plenty youngins just like you,\ngreener than the grass of Midland.";
    dialogues[2] = "Most of their stories don't end too well...";
    dialogues[3] = "That place is beautiful on the surface,\nbut underneath...";
    dialogues[4] = "I can tell you more when you're ready.\nTry increasing your speed as a start.";
}

public void setAction() {}

public void speak() {
    super.speak();
}
}