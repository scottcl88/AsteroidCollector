package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.IMoveable;
import com.mygdx.entities.Player;

public class GameController {

	private Difficulty difficulty;
	private Player player;
	private ArrayList<IMoveable> currentEntities;

	public GameController() {
		player = new Player(null);
		currentEntities = new ArrayList<IMoveable>();
	}

	public void Start() {
		difficulty = Difficulty.Normal;
	}

	public Vector2 GetPlayerPosition() {
		return player.position;
	}

	public float GetPlayerWidth() {
		return player.width;
	}

	public float GetPlayerHeight() {
		return player.height;
	}

	public void MovePlayer(Direction direction) {
		player.Move(direction);
	}

	public void Move(Direction direction) {
		for (IMoveable entity : currentEntities) {
			entity.Move(direction);
		}
	}

	public void Pause() {

	}

}
