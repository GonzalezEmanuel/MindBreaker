package com.mindbreaker.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mindbreaker.game.elementos.Imagen;
import com.mindbreaker.game.elementos.Texto;
import com.mindbreaker.game.utiles.Config;
import com.mindbreaker.game.utiles.Recursos;
import com.mindbreaker.game.utiles.Render;

public class PantallaMenu implements Screen {

	Imagen fondo;
	SpriteBatch b;
	Texto opciones[] = new Texto[4];
	String textos[] = {"Nueva Partida", "Online", "Configuracion", "Salir"};
	int opc=1;
	public float tiempo = 0;
	ShapeRenderer sr;
	OrthographicCamera c;
	Viewport v;
	


	
	@Override
	public void show() {
		fondo = new Imagen(Recursos.FONDOMENU);
		fondo.setSize(Config.ANCHO, Config.ALTO);
		b = Render.batch;
		sr = new ShapeRenderer();
		
		c = new OrthographicCamera();
	    v = new FitViewport(Config.ANCHO, Config.ALTO, c);
		
		int avance = 60;
		
		for (int i = 0; i < opciones.length; i++) {
			
			opciones[i] = new Texto(Recursos.FUENTEMENU, 40, Color.WHITE, false);
			opciones[i].setTexto(textos[i]);
			opciones[i].setPosition((Config.ANCHO/2)-(opciones[i].getAncho()/2), (Config.ALTO/2)+(opciones[0].getAlto()/2)-(opciones[i].getAlto()+(avance*i)));
		}

	}

	@Override
	public void render(float delta) {
		
		
		Render.limpiarPantalla(0, 0, 0);
		
		
		
		v.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
	    
	
	    c.position.set(c.viewportWidth / 2, c.viewportHeight / 2, 0);
	    c.update();
		
		
		b.begin();
			fondo.dibujar();
			for (int i = 0; i < opciones.length; i++) {
				
				opciones[i].dibujar();
			}
		b.end();
		
		sr.begin(ShapeType.Line);
		
		sr.setColor(Color.WHITE);
		for (int i = 0; i < opciones.length; i++) {
			sr.rect(opciones[i].getX(), opciones[i].getY()-opciones[i].getAlto(), opciones[i].getAncho(), opciones[i].getAlto());
			
		}
		
		sr.end();
		
		tiempo += delta;
		
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			if (tiempo > 0.1f) {
				tiempo = 0;
				opc++;
				if (opc>4) {
					opc = 1;
				}
			}
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			if (tiempo > 0.1f) {
				tiempo = 0;
				opc--;
				if (opc<1) {
					opc = 4;
				}
			}
		}
		
		
		for (int i = 0; i < opciones.length; i++) {
			
			if (i == (opc-1)) {
				opciones[i].setColor(Color.YELLOW);
				
			} else {
				opciones[i].setColor(Color.WHITE);
			}
			
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			if (opc == 1) {
				Render.app.setScreen(new PantallaJuego());
				
			}  else if (opc == 3) {
				Render.app.setScreen(new PantallaConfiguracion());
			}  else if (opc == 4) {
				Gdx.app.exit();
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
		
		for (int i = 0; i < opciones.length; i++) {
			opciones[i].setPosition((width / 2) - (opciones[i].getAncho() / 2),
					(height / 2) + (opciones[0].getAlto() / 2) - (opciones[i].getAlto() + (60 * i)));
		}
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
