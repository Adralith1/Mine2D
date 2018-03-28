package com.neet.Mine2D.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.neet.Mine2D.handlers.Animation;
import com.neet.Mine2D.handlers.Constantes;

import java.util.HashMap;

public class Entity {
    protected Animation animation;
    public Body body;
    protected float width;
    protected float height;


    public Entity(Body body)
    {
        this.body = body;
        animation=new Animation();
    }

    public void setAnimation(TextureRegion[] reg, float delay) {
        animation.setFrames(reg, delay);
        width = reg[0].getRegionWidth();
        height = reg[0].getRegionHeight();
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
