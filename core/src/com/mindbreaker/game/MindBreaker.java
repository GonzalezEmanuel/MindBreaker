package com.mindbreaker.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mindbreaker.game.pantallas.PantallaCarga;
import com.mindbreaker.game.utiles.ManagerMusica;
import com.mindbreaker.game.utiles.Recursos;
import com.mindbreaker.game.utiles.Render;


public class MindBreaker extends Game {

	
	
	@Override
	public void create () {
		Render.app = this;
		Render.batch = new SpriteBatch();
		this.setScreen(new PantallaCarga());
		ManagerMusica.playMusic(Recursos.MUSICAJUEGO);
		
	}
	
	
	@Override
	public void render () {		
		super.render();
	}	
	
	@Override
	public void dispose () {
		Render.batch.dispose();
		
	}
	
	
}
