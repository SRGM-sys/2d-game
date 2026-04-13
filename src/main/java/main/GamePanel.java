package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTINGS
    final int OriginalTileSize = 16; // 16px * 16px
    /* 16PX
    Eran el tamaño ideal en consolas antiguas, pero ahora en computadoras
    actuales las resoluciones son muy altas, y 16px es muy pequeño
    CONCLUSIÓN: Vamos a escalarlo*/ 
    final int scale = 3;
    public final int tileSize = OriginalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;  // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    
    // FPS
    int FPS = 60;
    
    // SYSTEM
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound sound = new Sound(); // Sound Effects
    Thread gameThread;
    public TileManager tileM = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public UI ui = new UI(this);
    public AssetSetter aSetter = new AssetSetter(this);
    
    // ENTITY & OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; // Mostrar 10 objetos a la vez
    
    
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //Mayor renderizado
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void setupGame(){
        aSetter.setObject();
        playMusic(0);
    }
        
    public void startGameThread(){
        gameThread = new Thread(this); // Le pasamos esta clase (Implementa Run)
        gameThread.start();
    }

    // Haré que esta clase se comporte como un Hilo para simular movimiento (FPS)
    @Override
    public void run() {
        /* Tendrá 2 funcionalidades
        1. UPDATE: update information such as character position
        2. DRAW: draw the screen with the update information*/
        
        // Necesito controlar el tiempo para controlar la movilidad
        double drawInterval = 1000000000 / FPS; // 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: "+drawCount);
                drawCount = 0;
                timer = 0;
            }            
        }
    }
    
    public void update(){
        player.update();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; //Casting: Graphics2D extends Graphics
        
        // TILE
        tileM.draw(g2); // Es importante dibujar antes del jugador para no taparlo
        
        // OBJECT
        for(int i = 0; i<obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        // PLAYER
        player.draw(g2);
        
        // UI
        ui.draw(g2);
        
        g2.dispose(); // Buena practica para ahorrar memoria
    }
    
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    
    public void stopMusic(){
        music.stop();
    }
    
    public void soundEffect(int i){
        sound.setFile(i);
        sound.play();
    }
            
    
}
