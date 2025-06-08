package data;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;

import main.GamePanel;

public class SaveLoad {

    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }
    
    public void save() {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

            ds.level = gp.player.level;
            ds.maxHealth = gp.player.maxHealth;
            ds.health = gp.player.health;
            ds.arrows = gp.player.arrows;
            ds.strength = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.gold = gp.player.gold;
            ds.defaultSpeed = gp.player.defaultSpeed;

            // Write datastorage object

            oos.writeObject(ds);

        } catch (Exception e) {
            System.out.println("Save Exception");
        }
    }

    public void load() {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            // Read datastorage object

            DataStorage ds = (DataStorage)ois.readObject();

            gp.player.level = ds.level;
            gp.player.maxHealth = ds.maxHealth;
            gp.player.health = ds.health;
            gp.player.arrows = ds.arrows;
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.gold = ds.gold;
            gp.player.defaultSpeed = ds.defaultSpeed;

            for (int i=0;i<gp.player.inventory.size();i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
            }
            
        } catch(Exception e) {
            System.out.println("Load Exception");
        }
    }
}
//TODO implement player item saving