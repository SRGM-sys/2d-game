package entity;

import java.util.Random;
import main.GamePanel;

public class NPC_OldMan extends Entity{
    
    public NPC_OldMan(GamePanel gp) {
        super(gp);
        direction = "down"; //Empezará mirando hacia abajo
        speed = 1;
        
        getOldManImage();
        setDialogue();
    }
    
    public void getOldManImage(){
        
        up1 = setup("npc", "oldman_up_1");
        up2 = setup("npc", "oldman_up_2");
        down1 = setup("npc", "oldman_down_1");
        down2 = setup("npc", "oldman_down_2");
        left1 = setup("npc", "oldman_left_1");
        left2 = setup("npc", "oldman_left_2");
        right1 = setup("npc", "oldman_right_1");
        right2 = setup("npc", "oldman_right_2");
    }
    
    public void setDialogue(){
        dialogues[0] = "Hola, srgm";
        dialogues[1] = "Asi que llegaste a esta isla para \nencontrar el tesoro";
        dialogues[2] = "Yo solia ser un gran mago, pero \nahora... Estoy un poco viejo para \nir de aventuras";
        dialogues[3] = "Bueno, que la fortuna te acompañe";
    }
    
    // En este método trabajaremos la IA del NPC
    @Override
    public void setAction(){
        
        actionLockCounter++;
        
        if(actionLockCounter == 120){
            Random rd = new Random();
            int i = rd.nextInt(100)+1; 

            if(i<= 25){
                direction = "up";
            }
            if(i>25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75){
                direction = "right";
            }
            
            actionLockCounter = 0;
        }
    }
    
    @Override
    public void speak(){
        super.speak();
    }
    
    
}
