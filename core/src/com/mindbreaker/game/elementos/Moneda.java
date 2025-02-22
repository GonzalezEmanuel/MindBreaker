package com.mindbreaker.game.elementos;

import com.badlogic.gdx.math.Rectangle;

public class Moneda {
	private Rectangle rectangulo;
	
	public Moneda(float x, float y, float ancho, float alto) {
		rectangulo = new Rectangle(x,y,ancho,alto);
		
	}
	
	public Rectangle getRectangulo() {
		return rectangulo;
	}
	
}
