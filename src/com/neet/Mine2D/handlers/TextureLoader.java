package com.neet.Mine2D.handlers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

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













