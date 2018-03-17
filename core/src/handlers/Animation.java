package handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Flo sur FB :
 * Ici pareil on écrira pas grand chose de plus,
 * en gros ça sert juste à dire que même entre deux frame tu peux te prendre un pain dans la gueule
 * + Incrémenter les frames du personnage,
 * en fait c'est une gestion manuelle de l'affichage des sprites.
 */
public class Animation {
	// Classe de définition des Animation équivalant à la Classe Sting mais ne gère pas une liste de char
	// mais plusieur field primitif ou non.
	// Chaque entites (perso, bloc) peut définir son animation en implémentent une instance de cette classe
	private TextureRegion[] frames;
	private float time;
	private float delay;
	private int currentFrame;
	private int timesPlayed;
	
	public Animation() {}
	
	public Animation(TextureRegion[] frames, float delay) {
		setFrames(frames, delay);
	}
	
	public void setFrames(TextureRegion[] frames, float delay) {
		this.frames = frames;
		this.delay = delay;
		time = 0;
		currentFrame = 0;
		timesPlayed = 0;
	}
	
	public void update(float dt) {
		if(delay <= 0) return;
		time += dt;
		while(time >= delay) {
			step();
		}
	}
	
	private void step() { // le temps durant lequel le jeu passe une frame
		time -= delay;
		currentFrame++;
		if(currentFrame == frames.length) {
			currentFrame = 0;
			timesPlayed++;
		}
	}
	
	public TextureRegion getFrame() { return frames[currentFrame]; }
	public int getTimesPlayed() { return timesPlayed; }
	
}













