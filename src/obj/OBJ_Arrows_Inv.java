package obj;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Arrows_Inv extends Projectile {

    public final static String objName = "Arrows";

    GamePanel gp;

    public OBJ_Arrows_Inv(GamePanel gp) {
        
        super(gp);
        this.gp = gp;

        name = objName;
        description = name + "\n\nA quiver of 5 arrows.";
        price = 15;
        type = type_consumable;
        getImage();
    }
    
    public void getImage() {
        image = setup("/res/objects/arrow", 45, 45);
    }

    public void use(Entity entity) {
        
        gp.player.arrows += 5;
        dialogues[0][0] = " You got 5 Arrows!";
        gp.keyH.spacePressed = false;
        gp.playSE(13);
        startDialogue(this, 0);
    }
}
