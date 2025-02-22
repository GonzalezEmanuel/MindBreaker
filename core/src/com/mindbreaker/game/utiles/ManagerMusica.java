package com.mindbreaker.game.utiles;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class ManagerMusica {
	 private static HashMap<String, Music> musicMap = new HashMap<>();
	    private static Music currentMusic;

	    public static void playMusic(String filePath) {
	        if (currentMusic != null) {
	            currentMusic.stop();
	        }

	        if (!musicMap.containsKey(filePath)) {
	            Music music = Gdx.audio.newMusic(Gdx.files.internal(filePath));
	            music.setLooping(true);
	            music.setVolume(Config.getVolumen());
	           
	            
	            
	            musicMap.put(filePath, music);
	        }

	        currentMusic = musicMap.get(filePath);
	        currentMusic.play();
	    }
	   
	    
	    public static Music getCurrentMusic() {
			return currentMusic;
		}

		public static void pauseMusic() {
	        if (currentMusic != null && currentMusic.isPlaying()) {
	            currentMusic.pause();
	        }
	    }

	    public static void resumeMusic() {
	        if (currentMusic != null && !currentMusic.isPlaying()) {
	            currentMusic.play();
	        }
	    }

	    public static void stopMusic() {
	        if (currentMusic != null) {
	            currentMusic.stop();
	        }
	    }

	    public static void dispose() {
	        for (Music music : musicMap.values()) {
	            music.dispose();
	        }
	        musicMap.clear();
	    }
}
