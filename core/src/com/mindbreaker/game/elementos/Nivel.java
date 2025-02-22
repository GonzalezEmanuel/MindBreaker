package com.mindbreaker.game.elementos;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Nivel {
	private Array<Obstaculo> obstaculos;
	private Array<Rectangle> paredes;
	public int numeroNivel;
	private Array<Moneda> monedas;
	
	public Nivel(int numeroNivel) {
		this.numeroNivel = numeroNivel;
		this.obstaculos = new Array<>();
		this.paredes = new Array<>();
		this.monedas = new Array<>();
		crearObstaculos();
		crearParedes();
		crearMonedas();
		
	}
	
	private void crearObstaculos() {
	    obstaculos.clear();

        switch (numeroNivel) {
            case 1:
                obstaculos.add(new Obstaculo(640, 306, 35, 35, 700, 0));
                obstaculos.add(new Obstaculo(640, 412, 35, 35, -700, 0));
                break;
            case 2:
                obstaculos.add(new Obstaculo(368.75f, 649, 35, 35, 0, -700));
                obstaculos.add(new Obstaculo(556.25f, 276, 35, 35, 0, 700));
                obstaculos.add(new Obstaculo(743.75f, 649, 35, 35, 0, -700 ));
                obstaculos.add(new Obstaculo(931.25f, 276, 35, 35, 0, 700 ));
                break;
            case 3:
            	obstaculos.add(new Obstaculo(368.75f, 649, 35, 35, 0, -700));
                obstaculos.add(new Obstaculo(556.25f, 71, 35, 35, 0, 700));
                obstaculos.add(new Obstaculo(743.75f, 649, 35, 35, 0, -700 ));
                obstaculos.add(new Obstaculo(931.25f, 71, 35, 35, 0, 700 ));
                obstaculos.add(new Obstaculo(100, 312.5f, 35, 35, 700, 0));
                obstaculos.add(new Obstaculo(1200, 387.5f, 35, 35, -700, 0));
                
            break;
            case 4:
            	obstaculos.add(new Obstaculo(278, 650, 35, 35, 0, -800));
            	obstaculos.add(new Obstaculo(381.25f, 70, 35, 35, 0, 800));
            	obstaculos.add(new Obstaculo(487.5f, 650, 35, 35, 0, -800));
            	obstaculos.add(new Obstaculo(593.75f, 70, 35, 35, 0, 800));
            	obstaculos.add(new Obstaculo(700, 650, 35, 35, 0, -800));
            	obstaculos.add(new Obstaculo(806.25f, 70, 35, 35, 0, 800));
            	obstaculos.add(new Obstaculo(895.625f, 650, 35, 35, 0, -800)); // 89.375
            	obstaculos.add(new Obstaculo(985, 70, 35, 35, 0, 800));
            break;
            default:
              
                obstaculos.add(new Obstaculo(100, 200, 35, 35, 150, -150));
                obstaculos.add(new Obstaculo(600, 400, 35, 35, -150, 150));
                break;
        }
	}
	public void crearMonedas() {
		monedas.clear();
		
		switch(numeroNivel) {
		case 1:
		break;
		
		case 2:
		break;
		
		case 3:
		monedas.add(new Moneda(110, 80, 35, 35));
		monedas.add(new Moneda(1155, 605, 35, 35));
		break;
		
		case 4:
		monedas.add(new Moneda(277, 72, 35, 35));
		monedas.add(new Moneda(988, 72, 35, 35));
		monedas.add(new Moneda(277, 613, 35, 35));
		monedas.add(new Moneda(988, 613, 35, 35));
		break;
		
		}
		
		
	}
	
	public void crearParedes() {
		
		paredes.clear();
		
		switch(numeroNivel) {
		case 1:
			paredes.add(new Rectangle(100, 645, 175, 5)); 
			paredes.add(new Rectangle(270, 200, 5, 450)); 
			paredes.add(new Rectangle(275, 195, 125, 5)); 
			paredes.add(new Rectangle(395, 200, 5, 350)); 
			paredes.add(new Rectangle(520, 70, 5, 130)); 
			paredes.add(new Rectangle(400, 545, 380, 5)); 
			paredes.add(new Rectangle(775, 550, 5, 100)); 
			paredes.add(new Rectangle(780, 645, 420, 5)); 
			paredes.add(new Rectangle(100, 65, 425, 5)); 
			paredes.add(new Rectangle(95, 70, 5, 580)); 
			paredes.add(new Rectangle(1195, 70, 5, 580)); 
			paredes.add(new Rectangle(1020, 70, 5, 450)); 
			paredes.add(new Rectangle(875, 520, 150, 5)); 
			paredes.add(new Rectangle(875, 200, 5, 320)); 
			paredes.add(new Rectangle(525, 195, 355, 5)); 
			paredes.add(new Rectangle(1020, 70, 180, 5)); 
		break;
		case 2:
			paredes.add(new Rectangle(100, 65, 175, 5)); 
			paredes.add(new Rectangle(95, 70, 5, 580)); 
			paredes.add(new Rectangle(100, 645, 1100, 5)); 
			paredes.add(new Rectangle(270, 70, 5, 205));
			paredes.add(new Rectangle(1195, 70, 5, 580)); 
			paredes.add(new Rectangle(1025, 65, 175, 5));
			paredes.add(new Rectangle(1020, 70, 5, 205)); 
			paredes.add(new Rectangle(275, 270, 750, 5)); 
		break;
		case 3:
			paredes.add(new Rectangle(95, 65, 5, 650));
	 		paredes.add(new Rectangle(95, 65, 1200, 5));
	 		paredes.add(new Rectangle(1200, 65, 5, 650));
	 		paredes.add(new Rectangle(95, 645, 1200, 5));	
		break;
		case 4:
			paredes.add(new Rectangle(95, 210, 5, 290));
			paredes.add(new Rectangle(95, 500, 175, 5));
			paredes.add(new Rectangle(95, 210, 175, 5));
			paredes.add(new Rectangle(270, 500, 5, 145));
			paredes.add(new Rectangle(270, 70, 5, 145));
			paredes.add(new Rectangle(270, 70, 5, 145));
			paredes.add(new Rectangle(270, 65, 750, 5));
			paredes.add(new Rectangle(270, 645, 750, 5));
			paredes.add(new Rectangle(1020, 500, 5, 145));
			paredes.add(new Rectangle(1020, 65, 5, 145));
			paredes.add(new Rectangle(1200, 210, 5, 290));
			paredes.add(new Rectangle(1020, 210, 175, 5));
			paredes.add(new Rectangle(1020, 505, 175, 5));
	 	break;
		case 5:
			paredes.add(new Rectangle(512.5f, 272.5f, 175, 175));
			
			break;
		}
	}
	
	
	public Array<Obstaculo> getObstaculos() {
	    return obstaculos;
	}
	
	public Array<Rectangle> getParedes() {
		return paredes;
	}
	
	public Array<Moneda> getMonedas() {
		return monedas;
	}

	public int getNumeroNivel() {
		return numeroNivel;
	}
	
}
