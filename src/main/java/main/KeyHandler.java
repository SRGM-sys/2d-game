package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Con esto manejaremos el juego mediantes las teclas del teclado

public class KeyHandler implements KeyListener{
    // KeyListener: This listener interface for reciving keyboard events 
    
    GamePanel gp;
    
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    public boolean checkDrawTime = false;
    
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    
    @Override // Generalmente este no se usa
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode(); // Nos devuelve el código de la tecla presionada
        
        // TITLE STATE
        if(gp.gameState == gp.titleState){
            if(code == KeyEvent.VK_W) gp.ui.commandNum--;
            if(code == KeyEvent.VK_S) gp.ui.commandNum++;
            gp.ui.controlCommandNum();
            
            if(code == KeyEvent.VK_ENTER){
                switch(gp.ui.commandNum){
                    case 0: 
                        gp.gameState = gp.playState; 
                        gp.playMusic(0);
                        break;
                    case 1: break; // Función aún no disponible
                    case 2: System.exit(0); break;
                }
            }
        }
        
        // PLAY STATE
        if(gp.gameState == gp.playState){
            // El usuario se movera con: WSAD
            if(code == KeyEvent.VK_W) upPressed = true;
            if(code == KeyEvent.VK_S) downPressed = true;
            if(code == KeyEvent.VK_A) leftPressed = true;
            if(code == KeyEvent.VK_D) rightPressed = true;
      
            if(code == KeyEvent.VK_P) gp.gameState = gp.pauseState;
            if(code == KeyEvent.VK_ENTER) enterPressed = true;
                
            // Show Draw Time: Cada vez que presione T, cambia de estado
            if(code == KeyEvent.VK_T){
                if(checkDrawTime){
                    checkDrawTime = false;
                } else{
                    checkDrawTime = true;
                }
            }
        }
        
        // PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            // Vamos a alternar entre Pausa y Play
            if(code == KeyEvent.VK_P) gp.gameState = gp.playState;
        }
        
        // DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }
        
        
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode(); 
       
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
