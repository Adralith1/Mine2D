package handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import java.security.Key;

/**
 * Flo sur FB :
 *  InputsHandler : Juste pour dire que si t'appui sur Z, bah tu génére le chiffre que tu souhaites binder,
 *  par exemple, Z = BUTTON1 nous c'est le saut
 */
public class InputsHandlers extends InputAdapter implements InputProcessor{
	// en fonction de la touche choisie on setKey pour une action
	public boolean keyDown(int k) {
		if(k == Keys.Z) {
			Inputs.setKey(Inputs.BUTTON1, true);
		}
		if(k == Keys.X) {
			Inputs.setKey(Inputs.BUTTON2, true);
		}
		if(k== Keys.D)
		{
			Inputs.setKey(Inputs.DROITE,true);
		}
		if(k==Keys.Q)
		{
			Inputs.setKey(Inputs.GAUCHE,true);
		}
		if(k== Keys.NUM_1)
		{
			Inputs.setKey(Inputs.DESTROY,true);
		}
		if(k== Keys.NUM_2)
		{
			Inputs.setKey(Inputs.BUILD,true);
		}
		return true;
	}
	
	public boolean keyUp(int k) {
		if(k == Keys.Z) {
			Inputs.setKey(Inputs.BUTTON1, false);
		}
		if(k == Keys.X) {
			Inputs.setKey(Inputs.BUTTON2, false);
		}
		if(k== Keys.D)
		{
			Inputs.setKey(Inputs.DROITE,false);
		}
		if(k==Keys.Q)
		{
			Inputs.setKey(Inputs.GAUCHE,false);
		}
		if(k== Keys.NUM_1)
		{
			Inputs.setKey(Inputs.DESTROY,false);
		}
		if(k== Keys.NUM_2)
		{
			Inputs.setKey(Inputs.BUILD,false);
		}
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(button == 0){
			Inputs.setKey(Inputs.MOUSE_LEFT,true);
		}else if(button == 1){
			Inputs.setKey(Inputs.MOUSE_RIGHT,true);
		}else if(button == 2){
			Inputs.setKey(Inputs.MOUSE_CENTER,true);
		}
		//Inputs.mouseLocation.x = screenX;
		//Inputs.mouseLocation.y = screenY;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(button == 0){
			Inputs.setKey(Inputs.MOUSE_LEFT,false);
		}else if(button == 1){
			Inputs.setKey(Inputs.MOUSE_RIGHT,false);
		}else if(button == 2){
			Inputs.setKey(Inputs.MOUSE_CENTER,false);
		}
		//Inputs.mouseLocation.x = screenX;
		//Inputs.mouseLocation.y = screenY;
		return false;
	}


	
}
