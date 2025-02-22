package com.mindbreaker.game.elementos;

import com.badlogic.gdx.math.Rectangle;

public class Obstaculo {
	private Rectangle rectangulo;
	public float velocidadX;
	public float velocidadY;
	
	
    public Obstaculo(float x, float y, float ancho, float alto, float velocidadX, float velocidadY) {
        rectangulo = new Rectangle(x,y,ancho,alto);
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
    }
   
    public void update(float delta) {
       
        rectangulo.x += velocidadX * delta;
        rectangulo.y += velocidadY * delta;
        
      
	  
    }
    
    public Rectangle getRectangulo() {
    	return rectangulo;
    }
   
  
}
