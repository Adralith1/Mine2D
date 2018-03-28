package handlers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import static handlers.Constantes.PPM;

/**
 * Flo sur fB :
 * Inputs : Si vous avez une commande à rajouter, ajoutez le dans cette classe,
 * une fonctionnalité est associée à un entier, si vous rajouter par exemple "Crouch = 6 "
 * faudra augmenter la taille du tableau NUM_KEYS de 6 à 1, car il définit la taille du tableau d'inputs
 */
public class Inputs {

	//Classe de gestion des touches.

	public static boolean[] keys;
	public static boolean[] pkeys;



	public static final int NUM_KEYS = 9;
	public static final int BUTTON1 = 0;
	public static final int BUTTON2 = 1;
	public static final int DROITE=2;
	public static final int GAUCHE=3;
	public static final int DESTROY=4;
	public static final int BUILD=5;

	public static final int MOUSE_LEFT=7;
	public static final int MOUSE_RIGHT=8;
	public static final int MOUSE_CENTER=9;

	public static Vector3 mouseLocation = new Vector3();

	static {
		keys = new boolean[NUM_KEYS];
		pkeys = new boolean[NUM_KEYS];
	}
	
	public static void update() {
		for(int i = 0; i < NUM_KEYS; i++) {
			pkeys[i] = keys[i];
		}
	}
	
	public static void setKey(int i, boolean b) { keys[i] = b; }
	public static boolean isDown(int i) { return keys[i]; }
	public static boolean isPressed(int i) { return keys[i] && !pkeys[i]; }
	
}
















