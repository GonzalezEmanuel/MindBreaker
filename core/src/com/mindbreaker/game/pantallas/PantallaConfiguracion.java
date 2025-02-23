package com.mindbreaker.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mindbreaker.game.elementos.Imagen;
import com.mindbreaker.game.elementos.Texto;
import com.mindbreaker.game.utiles.Config;
import com.mindbreaker.game.utiles.ManagerMusica;
import com.mindbreaker.game.utiles.Recursos;
import com.mindbreaker.game.utiles.Render;

public class PantallaConfiguracion implements Screen {


	Imagen fondo;
	SpriteBatch b;
	Texto opciones[] = new Texto[4];
	String textos[] = new String[4];
	int opc = 1;
	ShapeRenderer sr;
	float tiempo = 0;
	OrthographicCamera c;
	Viewport v;
	String[] resoluciones = {
		    "640x480", "800x600", "1024x768", "1176x664",
		    Config.ALTO + " x " + Config.ANCHO, "1360x768", "1366x768", "1600x900", "1920x1080"
		};
	private float tiempoCambioResolucion = 0;
	int indiceResolucion = 0;
	
	
	@Override
	public void show() {
		fondo = new Imagen(Recursos.FONDO);
		fondo.setSize(Config.ANCHO, Config.ALTO);
		b = Render.batch;
		sr = new ShapeRenderer();
		
		c = new OrthographicCamera();
	    v = new FitViewport(Config.ANCHO, Config.ALTO, c);
		
		
		textos[0] = "Resolución: " + Config.ANCHO + " x " + Config.ALTO;
		textos[1] = "Pantalla Completa: Desactivada";
		textos[2] = "Volumen: " + (int) (Config.getVolumen() * 100) + "%";
		textos[3] = "Volver al menú";
		
		 
		
		
		
		int avance = 60;

		for (int i = 0; i < opciones.length; i++) {

			opciones[i] = new Texto(Recursos.FUENTEMENU, 40, Color.WHITE, false);
			opciones[i].setTexto(textos[i]);
			opciones[i].setPosition((Config.ANCHO / 2) - (opciones[i].getAncho() / 2), (Config.ALTO - (Config.ALTO / 4))
					+ (opciones[0].getAlto() / 2) - (opciones[i].getAlto() + (avance * i)));
		}

	}

	@Override
	public void render(float delta) {

		tiempoCambioResolucion += delta; 
		
	    
	    v.update(Config.ANCHO, Config.ALTO, true);
	    
		
	    c.position.set(c.viewportWidth / 2, c.viewportHeight / 2, 0);
	    c.update();
	    
		b.begin();
		fondo.dibujar();
		for (int i = 0; i < opciones.length; i++) {

			opciones[i].dibujar();
		}
		b.end();

		b.setProjectionMatrix(c.combined);
//	    sr.setProjectionMatrix(c.combined);
//		
//		sr.begin(ShapeType.Line);
//
//		sr.setColor(Color.WHITE);
//		for (int i = 0; i < opciones.length; i++) {
//			sr.rect(opciones[i].getX(), opciones[i].getY() - opciones[i].getAlto(), opciones[i].getAncho(),
//					opciones[i].getAlto());
//
//		}
//
//		sr.end();

		tiempo += delta;

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			if (tiempo > 0.1f) {
				tiempo = 0;
				opc++;
				if (opc > 5) {
					opc = 1;
				}
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			if (tiempo > 0.1f) {
				tiempo = 0;
				opc--;
				if (opc < 1) {
					opc = 4;
				}
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if (tiempo > 0.1f) {
				tiempo = 0;

			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			if (tiempo > 0.1f) {
				tiempo = 0;
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Render.app.setScreen(new PantallaMenu());
		}

		for (int i = 0; i < opciones.length; i++) {

			if (i == (opc - 1)) {
				opciones[i].setColor(Color.YELLOW);
			} else {
				opciones[i].setColor(Color.WHITE);
			}

			if (opc == 1) {
				
				if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
		            cambiarOpcionResolucion(1);
		            tiempoCambioResolucion = 0;
		        }
		        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
		            cambiarOpcionResolucion(-1);
		            tiempoCambioResolucion = 0;
		        }
		        
				opciones[0].setTexto("Resolucion: " + Config.ANCHO + " x " + Config.ALTO);

			} else if (opc == 3) {
				if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
					Config.setVolumen(Config.getVolumen() + 0.01f);
				}

				if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
					Config.setVolumen(Config.getVolumen() - 0.01f);
				}

				ManagerMusica.getCurrentMusic().setVolume(Config.getVolumen());
				opciones[2].setTexto("Volumen: " + (int) (Config.getVolumen() * 100) + "%");

			} 
		}

		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			if (opc == 4) {
				Render.app.setScreen(new PantallaMenu());
			}

		}
		
		
		
	
	}
	
	private void cambiarOpcionResolucion(int direccion) {
	    indiceResolucion += direccion;
	    
	   
	    if (indiceResolucion < 0) {
	        indiceResolucion = resoluciones.length - 1;
	    } else if (indiceResolucion >= resoluciones.length) {
	        indiceResolucion = 0;
	    }

	    actualizarResolucion();
	   
	}
	
	
	public void cambiarResolucion(int ancho, int alto) {
		if (Config.ANCHO == ancho && Config.ALTO == alto) {
	        return; 
	    }

	    Config.ANCHO = ancho;
	    Config.ALTO = alto;

	    Gdx.app.postRunnable(() -> { 
	        Gdx.graphics.setWindowedMode(Config.ANCHO, Config.ALTO);
	    });

	    v.update(Config.ANCHO, Config.ALTO, true);
	    c.position.set(c.viewportWidth / 2, c.viewportHeight / 2, 0);
	    c.update();
	}
	

	private void actualizarResolucion() {
	  
		String nuevaResolucion = resoluciones[indiceResolucion];

		    if (!opciones[0].getTexto().equals("Resolucion: " + nuevaResolucion)) {
		        opciones[0].setTexto("Resolucion: " + nuevaResolucion);
		    }

		 String[] partes = nuevaResolucion.split("x");
		 int ancho = Integer.parseInt(partes[0].trim());
		 int alto = Integer.parseInt(partes[1].trim());

		 cambiarResolucion(ancho, alto);
	}




	@Override
	public void resize(int width, int height) {
		 v.update(width, height, true);
		 c.position.set(width / 2, height / 2, 0);
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


