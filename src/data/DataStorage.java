package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {
    
    // PLAYER STATS

    int level;
    int maxHealth;
    int health;
    int arrows;
    int strength;
    int dexterity;
    int exp;
    int nextLevelExp;
    int gold;
    int defaultSpeed;
    int worldX;
    int worldY;
    int currentMap;

    // ITEMS

    ArrayList<String> itemNames = new ArrayList<>();
    int currentWeaponSlot;
    int currentShieldSlot;
    int currentShootSlot = 999;
    int currentLightSlot = 999;

    // OBJECTS

    String mapObjectNames[][];
    int mapObjectWorldX[][];
    int mapObjectWorldY[][];
    int mapObjectSolidAreaHeight[][];
    int mapObjectSolidAreaWidth[][];
}
