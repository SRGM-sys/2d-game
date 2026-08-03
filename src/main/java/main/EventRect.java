package main;

// Vamos a hacer los eventos más dinámicos

import java.awt.Rectangle;


public class EventRect extends Rectangle{
    
    public int eventRectDefaultX, eventRectDefaultY;
    // Este parametro es clave en caso de que queramos que una trampa se active una única vez
    public boolean eventDone = false;  
    
}
