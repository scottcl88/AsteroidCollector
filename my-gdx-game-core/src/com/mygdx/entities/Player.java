package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Direction;

public class Player extends AbstractEntity implements IMoveable {

	public Player(Texture image) {
		super(image);
		this.speed = 10f;
		this.width = .2f;
		this.height = .2f;
	}

	@Override
	public void Move(Direction direction) {
		switch (direction) {
		case Up: {
			this.position.y += speed;
			break;
		}
		case Down: {
			this.position.y -= speed;
			break;
		}
		case Left: {
			this.position.x -= speed;
			break;
		}
		case Right: {
			this.position.x += speed;
			break;
		}
		}
	}

}
