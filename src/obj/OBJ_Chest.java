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
        
            if (gp.player.hasKey != 0 && gp.keyH.ePressed == true && this.usedObject == false) {
                gp.playSE(12);
                gp.ui.showMessage("You found an item!");
                for (int i = 0; i < gp.obj.length;i++) {
                    if (gp.obj[gp.currentMap][i] == null) {
                        gp.obj[gp.currentMap][i] = new OBJ_Coin(gp);
                        gp.obj[gp.currentMap][i].worldX = gp.tileSize*8;
                        gp.obj[gp.currentMap][i].worldY = gp.tileSize*40;
                        break;
                    }
                }
                this.down1 = setup("/res/objects/chestused", gp.tileSize, gp.tileSize+15);
                this.worldY -= 15;
                solidArea.height += 10;
                this.usedObject = true;
                entity.hasKey--;
                for (int i=0;i<gp.player.inventory.size();i++) {
                    if (gp.player.inventory.get(i).name.equals("Creamor Key")) {
                        gp.player.inventory.remove(i);
                        break;
                    }
                }
            } else {
                if (this.usedObject == false && gp.player.hasKey == 0 && gp.keyH.ePressed == true) {
                    gp.ui.showMessage("You need a key!");
                }
            }
        }
}