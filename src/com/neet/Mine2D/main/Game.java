package com.neet.Mine2D.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.neet.Mine2D.handlers.Inputs;
import com.neet.Mine2D.handlers.InputsHandlers;
import com.neet.Mine2D.handlers.TextureLoader;
import com.neet.Mine2D.states.Play;

public class Game implements ApplicationListener {
	
	public static final String TITLE = "MINE2D";
	public static final int GAME_WIDTH = 320; //résolution de la fenêtre en largeur
	public static final int GAME_HEIGHT = 240; // en hauteur
	public static final int SCALE = 3; // échelle de résolution

	
	public static final float STEP = 1 / 60f; // Nécessaire au calcul des frames
	private float accum;
	
	private SpriteBatch sb;
	private OrthographicCamera cam;
	
	private Play play;
	
	public static TextureLoader res; //asocier une texture à une clé
	public static TextureLoader left;
	
	public void create() {
		
		Texture.setEnforcePotImages(false);
		Gdx.input.setInputProcessor(new InputsHandlers());

		//suite d'association chemin / clé
		res = new TextureLoader();
		res.loadTexture("res/images/Steve_Walk.png", "steve");
		res.loadTexture("res/images/dirtBloc.png","dirtb");
		res.loadTexture("res/images/stoneBloc.png","stoneb");
		res.loadTexture("res/images/videBloc.png","videb");
		res.loadTexture("res/images/background.png","backg");

		left=new TextureLoader();
		left.loadTexture("res/images/Steve_Walk_Left.png", "left");
		
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);
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
	
	public void resize(int w, int h) {}
	public void pause() {}
	public void resume() {}
	
}
