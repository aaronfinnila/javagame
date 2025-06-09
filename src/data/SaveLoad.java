package data;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entity.Entity;

import java.io.File;
import java.io.FileInputStream;

import main.GamePanel;
import obj.OBJ_Bow_Default;
import obj.OBJ_Hammer;
import obj.OBJ_Longsword;
import obj.OBJ_Nightingale;
import obj.OBJ_Potion_Red;
import obj.OBJ_Shield_Default;
import obj.OBJ_Sword_Default;

public class SaveLoad {

    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }

    public Entity getObject(String itemName) {
        
        Entity obj = null;

        switch (itemName) {
            case OBJ_Hammer.objName: obj = new OBJ_Hammer(gp); break;
            case OBJ_Sword_Default.objName: obj = new OBJ_Sword_Default(gp); break;
            case OBJ_Nightingale.objName: obj = new OBJ_Nightingale(gp); break;
            case OBJ_Longsword.objName: obj = new OBJ_Longsword(gp); break;
            case OBJ_Bow_Default.objName: obj = new OBJ_Bow_Default(gp); break;
            case OBJ_Potion_Red.objName: obj = new OBJ_Potion_Red(gp); break;
            case OBJ_Shield_Default.objName: obj = new OBJ_Shield_Default(gp); break;
        }

        return obj;
    }
    
    public void save() {

        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")))) {
                DataStorage ds = new DataStorage();

                // Save player stats

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

                // Save player inventory

                for (int i=0;i<gp.player.inventory.size();i++) {
                    ds.itemNames.add(gp.player.inventory.get(i).name);
                }

                // Save inventory equipment status

                ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
                System.out.println(ds.currentWeaponSlot);
                ds.currentShieldSlot = gp.player.getCurrentShieldSlot();
                if (gp.player.currentShoot != null) {ds.currentShootSlot = gp.player.getCurrentShootSlot();}
                if (gp.player.currentLight != null) {ds.currentLightSlot = gp.player.getCurrentLightSlot();}

                // Save objects on map status

                ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
                ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
                ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];

                for (int mapNum=0;mapNum<gp.maxMap;mapNum++) {
                    for (int i=0;i<gp.obj[1].length;i++) {
                        if (gp.obj[mapNum][i] == null) {
                            ds.mapObjectNames[mapNum][i] = "NA";
                        } else {
                            ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                            ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                            ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
                        }
                    }
                }


                // Write datastorage object

                oos.writeObject(ds);
            }
            
        } catch (Exception e) {
            System.out.println("Save Exception");
        }
    }

    public void load() {

        try {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")))) {
                DataStorage ds = (DataStorage)ois.readObject();

                // Load player stats

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

                // Load player inventory

                gp.player.inventory.clear();

                for (int i=0;i<ds.itemNames.size();i++) {
                    gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                }

                // Load player equipment status

                gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
                gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
                gp.player.currentShoot = gp.player.inventory.get(ds.currentShootSlot);
                gp.player.currentLight = gp.player.inventory.get(ds.currentLightSlot);
                gp.player.getAttack();
                gp.player.getDefense();
                gp.player.getAttackImage();
                gp.player.getShootImage();

                // Load map object status

                for (int mapNum=0;mapNum<gp.maxMap;mapNum++) {
                    for (int i=0;i<gp.obj[1].length;i++) {
                        if (ds.mapObjectNames[mapNum][i].equals("NA")) {
                            gp.obj[mapNum][i] = null;
                        } else {
                            gp.obj[mapNum][i] = getObject(ds.mapObjectNames[mapNum][i]);
                            gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                            gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        }
                    }
                }
            }

            // Read datastorage object

            
        } catch(Exception e) {
            System.out.println("Load Exception");
        }
    }
}