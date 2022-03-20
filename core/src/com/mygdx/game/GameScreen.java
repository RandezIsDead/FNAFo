package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GameScreen extends ScreenBase {

    private boolean isLDOpened = true;
    private boolean isRdOpened = true;
    private boolean isLightLeft = true;
    private boolean isLightRight = true;
    private final Image ld = new Image(new Texture(Gdx.files.internal("ld.png")));
    private final Image rd = new Image(new Texture(Gdx.files.internal("rd.png")));
    private final Image lightL = new Image(new Texture(Gdx.files.internal("lightL.png")));
    private final Image lightR = new Image(new Texture(Gdx.files.internal("lightR.png")));

    private boolean isCamOpened = false;
    private final Image cam1 = new Image(new Texture(Gdx.files.internal("cam1.jpg")));
    private final Image cam2 = new Image(new Texture(Gdx.files.internal("cam2.jpg")));
    private final Image cam3 = new Image(new Texture(Gdx.files.internal("cam3.jpg")));

    private int t = 0;
    private int time = 0;
    private final Image pm12 = new Image(new Texture(Gdx.files.internal("time/12pm.png")));
    private final Image pm1 = new Image(new Texture(Gdx.files.internal("time/1pm.png")));
    private final Image pm2 = new Image(new Texture(Gdx.files.internal("time/2pm.png")));
    private final Image pm3 = new Image(new Texture(Gdx.files.internal("time/3pm.png")));
    private final Image pm4 = new Image(new Texture(Gdx.files.internal("time/4pm.png")));
    private final Image pm5 = new Image(new Texture(Gdx.files.internal("time/5pm.png")));
    private final Image pm6 = new Image(new Texture(Gdx.files.internal("time/6pm.png")));

    public GameScreen(Engine engine) {
        super(engine, "office.png");
        this.engine = engine;

        ld.setSize(1000, 1000 * (h/w));
        rd.setSize(1000, 1000 * (h/w));
        lightL.setSize(1000, 1000 * (h/w));
        lightR.setSize(1000, 1000 * (h/w));

        cam1.setSize(1000, 1000 * (h/w));
        cam2.setSize(1000, 1000 * (h/w));
        cam3.setSize(1000, 1000 * (h/w));

        pm12.setPosition( 900, 900 * (h/w));
        pm1.setPosition( 900, 950 * (h/w));
        pm2.setPosition( 900, 950 * (h/w));
        pm3.setPosition( 900, 950 * (h/w));
        pm4.setPosition( 900, 950 * (h/w));
        pm5.setPosition( 900, 950 * (h/w));
        pm6.setPosition( 900, 950 * (h/w));
        pm12.setSize( 100, 100 * (h/w));
        pm1.setSize( 100, 50 * (h/w));
        pm2.setSize( 100, 50 * (h/w));
        pm3.setSize( 100, 50 * (h/w));
        pm4.setSize( 100, 50 * (h/w));
        pm5.setSize( 100, 50 * (h/w));
        pm6.setSize( 100, 50 * (h/w));

        stage.addActor(pm12);

        Music music = Gdx.audio.newMusic(Gdx.files.internal("amb.mp3"));
        music.setVolume(0.2f);
        music.play();
        music.setLooping(true);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        time += Gdx.graphics.getDeltaTime();
        if (time > 60) {
            time -= 60;
            t++;
            setTime();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) leftDoor();
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) rightDoor();
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) lightLeft();
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) lightRight();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) camera(1);
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) camera(1);
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) camera(2);
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) camera(3);
    }

    private void setTime() {
        switch (t) {
            case 1:
                stage.addAction(Actions.removeActor(pm12));
                stage.addActor(pm1);
                break;
            case 2:
                stage.addAction(Actions.removeActor(pm1));
                stage.addActor(pm2);
                break;
            case 3:
                stage.addAction(Actions.removeActor(pm2));
                stage.addActor(pm3);
                break;
            case 4:
                stage.addAction(Actions.removeActor(pm3));
                stage.addActor(pm4);
                break;
            case 5:
                stage.addAction(Actions.removeActor(pm4));
                stage.addActor(pm5);
                break;
            case 6:
                stage.addAction(Actions.removeActor(pm5));
                stage.addActor(pm6);
                break;
        }
    }

    protected void camera(int num) {
        if (isCamOpened) {
            closeCameras();
            isCamOpened = false;
        } else {
            if (num == 1) stage.addActor(cam1);
            if (num == 2) stage.addActor(cam2);
            if (num == 3) stage.addActor(cam3);
            isCamOpened = true;
        }
    }

    protected void closeCameras() {
        stage.addAction(Actions.removeActor(cam1));
        stage.addAction(Actions.removeActor(cam2));
        stage.addAction(Actions.removeActor(cam3));
    }

    protected void leftDoor() {
        Gdx.audio.newMusic(Gdx.files.internal("door.mp3")).play();
        if (!isLightLeft) {
            stage.addAction(Actions.removeActor(lightL));
            isLightLeft = !isLightLeft;
        }
        if (isLDOpened) stage.addActor(ld);
        else stage.addAction(Actions.removeActor(ld));
        isLDOpened = !isLDOpened;
    }

    protected void rightDoor() {
        Gdx.audio.newMusic(Gdx.files.internal("door.mp3")).play();
        if (!isLightRight) {
            stage.addAction(Actions.removeActor(lightR));
            isLightRight = !isLightRight;
        }
        if (isRdOpened) stage.addActor(rd);
        else stage.addAction(Actions.removeActor(rd));
        isRdOpened = !isRdOpened;
    }

    protected void lightLeft() {
        Gdx.audio.newMusic(Gdx.files.internal("light.mp3")).play();
        if (isLightLeft) stage.addActor(lightL);
        else stage.addAction(Actions.removeActor(lightL));
        isLightLeft = !isLightLeft;
    }

    protected void lightRight() {
        Gdx.audio.newMusic(Gdx.files.internal("light.mp3")).play();
        if (isLightRight) stage.addActor(lightR);
        else stage.addAction(Actions.removeActor(lightR));
        isLightRight = !isLightRight;
    }

    protected void springTrapScream() {
        Gdx.audio.newMusic(Gdx.files.internal("scream.mp3")).play();
        Image image = new Image(new Texture(Gdx.files.internal("sp.png")));
        image.setSize(1000, 1000 * (h/w));
        stage.addActor(image);
    }
}