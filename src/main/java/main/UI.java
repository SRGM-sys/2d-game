package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

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
    public String message;
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue;

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
        
        // Validamos el estado del juego
        if(gp.gameState == gp.playState){
            
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
    }
    
    public void drawPauseScreen(){
        g2.setFont(arial_80B);
        String text = "PAUSED";
        int y = gp.screenHeight/2;
        g2.drawString(text, getCenterX(text), y);
    }
    
    public void drawDialogueScreen(){
        // Se va a mostrar un Dialog Window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*3;
        
        drawSubWindow(x,y,width,height);
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28f));
        x += gp.tileSize/2;
        y += gp.tileSize;
        
        // Hacemos esta wea de aquí para que funcione el salto de línea \n
        for(String line : currentDialogue.split(("\n"))){
            g2.drawString(line, x, y);
            y +=35;
        }
    }
    
    public void drawSubWindow(int x, int y, int width, int height){
        // El cuarto parámetro del color indica transparencia
        Color c1 = new Color(0,0,0, 0.75f); // De esta manera indico el porcentaje %
        Color c2 = new Color(255,255,255);
        
        g2.setColor(c1);
        g2.fillRoundRect(x, y, width, height, 35, 35); // Rectangulo redondo
        g2.setColor(c2);
        g2.setStroke(new BasicStroke(2)); // Grosor del borde
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
        
        
    }
    
    public int getCenterX(String text){
        // Si solo pongo "int x = gp.screenWidth/2;", no se va a presentar el texto en la mitad
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }
    
}
