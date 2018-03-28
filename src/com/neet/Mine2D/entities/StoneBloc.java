package com.neet.Mine2D.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.neet.Mine2D.main.Game;

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
