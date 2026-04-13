package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Obj_Door extends SuperObject{
    
    public Obj_Door(){
        name = "door";
        collision = true;
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
