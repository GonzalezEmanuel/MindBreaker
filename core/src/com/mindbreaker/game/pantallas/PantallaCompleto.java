package com.mindbreaker.game.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mindbreaker.game.elementos.Texto;
import com.mindbreaker.game.utiles.Config;
import com.mindbreaker.game.utiles.Recursos;
import com.mindbreaker.game.utiles.Render;

public class PantallaCompleto implements Screen {

	private	OrthographicCamera c;
	private SpriteBatch b;
	private Texto msg;
	
	
	
	
	@Override
	public void show() {
		b = Render.batch;
		c = new OrthographicCamera();
		c.setToOrtho(false,1280,720);
		msg = new Texto(Recursos.FUENTEMENU, 40, Color.WHITE, false);
		msg.setTexto("Pasaste el nivel!");
		msg.setPosition((Config.ANCHO/2)-(msg.getAncho()/2), (Config.ALTO/2)+(msg.getAlto()/2));
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla(0, 0, 0);
		
		b.begin();

		msg.dibujar();
		
		b.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
