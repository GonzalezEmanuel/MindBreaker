package com.mindbreaker.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mindbreaker.game.elementos.Moneda;
import com.mindbreaker.game.elementos.Nivel;
import com.mindbreaker.game.elementos.Obstaculo;
import com.mindbreaker.game.utiles.Config;
import com.mindbreaker.game.utiles.Recursos;
import com.mindbreaker.game.utiles.Render;

public class PantallaJuego implements Screen {

	private OrthographicCamera c;
	private SpriteBatch b;
	private Rectangle jugador;
	private Texture texturaJugador;
	private Texture texturaObstaculo;
	private Texture texturaMoneda;
	private ShapeRenderer sr;
    private Rectangle[] meta;
    private Nivel nivelActual;
    private int nivel;
    private Viewport v;

    
    
    
	@Override
	public void show() {
		c = new OrthographicCamera();
		
		
		v = new FitViewport(Config.ANCHO, Config.ALTO, c);
		c.position.set(c.viewportWidth / 2, c.viewportHeight / 2, 0);
		c.update();
		
		b = Render.batch;

		
		texturaJugador = new Texture(Recursos.SPRITEJUGADOR);
		texturaObstaculo = new Texture(Recursos.SPRITEOBSTACULO);
		texturaMoneda = new Texture(Recursos.SPRITEOBSTACULO);
		
		
		
		nivel = 1;
		nivelActual = new Nivel(nivel);
		
		meta = new Rectangle[10];
		
		meta[1] = new Rectangle(1025, 70, 175, 100);
		meta[2] = new Rectangle(1025, 70, 174, 100);
		meta[3] = new Rectangle(1025, 70, 175, 175);
		meta[4] = new Rectangle(1025, 270, 175, 175);
		meta[5] = new Rectangle(1,1,1,1);
		
		
		jugador = new Rectangle();
		jugador.width = 35;
		jugador.height = 35;
		jugador.x = 175;
		jugador.y = 550;
		
		sr = new ShapeRenderer();
		
	}
	
	@Override
	public void render(float delta) {
		
		Render.limpiarPantalla(0, 0, 0);
		
		v.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
	    
		
	    c.position.set(c.viewportWidth / 2, c.viewportHeight / 2, 0);
	    c.update();
		
		
		b.setProjectionMatrix(c.combined);
		sr.setProjectionMatrix(c.combined);
		
		
		
		sr.begin(ShapeRenderer.ShapeType.Filled);
		sr.setColor(Color.GREEN);
		
		switch (nivel) {
		case 1:
			sr.rect(100, 549, 174, 100);
			sr.rect(1025, 70, 174, 100);
		break;
		case 2:
			sr.rect(100, 70, 174, 100);
			sr.rect(1025, 70, 174, 100);
		break;
		case 3:
			sr.rect(100, 475, 175, 175);
			sr.rect(1025, 70, 175, 175);
		break;
		case 4:
			sr.rect(100, 215, 175, 290);
			sr.rect(1025, 215, 175, 290);
		break;
		case 5:
			sr.rect(100, 215, 175, 290);
			sr.rect(1025, 215, 175, 290);
		break;
		}
	    
	    sr.end();
		b.begin();
		
		b.setColor(Color.RED);
			
		b.draw(texturaJugador, jugador.x, jugador.y, jugador.width, jugador.height);
		
		
		b.end();
		
		b.begin();
		
		b.setColor(Color.YELLOW);
		
		for (Moneda m : nivelActual.getMonedas()) {
			b.draw(texturaMoneda, m.getRectangulo().x, m.getRectangulo().y, 35, 35);
		}
		
		b.end();
		
		
		b.begin();
		
		b.setColor(Color.BLUE);
		
		for (Obstaculo o : nivelActual.getObstaculos()) {
			
			b.draw(texturaObstaculo, o.getRectangulo().x, o.getRectangulo().y, 35, 35);
		}
		
		b.end();
		
		
		dibujarNivel();
		
	    
		entradasJugador();
	

		
		
		
	
		if (nivelSuperado()) {
			avanzarNivel();
			spawnearJugador(nivel);
			
		}
		
		moverObstaculos(delta);
	}

