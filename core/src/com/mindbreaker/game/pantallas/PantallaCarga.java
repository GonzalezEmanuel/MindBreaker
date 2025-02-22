package com.mindbreaker.game.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mindbreaker.game.elementos.Imagen;
import com.mindbreaker.game.utiles.Config;
import com.mindbreaker.game.utiles.ManagerMusica;
import com.mindbreaker.game.utiles.Recursos;
import com.mindbreaker.game.utiles.Render;

public class PantallaCarga implements Screen {

	Imagen fondo;
	SpriteBatch b;
	boolean fadeInTerminado = false, termina = false;
	float a = 0, contTiempo=0, tiempoEspera=5, contTiempoTermina = 0, tiempoTermina = 5;
	
	@Override
	public void show() {
		fondo = new Imagen(Recursos.FONDOINICIO);
		b = Render.batch;
		fondo.setTransparencia(a);
	}

	@Override
	public void render(float delta) {
		
		Render.limpiarPantalla(0,0,0);
		
		procesarFade();
		
		b.begin();
			fondo.dibujar();
		b.end();
		
		
	}

	private void procesarFade() {
		if (!fadeInTerminado) {
			a+=0.01f;
			if (a>1) {
				a=1;
				fadeInTerminado = true;
			}
		} else { 
			contTiempo+=0.1f;
			if (contTiempo > tiempoEspera) {
				a-=0.01f;
				if (a < 0) {
					a=0;
					termina = true;
				}
				
			}
		}
		fondo.setTransparencia(a);
		
		if (termina) {
			contTiempoTermina+=0.1f;
			if (contTiempoTermina > tiempoTermina) {
				
				Render.app.setScreen(new PantallaMenu());
			}
			
		}   
		
	}

	@Override
	public void resize(int width, int height) {
		width = Config.ANCHO;
		height = Config.ALTO;
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void dispose() {
		b.dispose();
		
	}

}
