package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import main.GamePanel;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();

    public TileManager(GamePanel gp) {
        this.gp = gp;

        // READ TILE DATA FILE

        InputStream is = getClass().getResourceAsStream("/res/maps/tiledata.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        String line;

        try {
            while ((line = br.readLine()) != null) {
                fileNames.add(line);
                collisionStatus.add(br.readLine());
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tile = new Tile[fileNames.size()];
        getTileImage();
        mapTileNum = new int[gp.maxMap][gp.maxMapSize][gp.maxMapSize]; 
        
        loadMap(0);
    }

    public void getTileImage() {

        for (int i = 0; i < fileNames.size(); i++) {

            String fileName;
            boolean collision;

            fileName = fileNames.get(i);
            
            if (collisionStatus.get(i).equals("true")) {
                collision = true;
            } else {
                collision = false;
            }
            
            setup(i, fileName, collision);
        }
    }

public void setup(int index, String imageName, boolean collision){
    System.out.println(index);
    System.out.println(collision);
    System.out.println(imageName);
    try {
        tile[index] = new Tile();
        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName));
        tile[index].collision = collision;
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void loadMap(int map) {

    String filePath = "";
    int musicNum = 0;

    if (map == 0) {filePath = "/res/maps/introisland.txt"; musicNum = 0;}
    if (map == 1) {filePath = "/res/maps/treasureisland.txt"; musicNum = 17;}
    if (map == 2) {filePath = "/res/maps/house1.txt"; musicNum = 18;}
    if (map == 3) {filePath = "/res/maps/house2.txt"; musicNum = 19;}
    if (map == 4) {filePath = "/res/maps/store.txt"; musicNum = 20;}

    checkMapSize();
    gp.changeMusic(musicNum);

    try {
        InputStream is = getClass().getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        int col = 0;
        int row = 0;
        
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            
            String line = br.readLine();

            while (col < gp.maxWorldCol) {

                String numbers[] = line.split(" ");

                int num = Integer.parseInt(numbers[col]);

                mapTileNum[map][col][row] = num;

                col++;
            }
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }

        br.close();

    } catch (Exception e){

    };
}

public void checkMapSize() {
    if (gp.currentMap == 0) {
        gp.maxWorldCol = 50;
        gp.maxWorldRow = 50;
    }
     if (gp.currentMap == 1) {
        gp.maxWorldCol = 100;
        gp.maxWorldRow = 100;
     }
}

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
