package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class ScreenBase implements Screen {

    protected Engine engine;
    protected final Stage stage;

    protected final float w = Gdx.graphics.getWidth();
    protected final float h = Gdx.graphics.getHeight();

    public ScreenBase(Engine engine, String path) {
        this.engine = engine;
        stage = new Stage(new StretchViewport(1000, 1000 * (h / w)));
        Image bg = new Image(new Texture(Gdx.files.internal(path)));
        bg.setSize(1000, 1000 * (h / w));
        stage.addActor(bg);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}
