
package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum [] []; //Usaremos los números del archivo de texto para dibujarlo

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10]; // Cantidad de diferentes bloques
        mapTileNum = new int [gp.maxWorldCol] [gp.maxWorldRow];  
        getTileImage();
        loadMap("/maps/world01.txt");
    }
    
    public void getTileImage(){
        try{
            
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;
            
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision = true;
            
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
            
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;
            
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
            
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
                    
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String  line = br.readLine();
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }

            br.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g2){
        /* SEPARACIÓN DE IMÁGENES
        Debe ir de 48 en 48, para que esten uno alado del otro*/
      
        int worldCol = 0;
        int worldRow = 0;
     
        
        // Este bucle pintará toda la pantalla visible
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            
            // Quiero obtener el número que se encuentra en [col] [row]
            int index = mapTileNum[worldCol][worldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX; 
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            int op1 = gp.player.worldX - gp.player.screenX;
            int op2 = gp.player.worldX + gp.player.screenX;
            int op3 = gp.player.worldY - gp.player.screenY;
            int op4 = gp.player.worldY + gp.player.screenY;
            
            /* Este condicional es CLAVE (MEJORA EL RENDIMIENTO)
            No queremos dibujar un bloque que este a 500 metros de nosotros
            Se iran dibujando nuevos bloques pero solo a nuestro alrededor */
            if((worldX + gp.tileSize > op1) && (worldX - gp.tileSize < op2) && 
               (worldY + gp.tileSize > op3) && (worldY - gp.tileSize <= op4)){
                g2.drawImage(tile[index].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            
            worldCol++;
            
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
                
            }   
        }
        
    }
    
    
    
    
}
