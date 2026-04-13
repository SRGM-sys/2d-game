package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;

public class SuperObject {
    
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    // Rango de Colisión para todos los Objetos
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0; 
    public int solidAreaDefaultY = 0;
    
    public void draw(Graphics2D g2, GamePanel gp){
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
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
   
}
