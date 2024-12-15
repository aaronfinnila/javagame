package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[50];

        mapTileNum = new int[gp.maxWorldCol] [gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/worldmap02.txt");
    }

    public void getTileImage() {

        // PLACEHOLDER

        setup(0, "grass1", false);

        setup(1, "grass1", false);

        setup(2, "placeholder", false);

        setup(3, "placeholder", false);

        setup(4, "placeholder", false);

        setup(5, "placeholder", false);

        setup(6, "placeholder", false);

        setup(7, "placeholder", false);

        setup(8, "placeholder", false);
 
        setup(9, "placeholder", false);

        // GRASS

        setup(10, "grass1", false);

        setup(11, "grass2", false);

        setup(12, "grass3", false);

        setup(22, "grass4", false);
        
        setup(14, "gwbottom", true);

        setup(15, "gwleft", true);

        setup(16, "gwright", true);

        setup(17, "gwtop", true);

        setup(18, "gwtopleft", true);

        setup(19, "gwtopright", true);

        setup(20, "gwbottomleft", true);

        setup(21, "gwbottomright", true);

        // WATER

        setup(13, "water", true);

        // ROCK

        setup(23, "rock1", true);

        setup(24, "rock2", true);
}

public void setup(int index, String imageName, boolean collision){

    UtilityTool utool = new UtilityTool();

    try {
        tile[index] = new Tile();
        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
        tile[index].collision = collision;
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void loadMap(String filePath) {

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

                mapTileNum[col][row] = num;

                // RANDOMIZE GRASS TILES
                
                if (num == 11) {
                    int numb = random.nextInt(3);
                    if (numb == 0) {
                        mapTileNum[col][row] = 11;
                    }
                    if (numb == 1) {
                        mapTileNum[col][row] = 12;
                    }
                    if (numb == 2) {
                        mapTileNum[col][row] = 22;
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

            int tileNum = mapTileNum[worldCol][worldRow];

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
