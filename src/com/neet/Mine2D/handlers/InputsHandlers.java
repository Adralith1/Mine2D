package com.neet.Mine2D.handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import java.security.Key;

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
	
}
