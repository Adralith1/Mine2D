package com.neet.Mine2D.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.neet.Mine2D.handlers.Animation;
import com.neet.Mine2D.handlers.Constantes;


public class Bloc extends Entity{ //va être la "mère" de tous les blocs qui auront le même comportement

    public Bloc(Body body) // utilise le constructeur du super car possède les mêmes attributs
    {
        super(body);
        this.body = body;
        animation=new Animation();
    }

    // l'animation sera un sprite immobile, mais inévitable si on souhaite gérer la map en objets
    //et pas en tiles (les tiles rendent difficile la destruction de ces dernières
    public void setAnimation(TextureRegion[] reg, float delay) {
        animation.setFrames(reg, delay);
        width = reg[0].getRegionWidth();
        height = reg[0].getRegionHeight();
    }

    //sert simplement à dessiner le sprite correspondant
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(
                animation.getFrame(),
                body.getPosition().x * Constantes.PPM - width / 2,
                body.getPosition().y * Constantes.PPM - height / 2
        );
        sb.end();
    }
    public void update(float dt) {
        animation.update(dt);
    } // mettre à jour l'animation

    //ci- dessous des get et set
    public Body getBody() { return body; }
    public Vector2 getPosition() { return body.getPosition(); }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
}
