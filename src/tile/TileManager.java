package tile;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.GamePanel;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.MAX_WORLD_ROW][gp.MAX_WORLD_COL];

        getTileImage();
        loadMap("/maps/worldMap01.txt");
    }

    public void getTileImage() {
        try {
            
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tilesAsset/grass00.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tilesAsset/wall.png"));
            tile[1].collision = true;
            
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tilesAsset/water00.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tilesAsset/tree.png"));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tilesAsset/earth.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tilesAsset/floor01.png"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        try {

            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));


            for (int row = 0; row < gp.MAX_WORLD_ROW; row++) {

                String line = br.readLine();
                String numbers[] = line.split(" ");
                System.out.println(numbers);

                for (int col = 0; col <gp.MAX_WORLD_COL; col++) {

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

        for (int worldRow = 0; worldRow < gp.MAX_WORLD_ROW; worldRow++) {
            for (int worldCol = 0; worldCol <gp.MAX_WORLD_COL; worldCol++) {
                int tileNum = mapTileNum[worldRow][worldCol];
                int worldX = worldCol * gp.TILE_SIZE;
                int worldY = worldRow * gp.TILE_SIZE;
                int screenX = worldX - gp.player.worldX + gp.player.SCREENX;
                int screenY = worldY - gp.player.worldY + gp.player.SCREENY;

                if (worldX > gp.player.worldX - gp.player.SCREENX - gp.TILE_SIZE && 
                    worldX < gp.player.worldX + gp.player.SCREENX + gp.TILE_SIZE && 
                    worldY > gp.player.worldY - gp.player.SCREENY - gp.TILE_SIZE && 
                    worldY < gp.player.worldY + gp.player.SCREENY + gp.TILE_SIZE) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, 
                            gp.TILE_SIZE, gp.TILE_SIZE, null);        
                }    
            }
        }
    }
}
