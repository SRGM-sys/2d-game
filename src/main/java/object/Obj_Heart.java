package object;

import entity.Entity;
import main.GamePanel;


public class Obj_Heart extends Entity{
    
    public Obj_Heart(GamePanel gp){
        super(gp);
        name = "heart";
        image = setup("objects","heart_full", super.px, super.px);
        image2 = setup("objects","heart_half", super.px, super.px);
        image3 = setup("objects","heart_blank", super.px, super.px);
    }
}
