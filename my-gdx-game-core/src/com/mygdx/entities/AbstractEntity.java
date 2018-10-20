package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractEntity {

	public AbstractEntity(Texture image) {
	   this.image = image;
	   this.position = new Vector2();
	}
	public Texture image;
	public float width;
	public float height;
	public Vector2 position;
	public int bounds;
	public float speed;
}
