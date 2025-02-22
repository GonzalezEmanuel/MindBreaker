package com.mindbreaker.game.utiles;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;

public class Colisiones {
	private ArrayList<Rectangle> limites;

    public Colisiones() {
        limites = new ArrayList<>();
        generarColisiones();
    }

    private void generarColisiones() {
        limites.add(new Rectangle(100, 650, 175, 1)); 
        limites.add(new Rectangle(275, 200, 1, 450)); 
        limites.add(new Rectangle(275, 200, 125, 1));  
        limites.add(new Rectangle(400, 200, 1, 350));  
        limites.add(new Rectangle(525, 70, 1, 130));   
        limites.add(new Rectangle(400, 550, 380, 1)); 
        limites.add(new Rectangle(780, 550, 1, 100)); 
        limites.add(new Rectangle(780, 650, 420, 1)); 
        limites.add(new Rectangle(100, 70, 425, 1)); 
        limites.add(new Rectangle(100, 70, 1, 580));   
        limites.add(new Rectangle(1200, 70, 1, 580));  
        limites.add(new Rectangle(1025, 70, 1, 450));  
        limites.add(new Rectangle(880, 520, 145, 1)); 
        limites.add(new Rectangle(880, 200, 1, 320)); 
        limites.add(new Rectangle(525, 200, 355, 1));  
        limites.add(new Rectangle(1025, 70, 175, 1));  
    }

    public boolean verificarColision(Rectangle jugador) {
        for (Rectangle colisionador : limites) {
            if (jugador.overlaps(colisionador)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Rectangle> getlimites() {
        return limites;
    }
}
