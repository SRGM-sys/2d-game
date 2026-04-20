
package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
    
    KeyHandler keyH;
    
    // Estas variables van a ser la camara del jugador
    public final int screenX;
    public final int screenY;
    

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        
        // Esto es para obtener el punto medio de la pantalla
        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;
        
        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y; 
        
        
        setDefaultValues();
        getPlayerImage();
    }
    
    // Vamos a establecer las configuraciones del Player
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }
    
    public void getPlayerImage(){
        
        up1 = setup("player", "boy_up_1");
        up2 = setup("player", "boy_up_2");
        down1 = setup("player", "boy_down_1");
        down2 = setup("player", "boy_down_2");
        left1 = setup("player", "boy_left_1");
        left2 = setup("player", "boy_left_2");
        right1 = setup("player", "boy_right_1");
        right2 = setup("player", "boy_right_2");
        
    }
    
    /* VAMOS A PONER EL UPDATE Y DRAW PARA CADA ENTIDAD
    Esto lo haremos para evitar un código gigantesco en GamePanel*/
    public void update(){
        
        // Este gran if me va a permtir que el personaje no se mueva si el 
        // usuario no presiona ninguna tecla
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if(keyH.upPressed){
                direction = "up";
            }
            else if(keyH.downPressed){
                direction = "down";
            }
            else if(keyH.leftPressed){
                direction = "left";
            }
            else if(keyH.rightPressed){
                direction = "right";
            }
            
            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this); // Polimorfismo, Plater es una Entity
            
            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            
            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            
            // IF COLLISION IS FALSE, PLAYER CAN MOVE
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
    }
    
    public void interactNPC(int i){
        if(i != 999){
            System.out.println("Hit NPC");
        }
    }
    
    public void pickUpObject(int i){
        // Si i = 999, significa que no hemos tocado el objeto
        if(i != 999){
            
        }
    }
    
    // Organizamos las imágenes en cada caso de movimiento del personaje
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        
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
        
        g2.drawImage(image, screenX, screenY, null);
    }
    
    
}
