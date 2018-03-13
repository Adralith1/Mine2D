package handlers;

import com.badlogic.gdx.physics.box2d.*;

import com.badlogic.gdx.utils.Array;

public class CollisionHandler implements ContactListener {
	
	private int numFootContacts;
	private Array<Body> blocToDelete;


	public CollisionHandler()
	{
		super();
		blocToDelete=new Array<Body>(); // l'array qui contient les blocs à supprimer.

	}
	
	// called when two fixtures start to collide
	public void beginContact(Contact c) {
		
		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();
		if(fa==null || fb==null) return;
		if(fa.getUserData() != null && fa.getUserData().equals("foot")) {// incrémenter les pas de l'utilisateur
			numFootContacts++;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("foot")) {
			numFootContacts++;
		}

		//on vérifie la collision entre fa et fb, mais on ne sait pas qui est qui ? Et on fait attention de l'ajouter qu'une seule fois, sinon erreur
		if(fa.getUserData() != null && fa.getUserData().equals("dirt") && !blocToDelete.contains(fa.getBody(),true)) {
			blocToDelete.add(fa.getBody());
		}
		if(fb.getUserData() != null && fb.getUserData().equals("dirt") && !blocToDelete.contains(fb.getBody(),true)) {
			blocToDelete.add(fb.getBody());
		}
		if(fa.getUserData() != null && fa.getUserData().equals("stone") && !blocToDelete.contains(fa.getBody(),true)) {
			blocToDelete.add(fa.getBody());
		}
		if(fb.getUserData() != null && fb.getUserData().equals("stone") && !blocToDelete.contains(fb.getBody(),true)) {
			blocToDelete.add(fb.getBody());
		}
		
	}
	
	// called when two fixtures no longer collide
	public void endContact(Contact c) {
		
		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();
		if(fa==null || fb==null) return;
		if(fa.getUserData() != null && fa.getUserData().equals("foot")) {
			numFootContacts--;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("foot")) {
			numFootContacts--;
		}
		// il faudrait ci-dessous retirer les blocs sur lesquels le joueur n'est plus

		
	}
	
	public boolean isPlayerOnGround() { return numFootContacts > 0; }

	public Array<Body> getBlocToDelete() {
		return blocToDelete;
	}



	public void preSolve(Contact c, Manifold m) {} //héritée de contact listener (donc devoir de l'implémenter)
	public void postSolve(Contact c, ContactImpulse ci) {} // de même
	
}









