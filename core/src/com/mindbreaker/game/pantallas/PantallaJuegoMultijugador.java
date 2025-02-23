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
import com.mindbreaker.game.elementos.Texto;
import com.mindbreaker.game.io.KeyListener;
import com.mindbreaker.game.net.HiloCliente;
import com.mindbreaker.game.net.HiloServidor;
import com.mindbreaker.game.utiles.Config;
import com.mindbreaker.game.utiles.Globales;
import com.mindbreaker.game.utiles.Recursos;
import com.mindbreaker.game.utiles.Render;

public class PantallaJuegoMultijugador implements Screen {

	private OrthographicCamera c;
	private SpriteBatch b;
	private Rectangle j1;
	private Rectangle j2;
	private Texture texturaJugador;
	private Texture texturaObstaculo;
	private Texture texturaMoneda;
	private ShapeRenderer sr;
    private Rectangle[] meta;
    private Nivel nivelActual;
    private int nivel;
    private Viewport v;
    private Texto espera;
    private HiloCliente hc;
    private HiloServidor hs;
    private KeyListener teclas;
    public boolean isUp1 = false, isDown1 = false, isUp2 = false, isDown2 = false;
    
	@Override
	public void show() {
		c = new OrthographicCamera();
		
		
		v = new FitViewport(Config.ANCHO, Config.ALTO, c);
		c.position.set(c.viewportWidth / 2, c.viewportHeight / 2, 0);
		c.update();
		
		teclas = new KeyListener(hc);
		
		b = Render.batch;
		

		
		texturaJugador = new Texture(Recursos.SPRITEJUGADOR);
		texturaObstaculo = new Texture(Recursos.SPRITEOBSTACULO);
		texturaMoneda = new Texture(Recursos.SPRITEOBSTACULO);
		
		espera = new Texto(Recursos.FUENTEMENU, 50, Color.WHITE, false);
		espera.setTexto("Esperando jugador 2....");
		espera.setPosition((Config.ANCHO / 2) - (espera.getAncho() / 2), (Config.ALTO / 2) - (espera.getAlto() / 2));
		
		nivel = 1;
		nivelActual = new Nivel(nivel);
		
		meta = new Rectangle[10];
		
		meta[1] = new Rectangle(1025, 70, 175, 100);
		meta[2] = new Rectangle(1025, 70, 174, 100);
		meta[3] = new Rectangle(1025, 70, 175, 175);
		meta[4] = new Rectangle(1025, 270, 175, 175);
		meta[5] = new Rectangle(1,1,1,1);
		
		
		j1 = new Rectangle();
		j1.width = 35;
		j1.height = 35;
		j1.x = 175;
		j1.y = 550;
		
		j2 = new Rectangle();
		j2.width = 35;
		j2.height = 35;
		j2.x = 175;
		j2.y = 550;
		
		sr = new ShapeRenderer();
		if (Globales.esServer) {
			hs = new HiloServidor(this);
			hs.start();
		} else {
			hc = new HiloCliente();
			hc.start();
		}
		
	}
	
	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0);
		
		
		if (!Globales.empieza) {
			b.begin();
			espera.dibujar();
			b.end();
		} else {

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

			b.draw(texturaJugador, j1.x, j1.y, j1.width, j1.height);

			b.end();

			b.setColor(Color.ORANGE);

			b.draw(texturaJugador, j2.x, j2.y, j2.width, j2.height);

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
			entradasJugador(j1);
			entradasJugador(j2);

			if (nivelSuperado()) {
				avanzarNivel();
				spawnearJugador(nivel, j1);
				spawnearJugador(nivel, j2);

			}
			moverObstaculos(delta);

		}

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


private void entradasJugador(Rectangle j) {	
	float velocidad = 200 * Gdx.graphics.getDeltaTime();
    float nuevaX = j.x;
    float nuevaY = j.y;
    
    if (Globales.esServer) {
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
    } else {
    	if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
    		hc.enviarMensaje("Arriba");
    		isUp1 = true;
        } else {
        	hc.enviarMensaje("No aprete arriba");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
    		hc.enviarMensaje("Abajo");
    		isDown1 = true;
        } else {
        	hc.enviarMensaje("No aprete abajo");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            
        }
    }
    
    
    
    
   
    if (!colisionaConPared(nuevaX, j1.y)) {
        j1.x = nuevaX;
    }
    if (!colisionaConPared(j1.x, nuevaY)) {
        j1.y = nuevaY;
    }
    
    if (!colisionaConPared(nuevaX, j2.y)) {
        j2.x = nuevaX;
    }
    if (!colisionaConPared(j2.x, nuevaY)) {
        j2.y = nuevaY;
    }
    
    
    
    
    if (colisionaConObstaculo()) {
    	spawnearJugador(nivel,j1);
    	
    }

	verificarColisionMonedas();
	
    
       
    j1.x = MathUtils.clamp(j1.x, 0, Config.ANCHO - j1.width);
    j1.y = MathUtils.clamp(j1.y, 0, Config.ALTO - j1.height);
    j2.x = MathUtils.clamp(j2.x, 0, Config.ANCHO - j2.width);
    j2.y = MathUtils.clamp(j2.y, 0, Config.ALTO - j2.height);
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

    	if (j1.overlaps(meta[nivel]) && nivelActual.getMonedas().size == 0) {
    		return j1.overlaps(meta[nivel]);
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
	    Rectangle futuroJugador = new Rectangle(x, y, j1.width, j1.height);
	    for (Rectangle pared : nivelActual.getParedes()) {
	        if (futuroJugador.overlaps(pared)) {
	            return true; 
	        }
	    }
	    return false;
	}


	public void spawnearJugador(int nivel, Rectangle j) {
	    switch (nivel) {
	        case 1:
	            j.x = 175;
	            j.y = 550;
	            break;
	        case 2:
	            j.x = 175;
	            j.y = 200;
	            break;
	        case 3:
	        	j.x = 175;
	        	j.y = 550;
	        	break;
	        case 4:
	        	j.x = 170; 
	        	j.y = 345;
	        	break;
	        default:
	            j.x = 175;
	            j.y = 550;
	            break;
	    }
	}
	
	private boolean colisionaConObstaculo() {
		for (Obstaculo o : nivelActual.getObstaculos()) {
			if (j1.overlaps(o.getRectangulo())) {
				return true; // para debug = false
			}
			
		}
		return false;
	}
	
	private void verificarColisionMonedas() {
	    for (int i = nivelActual.getMonedas().size - 1; i >= 0; i--) {
	        Moneda moneda = nivelActual.getMonedas().get(i);
	        if (j1.overlaps(moneda.getRectangulo())) {
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