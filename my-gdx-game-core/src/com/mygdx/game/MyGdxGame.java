package com.mygdx.game;

import javax.swing.plaf.synth.Region;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite sprite;
	Sprite playerSprite;
	Sprite leftBorder;
	Sprite rightBorder;
	Sprite topBorder;
	Sprite bottomBorder;
	Texture img;
	Texture backgroundTexture;
	Texture spaceshipTexture;
	Texture borderTexture;
	float screenWidth;
	float screenHeight;
	GameController gameController;
	Polygon playerPolygon;
	PolygonSprite polygonSprite;
	Ellipse playerEllipseX;
	Ellipse playerEllipseY;
	Polygon leftWallPoly, rightWallPoly, topWallPoly, bottomWallPoly;
	Polygon playerPoly;

	@Override
	public void create() {
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		backgroundTexture = new Texture("background.jpg");
		spaceshipTexture = new Texture("spaceship.png");
		SetupBorders();
		sprite = new Sprite(img);
		playerSprite = new Sprite(spaceshipTexture);
		gameController = new GameController();
		gameController.Start();
		playerPolygon = new Polygon();

		// screenWidth/2 -background.getWidth()/2, screenHeight/2 -
		// background.getHeight()/2,
	}

	private void SetupBorders() {
		borderTexture = new Texture("Blue_laser.png");
		leftBorder = new Sprite(borderTexture);
		leftBorder.rotate(90);
		leftBorder.setPosition(0 - borderTexture.getWidth() / 2, screenHeight / 2);
		leftBorder.setScale(5, leftBorder.getScaleY());
		rightBorder = new Sprite(borderTexture);
		rightBorder.rotate(90);
		rightBorder.setPosition(screenWidth - borderTexture.getWidth() / 2, screenHeight / 2);
		rightBorder.setScale(5, rightBorder.getScaleY());
		topBorder = new Sprite(borderTexture);
		topBorder.setPosition(screenWidth / 2, screenHeight - borderTexture.getHeight() / 2);
		topBorder.setScale(5, topBorder.getScaleY());
		bottomBorder = new Sprite(borderTexture);
		bottomBorder.setPosition(screenWidth / 2, 0 - borderTexture.getHeight() / 2);
		bottomBorder.setScale(5, bottomBorder.getScaleY());
		leftWallPoly = new Polygon();
		float[] verticesForWall = new float[] { 0, 0, 20, 0, 20, screenHeight, 0, screenHeight };
		leftWallPoly.setVertices(verticesForWall);

		rightWallPoly = new Polygon();
		verticesForWall = new float[] { screenWidth, 0, screenWidth - 20, 0, screenWidth - 20, screenHeight, screenWidth, screenHeight };
		rightWallPoly.setVertices(verticesForWall);
		
		topWallPoly = new Polygon();
		verticesForWall = new float[] { 0, screenHeight, screenWidth, screenHeight, screenWidth, screenHeight - 20, 0, screenHeight - 20 };
		topWallPoly.setVertices(verticesForWall);
		
		bottomWallPoly = new Polygon();
		verticesForWall = new float[] { 0, 0, screenWidth, 0, screenWidth, 20, 0, 20 };
		bottomWallPoly.setVertices(verticesForWall);
		
		playerPoly = new Polygon();
		// -50, -15, 50, -15, 50, 5, -50, 5
		float[] verticesForPlayer = new float[] { -51, -5, -0, -25, 50, -5, 25, 5, 15, 22, -15, 22, -25, 5, };
		playerPoly.setVertices(verticesForPlayer);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (!Intersector.overlapConvexPolygons(playerPoly, topWallPoly) && Gdx.input.isKeyPressed(Input.Keys.UP))
			gameController.MovePlayer(Direction.Up);

		if (!Intersector.overlapConvexPolygons(playerPoly, bottomWallPoly) && Gdx.input.isKeyPressed(Input.Keys.DOWN))
			gameController.MovePlayer(Direction.Down);

		if (!Intersector.overlapConvexPolygons(playerPoly, leftWallPoly) && Gdx.input.isKeyPressed(Input.Keys.LEFT))
			gameController.MovePlayer(Direction.Left);
		
		if (!Intersector.overlapConvexPolygons(playerPoly, rightWallPoly) && Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			gameController.MovePlayer(Direction.Right);
		
		playerSprite.setScale(gameController.GetPlayerWidth(), gameController.GetPlayerHeight());
		playerSprite.setPosition(gameController.GetPlayerPosition().x, gameController.GetPlayerPosition().y);
		Testing2();

		batch.begin();
		batch.draw(backgroundTexture, 0, 0, 1200, 800);
		leftBorder.draw(batch);
		rightBorder.draw(batch);
		topBorder.draw(batch);

		bottomBorder.draw(batch);
		playerSprite.draw(batch);
		batch.end();

		// if(Intersector.overlapConvexPolygons(beePoly, wallPoly))
		// {
		// System.out.println("bee got collided with the wall");
		// }
		ShapeRenderer sr = new ShapeRenderer();
		sr.setAutoShapeType(true);
		sr.begin();
		//sr.polygon(bottomWallPoly.getTransformedVertices());
		//sr.polygon(playerPoly.getTransformedVertices());
		// sr.ellipse(playerEllipseX.x, playerEllipseX.y, playerEllipseX.width,
		// playerEllipseX.height);
		// sr.ellipse(playerEllipseY.x, playerEllipseY.y, playerEllipseY.width,
		// playerEllipseY.height);
		sr.end();
		// Testing();
	}

	private void Testing2() {
		float posY = gameController.GetPlayerPosition().y + (playerSprite.getHeight() / 2);
		float posX = gameController.GetPlayerPosition().x + (playerSprite.getWidth() / 2);
		// float[]verticesForBee=new float[]{-20,-20, -20,20,-10,20,-10,30,10,30,10,20,
		// 20,20, 20,-20 };
		// Polygon beePoly=new Polygon();
		// beePoly.setVertices(verticesForBee);
		playerPoly.setPosition(posX, posY);

		// System.out.println("Player width = "+gameController.GetPlayerWidth());
		playerEllipseX = new Ellipse(posX - (gameController.GetPlayerWidth() * 251),
				posY - (gameController.GetPlayerHeight() * 82), 101, 23);
		playerEllipseY = new Ellipse(posX - (gameController.GetPlayerWidth() * 113),
				posY - (gameController.GetPlayerHeight() * 115), 48, 48);
	}

	private void Testing() {
		PolygonSprite poly;
		PolygonSpriteBatch polyBatch = new PolygonSpriteBatch(); // To assign at the beginning
		Texture textureSolid;

		// Creating the color filling (but textures would work the same way)
		Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pix.setColor(0xDEADBEFF); // DE is red, AD is green and BE is blue.
		pix.fill();
		textureSolid = new Texture(pix);
		float posY = gameController.GetPlayerPosition().y + (playerSprite.getHeight() / 2);
		float posX = gameController.GetPlayerPosition().x + (playerSprite.getWidth() / 2);
		posX -= 3;
		PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid), new float[] { // Four vertices
				posX, posY, // Vertex 0 3--2
				posX + 50, posY, // Vertex 1 | /|
				posX + 100, posY, // Vertex 1 | /|
				posX + 50, posY + 50, // Vertex 2 |/ |
				posX, posY + 50 // Vertex 3 0--1
		}, new short[] { 0, 1, 2, // Two triangles using vertex indices.
				0, 2, 3 // Take care of the counter-clockwise direction.
		});
		poly = new PolygonSprite(polyReg);
		// poly.setOrigin(a, b);
		polyBatch = new PolygonSpriteBatch();
		polyBatch.begin();
		poly.draw(polyBatch);
		polyBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
		screenWidth = width;
		screenHeight = height;
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
		backgroundTexture.dispose();
		spaceshipTexture.dispose();
		borderTexture.dispose();
	}
}
