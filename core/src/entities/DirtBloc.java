package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.*;
import entities.*;
import handlers.TextureLoader;
import states.*;


public class DirtBloc extends Bloc {
    public DirtBloc(Body body) {
        super(body);

        Texture tex = Game.res.getTexture("dirtb"); // on associe la texture au bloc avec la clé dirtb
        TextureRegion[] sprites = TextureRegion.split(tex, 25, 25)[0]; // dimensions

        setAnimation(sprites, 1 / 12f); // 1/12f est le temps entre chaque animation (ici on pourrait le baisser puisque justement il ne change pas et le mettre à 1.0 ?
    }

    public void update(float dt) {
        animation.update(dt);
    }

}
