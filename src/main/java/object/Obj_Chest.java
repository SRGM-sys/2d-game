/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Usuario
 */
public class Obj_Chest extends SuperObject{
    
    public Obj_Chest(){
        name = "chest";
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
