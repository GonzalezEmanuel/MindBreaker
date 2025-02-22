package com.mindbreaker.game.utiles;

public class Config {
    
    public static int ANCHO = 1280; 
    public static int ALTO = 720;
    private static float VOLUMEN = 0.5f;
    private static int FPS = 60;

    public static void setVolumen(float volumen) {
        VOLUMEN = Math.max(0, Math.min(1, volumen));
    }


    public static int getFps() {
		return FPS;
	}


	public static void setFps(int fPS) {
		FPS = fPS;
	}


	public static float getVolumen() {
        return VOLUMEN;
    }
}
