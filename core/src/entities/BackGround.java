package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Game;


public class BackGround extends Bloc {
    public BackGround(Body body) {
        super(body);
        Texture tex = Game.res.getTexture("backg");
        TextureRegion[] sprites = TextureRegion.split(tex, 800, 450)[0];

        setAnimation(sprites, 1 / 12f);
    }

    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(
                animation.getFrame(),
                -100,
                100
        );
        sb.end();
    }
}
