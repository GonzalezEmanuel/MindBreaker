package com.mindbreaker.game.utiles;

import com.badlogic.gdx.Game;
import com.mindbreaker.game.pantallas.PantallaJuego;

public class GdxGame extends Game{
	private static GdxGame instance;

	public static GdxGame getInstance() {
		return instance;
	}

	@Override
	public void create() {
		instance = this;
		setScreen(new PantallaJuego());
	}
}
