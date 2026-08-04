
package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class Entity {
    
    // MOVIMIENTO
    public GamePanel gp;
    public int worldX,worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage atkUp1, atkUp2, atkDown1, atkDown2, atkLeft1, atkLeft2, atkRight1, atkRight2;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int px;
    
    // COLISIONES
    public Rectangle solidArea = new Rectangle(0,0,48,48); // Cuadro por defecto
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    
    // DIÁLOGOS
    public int actionLockCounter = 0;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    
    // OBJECT CLASS
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    
    // ESTADO DEL JUGADOR
    public int maxLife;
    public int life;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public boolean attacking = false;
    
    // ESTADO GENERAL DE LA ENTIDAD
    public int type; // 0 = player ; 1 = npc ; 2 = monster
    
    public Entity(GamePanel gp){
        this.gp = gp;
        this.px = gp.tileSize;
    }
    
    public void setAction(){}
    
    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gp.player.direction){
            case "up": direction = "down"; break;
            case "left": direction = "right"; break;
            case "down": direction = "up"; break;
            case "right": direction = "left"; break;
        }
    }
    
    
    public void update(){
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.mon);
        boolean contactPlayer = gp.cChecker.checkerPlayer(this);
        
        // Si el jugador choca con un mounstro entonces
        if(this.type == 2 && contactPlayer){
            if(!gp.player.invincible){
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }
        
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
        
        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    
    // Vamos a crear una función para dibujara los npc
    public void draw(Graphics2D g2){
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
            
            if(invincible){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }
            
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
         
    }
    
    // Vamos a escalar los personajes antes de entrar al bucle
    public BufferedImage setup(String folder, String imageName, int width, int height){
        
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/"+folder+"/"+imageName+".png"));
            image = uTool.scaleImage(image, width, height);
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return image;
    }
    
}
