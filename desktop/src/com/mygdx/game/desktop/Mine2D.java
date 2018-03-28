package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Game;

public class Mine2D {
	
	public static void main(String[] args) {
		// classe de lancement du jeu
		LwjglApplicationConfiguration cfg =
			new LwjglApplicationConfiguration();
		cfg.title = Game.TITLE;
		//cfg.width = Game.GAME_WIDTH * Game.SCALE;
		//cfg.height = Game.GAME_HEIGHT * Game.SCALE;

		cfg.width = 320 * 3;
		cfg.height = 240 * 3;

		new LwjglApplication(new Game(), cfg);
	}
}
