package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import handlers.Animation;
import handlers.Constantes;


import java.util.HashMap;

public class Character extends Entity{ // Entity défini TOUT objet dans le jeu
	

	public Texture left,right,idle; //le personnage a trois différentes manières d'exister
	public TextureRegion[] leftReg,rightReg,idleReg; // donc ses textures aussi
	
	public Character(Body body) {
		super(body);
		this.body = body;
		animation = new Animation();
		//right = new Texture("res/images/Steve_Walk.png"); // chargement des textures....
        right = new Texture("core/assets/images/Steve_Walk.png"); // chargement des textures...
		//left=new Texture("res/images/Steve_Walk_Left.png") ;
        left=new Texture("core/assets/images/Steve_Walk_Left.png") ;
		//idle=new Texture("res/images/SteveIDLE.png");
        idle=new Texture("core/assets/images/SteveIDLE.png");

		leftReg = TextureRegion.split(left, 32, 62)[0]; /// 32 et 62 sont les dimensions des sprites du perso
		rightReg= TextureRegion.split(right,32,62)[0];
		idleReg=TextureRegion.split(idle,32,62)[0];

		setAnimation(idleReg, 1 / 6f); // par défaut (au début le personnage est IDLE
	}


	
	public void setAnimation(TextureRegion[] reg, float delay) {
		animation.setFrames(reg, delay);
		width = reg[0].getRegionWidth();
		height = reg[0].getRegionHeight();
	}
	
	public void update(float dt) {
		animation.update(dt);
	}
	
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(
			animation.getFrame(),
			body.getPosition().x * Constantes.PPM - width / 2,
			body.getPosition().y * Constantes.PPM - height / 2
		);
		sb.end();
	}
	
	public Body getBody() { return body; }
	public Vector2 getPosition() { return body.getPosition(); }
	public float getWidth() { return width; }
	public float getHeight() { return height; }
	
}























