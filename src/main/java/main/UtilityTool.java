package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/* Esta clase se va a encargar de tener funciones convenientes para el código
* 1) Se encarga de escalar las imágenes del Tile Manager
*/
public class UtilityTool {
    
    // Si escalamos las imagenes dentro del bucle perderemos mucha memoria y velocidad
    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original,0,0, width, height, null);
        g2.dispose();
        
        return scaledImage;
    }
    
}
