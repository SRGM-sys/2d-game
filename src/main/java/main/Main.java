package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 'x' para salir
        window.setResizable(false); // No cambiar sus dimensiones
        window.setTitle("2D Adventure"); // Titulo
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        window.pack();
        
        window.setLocationRelativeTo(null); // Aparecera en el centro de la pantalla
        window.setVisible(true);
        
        gamePanel.setupGame();
        gamePanel.startGameThread();
        
    }
}
