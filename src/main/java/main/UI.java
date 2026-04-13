package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.Obj_Key;

public class UI {
    
    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    
    public boolean messageOn = false;
    public boolean gameFinished = false;
    public String message;
    int messageCounter = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        Obj_Key key = new Obj_Key();
        keyImage = key.image; 
    }
    
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    // Vamos a dibujar texto de ayuda en pantalla
    /* Instanciar objetos en métodos que se llaman a 60FPS podría
    hacer que el programa se vuelva lento, por eso evitemos eso */
    public void draw(Graphics2D g2){
        
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        
        if(gameFinished){
            String text;
            int textLenght;
            int x;
            int y;
            
            text = "You found the treasure!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLenght/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);
            
            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLenght/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);
            
        } else{
            
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("X "+gp.player.hashKey, 74, 65);

            if(messageOn){
                g2.setFont(g2.getFont().deriveFont(20F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*11);
                messageCounter++;

                /* Dibuja a 60 FPS
                El método draw se ejecuta 60 veces por segundo, y aumentara el contador
                en este condicional, el mensaje desaparecera luego de 2 segundos */
                if(messageCounter > 120 ){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }  
    }
    
}
