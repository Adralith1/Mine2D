package handlers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Flo sur FB :
 * TextureLoader : C'est clairement juste pour gagner du temps avec les sprites,
 * c'est la création de la fonction qui associe dans un tableau type "Hashmap" une string à une clé,
 * c'est un peu le principe des tableaux du javascript où tu peux faire fruits["rouge"]
 * et t'as genre une cerise stockée dedans
 */
public class TextureLoader {
	
	private HashMap<String, Texture> textures; // contient une association clé / texture
	
	public TextureLoader() {
		textures = new HashMap<String, Texture>();
	} // constructeur
	
	public void loadTexture(String path, String key) { // fonction pour load une texture
		Texture tex = new Texture(Gdx.files.internal(path));
		textures.put(key, tex);
	}
	
	public Texture getTexture(String key) {
		return textures.get(key);
	} // get...
	
	public void disposeTexture(String key) { // supprimer la texture ? Peut-être inutile.
		Texture tex = textures.get(key);
		if(tex != null) tex.dispose();
	}
	
}













