package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

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

        is = getClass().getResourceAsStream("/res/maps/sample.txt");
        br = new BufferedReader(new InputStreamReader(is));

        try {
            String line2 = br.readLine();
            String maxTile[] = line2.split(" ");

            gp.maxWorldCol = maxTile.length;
            gp.maxWorldRow = maxTile.length;
            mapTileNum = new int[gp.maxMap][gp.maxWorldCol] [gp.maxWorldRow]; 

            br.close();
        } catch (IOException e) {
            System.out.println("Exception!");
        }
        loadMap("/res/maps/sample.txt", 0);
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

    try {
        tile[index] = new Tile();
        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName));
        tile[index].collision = collision;
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void loadMap(String filePath, int map) {

    try {
        InputStream is = getClass().getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        Random random = new Random();

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

            String line = br.readLine();

            while (col < gp.maxWorldCol) {

                String numbers[] = line.split(" ");

                int num = Integer.parseInt(numbers[col]);

                mapTileNum[map][col][row] = num;

                // RANDOMIZE TILES

                if (num == 11) {
                    int numb = random.nextInt(3);
                    if (numb == 0) {
                        mapTileNum[map][col][row] = 11;
                    }
                    if (numb == 1) {
                        mapTileNum[map][col][row] = 12;
                    }
                    if (numb == 2) {
                        mapTileNum[map][col][row] = 22;
                    }
                }
                if (num == 13) {
                    int numb = random.nextInt(10);
                    if (numb == 0) {
                        mapTileNum[map][col][row] = 13;
                    } else {
                        mapTileNum[map][col][row] = 58;
                    }
                }
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
