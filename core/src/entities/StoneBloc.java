package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Game;

public class StoneBloc extends Bloc {
    public StoneBloc(Body body) {
        super(body);
        Texture tex = Game.res.getTexture("stoneb");
        TextureRegion[] sprites = TextureRegion.split(tex, 25, 25)[0];

        setAnimation(sprites, 1 / 12f);
    }
    public void update(float dt) {
        animation.update(dt);
    }
}
