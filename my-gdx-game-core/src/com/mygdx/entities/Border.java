package com.mygdx.entities;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Direction;

public class Border {

	public float width;
	public float height;
	public Vector2 position;
	public Direction direction;
	
	public boolean CheckCollision(AbstractEntity entity) {
		
		return false;
	}
	
}
