package obj;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {

    public final static String objName = "Chest";

    GamePanel gp;

    public OBJ_Chest(GamePanel gp) {
        
        super(gp);

        this.gp = gp;
        name = objName;
        type = type_static_object;
        down1 = setup("/res/objects/chest", gp.tileSize, gp.tileSize);
        collision = true;
    }

    public void use(Entity entity) {

        boolean playerHasChestKey = false;
        for (Entity e : gp.player.inventory) {
            if (e.name.equals("Chest Key")) {
                playerHasChestKey = true;
            }
        }
        
            if (playerHasChestKey == true && gp.keyH.ePressed == true && this.usedObject == false) {
                gp.playSE(12);
                gp.ui.showMessage("You found an item!");
                for (int i = 0; i < gp.obj.length; i++) {
                    if (gp.obj[gp.currentMap][i] == null) {
                        gp.obj[gp.currentMap][i] = new OBJ_Nightingale(gp);
                        gp.obj[gp.currentMap][i].worldX = gp.tileSize*55;
                        gp.obj[gp.currentMap][i].worldY = gp.tileSize*48;
                        break;
                    }
                }
                this.down1 = setup("/res/objects/chestused", gp.tileSize, gp.tileSize+15);
                this.worldY -= 15;
                solidArea.height += 10;
                this.usedObject = true;
                gp.player.hasChestKey = false;
                for (Entity e : gp.player.inventory) {
                    if (e.name.equals("Chest Key")) {
                        gp.player.inventory.remove(e);
                        break;
                    }
                }
            } else {
                if (this.usedObject == false && gp.player.hasChestKey == false && gp.keyH.ePressed == true) {
                    gp.ui.showMessage("You need a key!");
                }
            }
        }
}