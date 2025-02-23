package com.mindbreaker.game;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mindbreaker.game.utiles.Config;
import com.mindbreaker.game.utiles.Globales;


public class DesktopLauncher {
	

	
	public static void main(String[] args) {
		
		Globales.esServer = false;
		
		
		if (!Globales.esServer) {
			launchClient();
		} else {
			launchServer();
		}
		
		
		
    }

	
	public static void launchClient() {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(Config.getFps());
		config.setTitle("Mind-Breaker");
		config.setWindowedMode(Config.ANCHO, Config.ALTO);
		new Lwjgl3Application(new MindBreaker(), config);
	}
	
	public static void launchServer() {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(Config.getFps());
		config.setTitle("Mind-Breaker");
		config.setWindowedMode(Config.ANCHO, Config.ALTO);
		new Lwjgl3Application(new MindBreaker(), config);
	}
}


