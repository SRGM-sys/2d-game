
package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class Entity {
    
    public GamePanel gp;
    
    public int worldX,worldY;
    public int speed;
    
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    // Cuadro invisible que nos servirá para las colisiones
    public Rectangle solidArea = new Rectangle(0,0,48,48); // Cuadro por defecto
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    
    public int actionLockCounter = 0;
    
    public Entity(GamePanel gp){
        this.gp = gp;
    }
    
    
    // Vamos a escalar los personajes antes de entrar al bucle
    public BufferedImage setup(String folder, String imageName){
        
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/"+folder+"/"+imageName+".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return image;
    }
    
    public void setAction(){
        
    }
    
    public void update(){
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkerPlayer(this);
        
        if(!collisionOn){
                
            switch(direction){
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left":worldX -= speed; break;
                case "right": worldX += speed; break;
            }   
        }

        // Aquí el personaje cambiará de imagen en cada frame para simular movimiento
        spriteCounter++;
        if(spriteCounter > 12){ //Velocidad de cambio
            if(spriteNum == 1) spriteNum = 2;
            else if(spriteNum == 2) spriteNum = 1;
            spriteCounter  = 0;
        }       
    }
    
    // Vamos a crear una función para dibujara los npc
    public void drawNPC(Graphics2D g2){
        BufferedImage image = null;
        
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
            
            switch(direction){
            case "up":     
                if (spriteNum == 1) image = up1;    
                else if(spriteNum == 2) image = up2;
                break;
            case "down":   
                if (spriteNum == 1) image = down1; 
                else if(spriteNum == 2) image = down2;
                break;
            case "left":   
                if (spriteNum == 1) image = left1;  
                else if(spriteNum == 2) image = left2;
                break;
            case "right":  
                if (spriteNum == 1) image = right1; 
                else if(spriteNum == 2) image = right2;
                break;
            }
            
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
        
        
    }
}
