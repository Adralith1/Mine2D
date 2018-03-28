package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import handlers.Inputs;
import handlers.InputsHandlers;
import handlers.TextureLoader;
import states.Play;

/**
 * Flo sur FB :
 * La classe servant à faire des associations de sprite à des string qu'on définira plus tard;
 * c'est également une classe utile à l'initiation des objets "Abstraits du jeu,
 * comme une caméra, un SpriteBatch (un gestionnaire de sprites)
 */
public class Game implements ApplicationListener {
	
	public static final String TITLE = "MINE2D";
	public static final int GAME_WIDTH = 320; //résolution de la fenêtre en largeur
	public static final int GAME_HEIGHT = 240; // en hauteur

	public static final float SCALE = 32f; // échelle de résolution

    public final static float INV_SCALE = 1.f/SCALE;
    // this is our "target" resolution, not that the window can be any size, it is not bound to this one
    public final static float VP_WIDTH = (GAME_WIDTH * 3) * INV_SCALE;
    public final static float VP_HEIGHT = (GAME_HEIGHT * 3) * INV_SCALE;

	
	public static final float STEP = 1 / 60f; // Nécessaire au calcul des frames
	private float accum;
	
	private SpriteBatch sb;
	public static OrthographicCamera cam;
	private ExtendViewport viewport;
	
	private Play play;
	
	public static TextureLoader res; //asocier une texture à une clé
	public static TextureLoader left;
	
	public void create() {

		// Ajout le contrôle des touches clavier avec le jeu
		
		//Texture.setEnforcePotImages(false);
		Gdx.input.setInputProcessor(new InputsHandlers());

		Inputs.setKey(Inputs.MOUSE_LEFT,false);

		//suite d'association chemin / clé
		res = new TextureLoader();
		//res.loadTexture("res/images/Steve_Walk.png", "steve");
        res.loadTexture("core/assets/images/Steve_Walk.png", "steve");
		//res.loadTexture("res/images/dirtBloc.png","dirtb");
        res.loadTexture("core/assets/images/dirtBloc.png","dirtb");
		//res.loadTexture("res/images/stoneBloc.png","stoneb");
        res.loadTexture("core/assets/images/stoneBloc.png","stoneb");
		//res.loadTexture("res/images/videBloc.png","videb");
        res.loadTexture("core/assets/images/videBloc.png","videb");
		//res.loadTexture("res/images/background.png","backg");
        res.loadTexture("core/assets/images/background.png","backg");

        res.loadTexture("core/assets/images/rickIDLE.png","zombie");

		left=new TextureLoader();
		//left.loadTexture("res/images/Steve_Walk_Left.png", "left");
        left.loadTexture("core/assets/images/Steve_Walk_Left.png", "left");
        left.loadTexture("core/assets/images/rick_Walk_Left.png", "left_zombie");

		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);

        viewport = new ExtendViewport(VP_WIDTH, VP_HEIGHT, cam);

		play = new Play(this);
	}

	public void render() { // on fait le rendu selon le STEP. (FPS)
		
		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP) {
			accum -= STEP;
			play.update(STEP);
			play.render();
			Inputs.update();
		}
		
	}
	
	public void dispose() {
		
	}
	
	public SpriteBatch getSpriteBatch() { return sb; }
	public OrthographicCamera getCamera() { return cam; }
	
	public void resize(int w, int h) {  }
	public void pause() {}
	public void resume() {}
	
}
