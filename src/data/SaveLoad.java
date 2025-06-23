package data;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entity.Entity;

import java.io.File;
import java.io.FileInputStream;

import main.GamePanel;
import obj.OBJ_Arrow;
import obj.OBJ_Bench;
import obj.OBJ_Boots;
import obj.OBJ_Bow_Default;
import obj.OBJ_Chest;
import obj.OBJ_Coin;
import obj.OBJ_Hammer;
import obj.OBJ_Heart;
import obj.OBJ_Hitbox1;
import obj.OBJ_House1;
import obj.OBJ_House1_Interior;
import obj.OBJ_House2;
import obj.OBJ_House2_Interior;
import obj.OBJ_Key;
import obj.OBJ_Lantern_Ground;
import obj.OBJ_Lantern_Inv;
import obj.OBJ_Longsword;
import obj.OBJ_Nightingale;
import obj.OBJ_Potion_Red;
import obj.OBJ_Questionmark;
import obj.OBJ_Questionmark2;
import obj.OBJ_Rowboat;
import obj.OBJ_Shield_Default;
import obj.OBJ_Slimeball;
import obj.OBJ_Store;
import obj.OBJ_Store_Interior;
import obj.OBJ_Sword_Default;
import obj.OBJ_Table1;
import obj.OBJ_Table2;
import obj.OBJ_Waterfountain;

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
            case OBJ_Lantern_Inv.objName: obj = new OBJ_Lantern_Inv(gp); break;
            case OBJ_Arrow.objName: obj = new OBJ_Arrow(gp); break;
            case OBJ_Bench.objName: obj = new OBJ_Bench(gp); break;
            case OBJ_Boots.objName: obj = new OBJ_Boots(gp); break;
            case OBJ_Chest.objName: obj = new OBJ_Chest(gp); break;
            case OBJ_Coin.objName: obj = new OBJ_Coin(gp); break;
            case OBJ_Heart.objName: obj = new OBJ_Heart(gp); break;
            case OBJ_Hitbox1.objName: obj = new OBJ_Hitbox1(gp); break;
            case OBJ_House1_Interior.objName: obj = new OBJ_House1_Interior(gp); break;
            case OBJ_House1.objName: obj = new OBJ_House1(gp); break;
            case OBJ_House2_Interior.objName: obj = new OBJ_House2_Interior(gp); break;
            case OBJ_House2.objName: obj = new OBJ_House2(gp); break;
            case OBJ_Key.objName: obj = new OBJ_Key(gp); break;
            case OBJ_Lantern_Ground.objName: obj = new OBJ_Lantern_Ground(gp); break;
            case OBJ_Questionmark.objName: obj = new OBJ_Questionmark(gp); break;
            case OBJ_Questionmark2.objName: obj = new OBJ_Questionmark2(gp); break;
            case OBJ_Rowboat.objName: obj = new OBJ_Rowboat(gp); break;
            case OBJ_Slimeball.objName: obj = new OBJ_Slimeball(gp); break;
            case OBJ_Store_Interior.objName: obj = new OBJ_Store_Interior(gp); break;
            case OBJ_Store.objName: obj = new OBJ_Store(gp); break;
            case OBJ_Table1.objName: obj = new OBJ_Table1(gp); break;
            case OBJ_Table2.objName: obj = new OBJ_Table2(gp); break;
            case OBJ_Waterfountain.objName: obj = new OBJ_Waterfountain(gp); break;
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
                ds.worldX = gp.player.worldX;
                ds.worldY = gp.player.worldY;

                // Save player inventory

                for (int i=0;i<gp.player.inventory.size();i++) {
                    ds.itemNames.add(gp.player.inventory.get(i).name);
                }

                // Save inventory equipment status

                ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
                ds.currentShieldSlot = gp.player.getCurrentShieldSlot();
                ds.currentShootSlot = gp.player.getCurrentShootSlot();
                ds.currentLightSlot = gp.player.getCurrentLightSlot();

                // Save status of objects on map

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
                            System.out.println(gp.obj[mapNum][i].name);
                        }
                    }
                }

                // Write datastorage object

                oos.writeObject(ds);
                gp.saveExists = true;
            }
            
        } catch (Exception e) {
            System.out.println("Save Exception");
        }
    }
    
    public void load() {
        
        try {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")))) {

                // Read datastorage object

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
                gp.player.worldX = ds.worldX;
                gp.player.worldY = ds.worldY;
                
                // Load player inventory
                
                gp.player.inventory.clear();

                for (int i=0;i<ds.itemNames.size();i++) {
                    gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                }
                System.out.println("loaded player inv");
                
                // Load player equipment status
                
                gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
                gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
                if (ds.currentShootSlot != 999) {gp.player.currentShoot = gp.player.inventory.get(ds.currentShootSlot);}
                if (ds.currentLightSlot != 999) {gp.player.currentLight = gp.player.inventory.get(ds.currentLightSlot);}
                gp.player.getAttack();
                gp.player.getDefense();
                gp.player.getAttackImage();
                if (gp.player.currentShoot != null) {
                    gp.player.getShootImage();
                }
                System.out.println("laoded images n shit");
                
                // Load status of objects on map
                
                for (int mapNum=0;mapNum<gp.maxMap;mapNum++) {
                    for (int i=0;i<gp.obj[1].length;i++) {
                        if (ds.mapObjectNames[mapNum][i].equals("NA")) {
                            System.out.println("object nullified");
                            gp.obj[mapNum][i] = null;
                        } else {
                            System.out.println(gp.obj[mapNum][i].name + " loaded");
                            gp.obj[mapNum][i] = getObject(ds.mapObjectNames[mapNum][i]);
                            gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                            gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        }
                    }
                }
            }
            
        } catch(Exception e) {
            System.out.println("Load Exception");
        }
    }
}