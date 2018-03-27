package states;


import static handlers.Constantes.PPM;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Game;
import entities.BackGround;
import entities.Bloc;
import entities.DirtBloc;
import entities.StoneBloc;
import handlers.CollisionHandler;
import handlers.Inputs;
import entities.Character;
import entities.Zombie;


/**
 * Flo sur FB : Play : TU PASSERAS FORCEMENT PAR LA
 c'est dans cette classe là que toutes les fonctions crées dans les autres classes sont utilisées,
 clairement quelquechose comme :
 	public void build() serait dans cette classe
 	public void crouch() serait à ajouter dans handleInput(),
 	tout objet à créer se créer là et se render là également 🙂
 */
public class Play extends Stage {
	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	protected Game game;
	private boolean showBoxes = false;
	private World world; //Ajoute un monde dans lequel sont les entites
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera b2dCam;
	private CollisionHandler cl;
	private TiledMap tileMap;
	private float tileSize;
	private OrthogonalTiledMapRenderer tmr;
	private Character steve;
	private Zombie rick;
	public Array<Bloc> allBlocs;
	public Array<Bloc> background;

	// Constructeur : ici on définit tous nos entites et notre monde dans lequel ils sont
	public Play(Game game) {
		sb = game.getSpriteBatch();
		cam = game.getCamera();
		world = new World(new Vector2(0, -9.81f), true);
		cl = new CollisionHandler();
		world.setContactListener(cl);
		b2dr = new Box2DDebugRenderer();
		createPlayer();
		createZombie();
		allBlocs=new Array<Bloc>();
		background=new Array<Bloc>();

		createTiles();
		createBackground(background);
		createDirtBlocs(allBlocs);
		createStoneBlocs(allBlocs);
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Game.GAME_WIDTH / PPM, Game.GAME_HEIGHT / PPM);

	}


	
	public void handleInput() { // Sert à gérer les INPUT en cours de jeu et effectuer une action

		if(Inputs.isPressed(Inputs.BUTTON1)) {
			if(cl.isPlayerOnGround()) {
				steve.getBody().applyForceToCenter(0, 200, true);
			}
		}
		if(Inputs.isPressed(Inputs.DROITE) )
		{
			if(cl.isPlayerOnGround() && !Inputs.isDown(3))
			{
				steve.getBody().applyForceToCenter(50,0,true);
				steve.setAnimation(steve.rightReg,1/6f);
			}
		}
		if(Inputs.isPressed(Inputs.GAUCHE) )
		{
			if(cl.isPlayerOnGround() && !Inputs.isDown(2))
			{
				steve.getBody().applyForceToCenter(-50,0,true);
				steve.setAnimation(steve.leftReg,1/6f);
			}
		}

		if(Inputs.isPressed(Inputs.MOUSE_LEFT)) {
			System.out.println("Cliquez bande de salope !");
			rick.dispose();
		}
		
	}



	public void handleBuilding() { // PAS encore terminée, devra servir à construire un bloc
		int mouseXcoords = Gdx.input.getX();
		int mouseYcoords = Gdx.input.getY();

		if (Inputs.isPressed(Inputs.DESTROY)) {
			System.out.println("CoordX :" +mouseXcoords);
			System.out.println("CoordX :" +mouseYcoords);
		} else if (Inputs.isPressed(Inputs.BUILD)) {
			TiledMapTileLayer layer=(TiledMapTileLayer)tileMap.getLayers().get("stone");
			Rectangle cellRect=new Rectangle();

			for(int row = 0; row < layer.getHeight(); row++) {
				for (int col = 0; col < layer.getWidth(); col++) {
					cellRect.x=(col + 0.5f) * tileSize / PPM;
					cellRect.y=(row + 0.5f) * tileSize / PPM;
					cellRect.width=25;
					Cell cell = layer.getCell(col, row);
					if(cellRect.contains(new Vector2(mouseXcoords,mouseYcoords)))
					{
						System.out.println("CLICKED");
					}
				}
			}
		}
	}

	public void handleZombie() {
		rick.setAnimation(rick.leftReg,1/6f);
	}
	
	public void update(float dt) { // Sert à prendre le nouvel état de chaque élément
		
		handleInput();
		handleBuilding();
		handleZombie();
		world.step(dt, 6, 2);
		if (Inputs.isPressed(Inputs.DESTROY)) {
			Array<Body> bodies = cl.getBlocToDelete();
			for (int i = 0; i < bodies.size; i++) {
				Body b = bodies.get(i);
				allBlocs.removeValue((Bloc) b.getUserData(), true);
				world.destroyBody(b);
			}
			bodies.clear();
		}
		if(steve.body.getLinearVelocity().x == 0)
		{
			steve.setAnimation(steve.idleReg,1/6f);
		}
		steve.update(dt);
		rick.update(dt);

		for(int i = 0; i < allBlocs.size; i++) {
			allBlocs.get(i).update(dt);
		}
		for(int i = 0; i < background.size; i++) {
			background.get(i).update(dt);
		}
	}
	
	public void render() { // faire le rendu

		// clear screen

		//Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);


		//sb.draw(background,0,0, game.GAME_WIDTH,game.GAME_HEIGHT);
		//sb.end();
		//ci-dessous : Positionner le personnage au quart de la caméra en largeur et au centre en hauteur. z=0 car pas de 3D
		cam.position.set(steve.getPosition().x*PPM + Game.GAME_WIDTH/4,
				steve.getPosition().y*PPM  ,
				0);
		cam.update();

		sb.setProjectionMatrix(cam.combined);
		for(int i=0;i<background.size;i++)
		{
			background.get(i).render(sb);
		}
		steve.render(sb);
		rick.render(sb);

		for(int i=0;i<allBlocs.size;i++)
		{
			allBlocs.get(i).render(sb);
		}

		if(showBoxes) {
			b2dr.render(world, b2dCam.combined);
		}
		//sb.end();
		
	}
	
	public void dispose() {
    }

	/**
	 * Ci-dessus les methodes qui servent à définir les entites, tu peux les voirs comme un "new"
	 * genre l'ajout d'un bloc dans le constructeur Play ne se fera pas
	 * Bloc dirt = new Bloc(param) mais s'écrit ici createBloc(param)
	 * Et c'est pareille avec chaque entites
	 */
	
	private void createPlayer() { // Création du personnage et de sa hitbox
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		// create player
		bdef.position.set(160 / PPM, 430 / PPM);
		bdef.type = BodyType.DynamicBody;
		bdef.linearVelocity.set(0, 0);
		Body body = world.createBody(bdef);
		
		shape.setAsBox(14 / PPM, 28 / PPM);
		fdef.shape = shape;
		body.createFixture(fdef).setUserData("player");
		
		// create foot sensor
		shape.setAsBox(13 / PPM, 10 / PPM, new Vector2(0, -20 / PPM), 0);
		fdef.shape = shape;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("foot");
		
		// create player
		steve =new Character(body);
		body.setUserData(steve);
		
	}

	private void createZombie() { // Création du zombie et de sa hitbox

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();

		// create player
		bdef.position.set(480 / PPM, 430 / PPM);
		bdef.type = BodyType.DynamicBody;
		bdef.linearVelocity.set(-0.2f, 0);
		Body body = world.createBody(bdef);

		shape.setAsBox(14 / PPM, 28 / PPM);
		fdef.shape = shape;
		body.createFixture(fdef).setUserData("zombie");

		// create player
		rick =new Zombie(body);
		body.setUserData(rick);

	}

	private void createTiles() { // Récupération du fichier.tmx
		//tileMap = new TmxMapLoader().load("res/maps/testObject.tmx");
        tileMap = new TmxMapLoader().load("core/assets/maps/testObject.tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		tileSize = 25;

	}

	private void createBackground(Array<Bloc> background)
	{
		MapLayer layer= this.tileMap.getLayers().get("background");
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		for(MapObject mo : layer.getObjects()) {
			bdef.type = BodyType.StaticBody;
			Body bod = world.createBody(bdef);
			//bod.createFixture(fdef).setUserData("background");
			BackGround bg = new BackGround(bod);
			background.add(bg);
		}
	}

	///CREATE OBJECT HERE
	private void createDirtBlocs(Array<Bloc> allBlocs) { // création des blocs de terre

		MapLayer layer = this.tileMap.getLayers().get("dirt");
		System.out.println(layer.getObjects().getCount());
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		for(MapObject mo : layer.getObjects()) {
			bdef.type = BodyType.StaticBody;
			float x =  (mo.getProperties().get("x", Float.class)) / PPM;
			float y = (mo.getProperties().get("y", Float.class)) / PPM;
			ChainShape cs = new ChainShape();
			Vector2[] v = new Vector2[3];
			v[0] = new Vector2(
					-tileSize / 2 / PPM, -tileSize / 2 / PPM);
			v[1] = new Vector2(
					-tileSize / 2 / PPM, tileSize / 2 / PPM);
			v[2] = new Vector2(
					tileSize / 2 / PPM, tileSize / 2 / PPM);
			cs.createChain(v);
			fdef.friction = 0;
			fdef.shape = cs;
			fdef.isSensor = false;
			//Pour retrouver quel bloc je tape
			Body bod=world.createBody(bdef);
			bod.createFixture(fdef).setUserData("dirt");
			//
			bdef.position.set(x, y);
			Body body = world.createBody(bdef);
			body.createFixture(fdef).setUserData("dirt");
			DirtBloc db = new DirtBloc(body);
			allBlocs.add(db);
			body.setUserData(db);
		}
	}
	private void createStoneBlocs(Array<Bloc> allBlocs) { // création des blocs de Pierre
		MapLayer layer = tileMap.getLayers().get("stone");
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		for(MapObject mo : layer.getObjects()) {
			bdef.type = BodyType.StaticBody;
			float x = mo.getProperties().get("x" , Float.class) / PPM;
			float y =  mo.getProperties().get("y" , Float.class) / PPM;
			ChainShape cs = new ChainShape();
			Vector2[] v = new Vector2[5];
			v[0] = new Vector2(
					-tileSize / 2 / PPM, -tileSize / 2 / PPM);
			v[1] = new Vector2(
					-tileSize / 2 / PPM, tileSize / 2 / PPM);
			v[2] = new Vector2(
					tileSize / 2 / PPM, tileSize / 2 / PPM);
			v[3] = new Vector2(
					tileSize / 2 / PPM, -tileSize / 2 / PPM);
			v[4] = new Vector2(
					-tileSize / 2 / PPM, -tileSize / 2 / PPM);

			cs.createChain(v);
			fdef.friction = 0;
			fdef.shape = cs;
			fdef.isSensor = false;
			bdef.position.set(x, y);
			Body bod=world.createBody(bdef);
			bod.createFixture(fdef).setUserData("stone");
			StoneBloc sb = new StoneBloc(bod);
			allBlocs.add(sb);
			bod.setUserData(sb);
		}
	}
	
}









