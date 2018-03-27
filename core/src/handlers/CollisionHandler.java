package handlers;

import com.badlogic.gdx.physics.box2d.*;

import com.badlogic.gdx.utils.Array;

/**
 *  là on va beaucoup plus y toucher car j'ai encore un bug à corriger,
 *  si vous voulez tester une collision entre deux trucs ça se passe ici ,
 *  si vous voulez une explication sur des lignes de code en particulier dans cette classe n'hésitez pas,
 *  c'est clairement la plus utile.
 */
public class CollisionHandler implements ContactListener {
	
	private int numFootContacts;
	private Array<Body> blocToDelete;


	public CollisionHandler()
	{
		super();
		blocToDelete=new Array<Body>(); // l'array qui contient les blocs à supprimer.

	}
	
	// provient de box2D, c'est une fonction automatiquement appelé lors d'un contact entre deux objet
	// permet d'effectuer une action lors d'un conatct ici de supprimer des blocs
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
		// delete les null + mettre des objet (si possible) plutot que par chaine
        // pour le mettre dans l'user data
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

		if(fa.getUserData().equals("player") && fb.getUserData().equals("zombie") || fb.getUserData().equals("player") && fa.getUserData().equals("zombie")) {
            System.out.println("dead Player");
            //fa.getShape().dispose();
			//return;
        }

        if(fa.getUserData().equals("foot") && fb.getUserData().equals("zombie") || fb.getUserData().equals("foot") && fa.getUserData().equals("zombie")) {
            System.out.println("dead Zombie");
        }
	}

	// provient de box2D, c'est une fonction automatiquement appelé lors de la fin d'un contact entre deux objet
	// et non lorsqu'il n'y a pas de contact c'est différent.
	// permet d'effectuer une action lors d'une fin de conatct ici de retirer le compteur de bloc à ces pieds
	// (exemple entre le personnage et les bloc un changement de vitesse, ainsi en fin de contact lors d'un saut on
	// obtient un effet trampoline mais on ne l'a pas une fois en l'air)
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









