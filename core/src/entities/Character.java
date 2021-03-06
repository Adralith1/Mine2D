package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import handlers.Animation;
import handlers.Constantes;


import java.util.HashMap;

public class Character extends Entity{ // Entity défini TOUT objet dans le jeu, ici on définit notre personnage

    public static int steeveWidth = 25; // tailles que l'on veut donner à Steeve
    public static int steeveHeight = 62;

	public Texture left,right,idle; //le personnage a trois différentes manières d'exister
	public TextureRegion[] leftReg,rightReg,idleReg; // donc ses textures aussi
	
	public Character(Body body) {
		super(body);
		this.body = body;
		animation = new Animation();
		//right = new Texture("res/images/Steve_Walk.png"); // chargement des textures....
        right = new Texture("images/Steve_Walk.png"); // chargement des textures...
		//left=new Texture("res/images/Steve_Walk_Left.png") ;
        left=new Texture("images/Steve_Walk_Left.png") ;
		//idle=new Texture("res/images/SteveIDLE.png");
        idle=new Texture("images/SteveIDLE.png");

		leftReg = TextureRegion.split(left, 32, steeveHeight)[0]; /// 32 et 62 sont les dimensions des sprites du perso
		rightReg= TextureRegion.split(right,32,steeveHeight)[0];
		idleReg=TextureRegion.split(idle,32,steeveHeight)[0];

		setAnimation(idleReg, 1 / 6f); // par défaut (au début le personnage est IDLE
	}


	// Définit les annimation
	public void setAnimation(TextureRegion[] reg, float delay) {
		animation.setFrames(reg, delay);
		width = reg[0].getRegionWidth();
		height = reg[0].getRegionHeight();
	}
	
	public void update(float dt) {
		animation.update(dt);
	}

	// Affiche le personnage
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(
			animation.getFrame(),
			body.getPosition().x * Constantes.PPM - width / 2,
			body.getPosition().y * Constantes.PPM - height / 2,
                steeveWidth,
                steeveHeight
		);
		sb.end();
	}

	// quand immobile, centre le personnage
	public void centerOnBlocRender(SpriteBatch sb) {

		int blocWidth = 25;

		// calcul du nouveau x
		int blocNum = (int) ((this.getPosition().x * Constantes.PPM) / blocWidth) + 1; // numero du bloc où se situe steeve (à partir de 1)
		float newX = (float) ((blocWidth * blocNum) - 12.5);

		sb.begin();
		sb.draw(
				animation.getFrame(),
				newX,
				body.getPosition().y * Constantes.PPM - height / 2,
                steeveWidth,
                steeveHeight

		);
		sb.end();

	}
	
	public Body getBody() { return body; }
	public Vector2 getPosition() { return body.getPosition(); }
	public float getWidth() { return width; }
	public float getHeight() { return height; }
	
}























