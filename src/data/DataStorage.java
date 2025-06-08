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

    // ITEMS

    ArrayList<String> itemNames = new ArrayList<>();
}
