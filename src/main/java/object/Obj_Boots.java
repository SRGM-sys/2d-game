package object;

import entity.Entity;
import main.GamePanel;

public class Obj_Boots extends Entity{
    
    public Obj_Boots(GamePanel gp){
        super(gp);
        name = "boots";
        down1 = setup("objects","boots", super.px, super.px);
    }
    
    
}
