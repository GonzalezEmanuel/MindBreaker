package com.mindbreaker.game.io;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.mindbreaker.game.net.HiloCliente;

public class KeyListener implements InputProcessor {
	
	private boolean up = false, down = false, up2 = false, down2 = false;
	
	private HiloCliente hc;
	
	public KeyListener(HiloCliente hc) {
		this.hc = hc;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.DOWN) {
			down = false;
			hc.enviarMensaje("DejeApretarAbajo");
		}
		
		if (keycode== Keys.UP) {
			up = false;
			hc.enviarMensaje("DejeApretarArriba");
		}
		
//		if (keycode == Keys.S) {
//			down2 = false;
//		}
//		
//		if (keycode == Keys.A) {
//			up2 = false;
//		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
