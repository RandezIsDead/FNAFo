package com.mygdx.game;

import com.badlogic.gdx.Game;

public class Engine extends Game {
	@Override
	public void create() {
		setScreen(new MainScreen(this));
	}
}