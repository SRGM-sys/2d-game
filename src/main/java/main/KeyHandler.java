package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    // KeyListener: This listener interface for reciving keyboard events 
    
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean checkDrawTime = false;
    
    @Override // Generalmente este no se usa
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode(); // Nos devuelve el código de la tecla presionada
        
        // El usuario se movera con: WSAD
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        
        // Show Draw Time: Cada vez que presione T, cambia de estado
        if(code == KeyEvent.VK_T){
            if(checkDrawTime){
                checkDrawTime = false;
            } else{
                checkDrawTime = true;
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
