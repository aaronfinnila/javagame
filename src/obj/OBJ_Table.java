package obj;
import entity.Entity;
import main.GamePanel;

public class OBJ_Table extends Entity {

    public final static String objName = "Table";

    GamePanel gp;
    int images = 0;

    public OBJ_Table(GamePanel gp) {

        super(gp);
        this.gp = gp;
        name = objName;
        collision = true;
        speed = 0;
        type = type_animated_object;
        solidArea.height = 50*2;
        solidArea.width = 65*2;
        setDialogue();
        getImage();
    }
    
    public void getImage() {
        
        down1 = setup("/res/objects/table1", 65*2, 55*2);
        down2 = setup("/res/objects/table2", 65*2, 55*2);
        up1 = setup("/res/objects/table3", 65*2, 55*2);
        up2 = setup("/res/objects/table4", 65*2, 55*2);
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

        if (direction.equals("down")) {
            startDialogue(this, 0);
        } else {
            startDialogue(this, 1);
        }
    }

    public void setDialogue() {

        dialogues[0][0] = "25th of July, 2025\nMonday\n10:30pm";
        dialogues[0][1] = "Dear diary, today was an interesting day.";
        dialogues[0][2] = "While I was out for a stroll near the water fountain, I\noverheard someone speak of a secret room at the local\nstore.";
        dialogues[0][3] = "They said that the shopkeeper stored secret items and\npossessions there, those that he didn't want others to find.";
        dialogues[0][4] = "Personally, I think that's complete rubbish. I mean, I've\nknown Michael since forever, and he never seemed like the\ntype to hide things.";
        dialogues[0][5] = "I mean, we were best friends for goodness sake!\nIf there's someone he would have told about some cool stuff\nhe's found, it's me.";
        dialogues[0][6] = "Though, we haven't really been close like that for a while..\nNot after the incident...";
        dialogues[0][7] = "Poor Michael, he's really been through a lot...";

        dialogues[1][0] = "Michael Jameson\nThe forgotten history of Treasure Island";
    }
}