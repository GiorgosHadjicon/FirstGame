package tile;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.GamePanel;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.MAX_SCREEN_ROW][gp.MAX_SCREEN_COLUMN];

        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage() {
        try {
            
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tilesAsset/grass00.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tilesAsset/wall.png"));
            
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tilesAsset/water00.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tilesAsset/tree.png"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        //int row = 0;
        //int col = 0;

        try {

            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));


            for (int row = 0; row < gp.MAX_SCREEN_ROW; row++) {

                String line = br.readLine();
                String numbers[] = line.split(" ");
                System.out.println(numbers);

                for (int col = 0; col <gp.MAX_SCREEN_COLUMN; col++) {

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[row][col] = num;
                }
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.MAX_SCREEN_COLUMN && row < gp.MAX_SCREEN_ROW) {

            int tileNum = mapTileNum[row][col];

            g2.drawImage(tile[tileNum].image, x, y, gp.TILE_SIZE, gp.TILE_SIZE, null);
            col++;
            x += gp.TILE_SIZE;

            if (col == gp.MAX_SCREEN_COLUMN) {
                col = 0;
                x = 0;
                row++;
                y += gp.TILE_SIZE;
            }
        }
    }
}
