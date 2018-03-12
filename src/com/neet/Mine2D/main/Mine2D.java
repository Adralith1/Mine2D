package com.neet.Mine2D.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Mine2D {
	
	public static void main(String[] args) {
		// classe de lancement du jeu
		LwjglApplicationConfiguration cfg =
			new LwjglApplicationConfiguration();
		cfg.title = Game.TITLE;
		cfg.width = Game.GAME_WIDTH * Game.SCALE;
		cfg.height = Game.GAME_HEIGHT * Game.SCALE;
		new LwjglApplication(new Game(), cfg);
	}
}
