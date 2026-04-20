package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import object.Obj_Key;

/*
* Esta clase manejará todo lo relacionado a la interfaz de usuario
* Mensajes de texto
* Iconos de elementos
*/


public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public boolean gameFinished = false;
    public String message;
    int messageCounter = 0;
    
    double playTime; // Se encargará de cronometrar el tiempo de juego
    DecimalFormat dFormat = new DecimalFormat("#0,00"); // Le daremos formato al cronometro

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }
    
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    /*
    * Esta función va a dibujar el texto de ayuda de cuantas llaves
    * tenemos en nuestro inventario actualmente
    */
    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        
        if(gp.gameState == gp.playState){
            
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
    }
    
    public void drawPauseScreen(){
       
        g2.setFont(arial_80B);
        String text = "PAUSED";
        int y = gp.screenHeight/2;
        
        g2.drawString(text, getCenterX(text), y);
    }
    
    public int getCenterX(String text){
        // Si solo pongo "int x = gp.screenWidth/2;", no se va a presentar el texto en la mitad
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }
    
}
