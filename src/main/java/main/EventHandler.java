package main;

import java.awt.Rectangle;

// Esta clase manejara diferentes eventos del jugador en puntos específicos del mapa

public class EventHandler {
    
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;
    
    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }
    
    public void checkEvent(){
        
        // Estas van a ser las coordenadas del evento y la dirección del jugador
        if(hit(27,16, "right")) damagePit(gp.dialogueState);
        if(hit(23,12, "up")) healingPool(gp.dialogueState);
            
    }
    
    // Va a ver si el usuario se acerca al rectangulo evento
    public boolean hit(int eventCol, int eventRow, String reqDirection){
        
        boolean hit = false;
        gp.player.solidArea.x += gp.player.worldX;
        gp.player.solidArea.y += gp.player.worldY;
        eventRect.x += eventCol*gp.tileSize;
        eventRect.y += eventRow*gp.tileSize;
        
        if(gp.player.solidArea.intersects(eventRect)){
            // QUE XD: Bueno básicamente si ambos colisionan es true
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
            }
        }
        
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
        
        return hit;
        
    }
    
    public void damagePit(int gameState){
        
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Pisaste una trampa";
        gp.player.life--;
        
    }
    
    public void healingPool(int gameState){
        if(gp.keyH.enterPressed){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Observar el lago te llena de \ndeterminación";
            gp.player.life = gp.player.maxLife;
        }
        
        gp.keyH.enterPressed = false;
    }
    
    
    
}
