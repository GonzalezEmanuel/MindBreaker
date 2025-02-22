package com.mindbreaker.game.io;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.mindbreaker.game.pantallas.PantallaMenu;
import com.mindbreaker.game.utiles.Config;

public class Entradas implements InputProcessor {

	private boolean abajo = false, arriba = false;
	private boolean enter = false;
	PantallaMenu app;
	private int mouseX=0, mouseY=0;
	
	public boolean isEnter() {
		return enter;
	}
	
	public Entradas (PantallaMenu app) {
		this.app = app;
		
		
	}
	
	
	public boolean isAbajo() {
		return abajo;
	}

	public boolean isArriba() {
		return arriba;
	}

	@Override 
	public boolean keyDown(int keycode) {
		
		app.tiempo = 0.08f;
		
		if (keycode == Keys.DOWN) {
			abajo = true;
			
		}
		
		if (keycode == Keys.UP) {
			arriba = true;
			
		}
		
		if (keycode == Keys.ENTER) {
			enter = true;
		}
		
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		if (keycode == Keys.DOWN) {
			abajo = false;
			
		}
		
		if (keycode == Keys.UP) {
			arriba = false;
			
		}
		
		if (keycode == Keys.ENTER) {
			enter = false;
			
		}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouseX = screenX;
		mouseY = Config.ALTO - screenY;
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
	
}
