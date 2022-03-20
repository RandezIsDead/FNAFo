package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MainScreen extends ScreenBase {

    public MainScreen(Engine engine) {
        super(engine, "main_menu.png");
        this.engine = engine;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) engine.setScreen(new GameScreen(engine));
    }
}