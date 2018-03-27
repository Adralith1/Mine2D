package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import handlers.Animation;
import handlers.Constantes;

public class Zombie extends Entity {

    public Texture left,right,idle; //le personnage a trois différentes manières d'exister
    public TextureRegion[] leftReg,rightReg,idleReg; // donc ses textures aussi

    public Zombie(Body body) {
        super(body);
        this.body = body;
        animation = new Animation();
        left=new Texture("core/assets/images/rick_Walk_Left.png") ;
        idle=new Texture("core/assets/images/rickIDLE.png");

        leftReg = TextureRegion.split(left, 62, 103)[0];
        idleReg=TextureRegion.split(idle,52,62)[0];

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
                body.getPosition().y * Constantes.PPM - height / 2
        );
        sb.end();
    }

    public void dispose() {
        body.resetMassData();
        body.destroyFixture(body.getFixtureList().first());
    }

    public Body getBody() { return body; }
    public Vector2 getPosition() { return body.getPosition(); }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
}
