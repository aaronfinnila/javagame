package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bookshelf extends Entity {

    public final static String objName = "Bookshelf";

    GamePanel gp;
    
    public OBJ_Bookshelf(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = objName;
        type = type_static_object;
        down1 = setup("/res/objects/bookshelf", gp.tileSize*3, gp.tileSize*3);
        collision = true;
        solidArea.height = gp.tileSize*3;
        solidArea.width = gp.tileSize*3;
        setDialogue();
    }

    public void speak() {
        System.out.println("bookshelfspeak");

        super.speak();
        startDialogue(this, 0);
    }

    public void setAction() {}

    public void setDialogue() {

        dialogues[0][0] = "So many books...";
    }
}