	private void moverObstaculos(float delta) {
		for (Obstaculo o : nivelActual.getObstaculos()) {
		    o.update(delta);
		    Rectangle rect = o.getRectangulo();

		    switch (nivel) {
		        case 1:
		            if (rect.x < 402) {
		                rect.x = 402;  
		                o.velocidadX = Math.abs(o.velocidadX);
		            } else if (rect.x + rect.width > 878) {  
		                rect.x = 878 - rect.width;  
		                o.velocidadX = -Math.abs(o.velocidadX);
		            }
		        break;

		        case 2:
		            if (rect.y < 274) {
		                rect.y = 274;
		                o.velocidadY = Math.abs(o.velocidadY);
		            } else if (rect.y + rect.height > 650) { 
		                rect.y = 650 - rect.height;
		                o.velocidadY = -Math.abs(o.velocidadY);
		            }
		        break;
		        
		        case 3:
		        	if (rect.y < 70) {
		        		rect.y = 70;
		        		o.velocidadY = Math.abs(o.velocidadY);
		        	} else if (rect.y + rect.height > 650) {
		        		rect.y = 650 - rect.height;
		        		o.velocidadY = -Math.abs(o.velocidadY);
		        	}
		        	
		        	if (rect.x < 100) {
		        		rect.x = 100;
		        		o.velocidadX = Math.abs(o.velocidadX);
		        	} else if (rect.x + rect.width > 1200) {
		        		rect.x = 1200 - rect.height;
		        		o.velocidadX = -Math.abs(o.velocidadX);
		        	}
		        	
		        	if (rect.x < 275 && rect.y > 475) {
		        		rect.x = 275;
		        		o.velocidadX = Math.abs(o.velocidadX);
		        	} else if (rect.x + rect.width > 1025 && rect.y < 275) {
		        		rect.x = 1025;
		        		o.velocidadX = -Math.abs(o.velocidadX);
		        	}
		        	
		        case 4:
		        	if (rect.y < 70) {
		        		rect.y = 70;
		        		o.velocidadY = Math.abs(o.velocidadY);
		        	} else if (rect.y + rect.height > 650) {
		        		rect.y = 650 - rect.height;
		        		o.velocidadY = -Math.abs(o.velocidadY);
		        	}
		        	
		        
		        break;
		        	
		        	
		    }
		}
		
	}
	
	
	private void entradasJugador() {
		float velocidad = 200 * Gdx.graphics.getDeltaTime();
	    float nuevaX = jugador.x;
	    float nuevaY = jugador.y;

	    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
	        nuevaY += velocidad;
	    }
	    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
	        nuevaY -= velocidad;
	    }
	    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
	        nuevaX -= velocidad;
	    }
	    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
	        nuevaX += velocidad;
	    }
	    
	    
	    
	   
	    if (!colisionaConPared(nuevaX, jugador.y)) {
	        jugador.x = nuevaX;
	    }
	    if (!colisionaConPared(jugador.x, nuevaY)) {
	        jugador.y = nuevaY;
	    }
	    
	   
	    
	    if (colisionaConObstaculo()) {
	    	spawnearJugador(nivel);
	    	
	    }

		verificarColisionMonedas();
		
	    
	       
	    jugador.x = MathUtils.clamp(jugador.x, 0, Config.ANCHO - jugador.width);
	    jugador.y = MathUtils.clamp(jugador.y, 0, Config.ALTO - jugador.height);
		
	}

	
	private void dibujarNivel() {
		 sr.setProjectionMatrix(c.combined);
		 sr.begin(ShapeRenderer.ShapeType.Line);
		 sr.setColor(Color.WHITE);
		 
		 switch(nivel) { 
		 	case 1:
		 	
		 		sr.line(100, 650, 275, 650); 
		 		sr.line(275, 650, 275, 200); 
		 		sr.line(275, 200, 400, 200); 
		 		sr.line(400, 200, 400, 550); 
		 		sr.line(525, 70, 525, 200);
		 		sr.line(400, 550, 780, 550); 
		 		sr.line(780, 650, 780, 550);
		 		sr.line(780, 650, 1200, 650);
		 		sr.line(100, 70, 525, 70); 
		 		sr.line(100, 70, 100, 650);
		 		sr.line(1200, 70, 1200, 650);
		 		sr.line(1025, 70, 1025, 520);
		 		sr.line(1025, 520, 880, 520);
		 		sr.line(880, 520, 880, 200);
		 		sr.line(880, 200, 525, 200);
		 		sr.line(1200, 70, 1025, 70);
		 		
		 		
		 	break;
		 	
		 	case 2:
		 		
		 		sr.line(100, 70, 275, 70); 
		 		sr.line(100, 70, 100, 650);
		 		sr.line(100, 650, 1200, 650);
		 		sr.line(275, 70, 275, 275);
		 		sr.line(1200, 650, 1200, 70);
		 		sr.line(1200, 70, 1025, 70);
		 		sr.line(1025, 70, 1025, 275);
		 		sr.line(1025, 275, 275, 275);
		 		
		 	break;
		 	
		 	case 3:
		 		
		 		sr.line(100, 70, 1200, 70);
		 		sr.line(100, 70, 100, 650);
		 		sr.line(100, 650, 1200, 650);
		 		sr.line(1200, 650, 1200, 70);
		 		
		 		
		 		
		 	break;
		 	
		 	case 4:
		 		sr.line(100, 215, 275, 215);
		 		sr.line(100, 215, 100, 505);
		 		sr.line(100, 505, 275, 505);
		 		sr.line(275, 505, 275, 650);
		 		sr.line(275, 70, 275, 215);
		 		sr.line(275, 70, 1025, 70);
		 		sr.line(275, 650, 1025, 650);
		 		sr.line(1025, 70, 1025, 215);
		 		sr.line(1025, 650, 1025, 505);
		 		sr.line(1025, 505, 1200, 505);
		 		sr.line(1025, 215, 1200, 215);
		 		sr.line(1200, 215, 1200, 505);
		 	break;
		 	
		 	case 5:
		 		sr.line(512.5f, 272.5f, 687.5f, 272.5f); //87.5
		 		
		 		
		 	break;
		 	
		 	default:
		 		sr.line(100, 70, 1200, 70);
		 		sr.line(100, 70, 100, 650);
		 		sr.line(100, 650, 1200, 650);
		 		sr.line(1200, 650, 1200, 70);
		 	break;
		 }
		 sr.end();
	}
	
    private boolean nivelSuperado() {

    	if (jugador.overlaps(meta[nivel]) && nivelActual.getMonedas().size == 0) {
    		return jugador.overlaps(meta[nivel]);
	    }
    	return false;
    }

    private void avanzarNivel() {
        if (nivel < 10) { 
            nivel++;
            nivelActual = new Nivel(nivel);
            
        } else {
           System.out.println("juego pasado");
        }
    }
  
	
	private boolean colisionaConPared(float x, float y) {
	    Rectangle futuroJugador = new Rectangle(x, y, jugador.width, jugador.height);
	    for (Rectangle pared : nivelActual.getParedes()) {
	        if (futuroJugador.overlaps(pared)) {
	            return true; 
	        }
	    }
	    return false;
	}


	public void spawnearJugador(int nivel) {
	    switch (nivel) {
	        case 1:
	            jugador.x = 175;
	            jugador.y = 550;
	            break;
	        case 2:
	            jugador.x = 175;
	            jugador.y = 200;
	            break;
	        case 3:
	        	jugador.x = 175;
	        	jugador.y = 550;
	        	break;
	        case 4:
	        	jugador.x = 170; 
	        	jugador.y = 345;
	        	break;
	        default:
	            jugador.x = 175;
	            jugador.y = 550;
	            break;
	    }
	}
	
	private boolean colisionaConObstaculo() {
		for (Obstaculo o : nivelActual.getObstaculos()) {
			if (jugador.overlaps(o.getRectangulo())) {
				return true; // para debug = false
			}
			
		}
		return false;
	}
	
	private void verificarColisionMonedas() {
	    for (int i = nivelActual.getMonedas().size - 1; i >= 0; i--) {
	        Moneda moneda = nivelActual.getMonedas().get(i);
	        if (jugador.overlaps(moneda.getRectangulo())) {
	            nivelActual.getMonedas().removeIndex(i);
	        }
	      
	    }
	}

	@Override
	public void resize(int width, int height) {
		Config.ANCHO = width;
		Config.ALTO = height;
		v.update(Config.ANCHO, Config.ALTO, true);
		c.position.set(c.viewportWidth / 2, c.viewportHeight / 2, 0);
		c.update();
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	


}
