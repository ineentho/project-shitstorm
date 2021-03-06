package com.ineentho.shitstorm.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ineentho.shitstorm.ProjectShitstorm;
import com.ineentho.shitstorm.screen.GameScreen;

public class DebugGUI {
    private static DebugGUI instance;
    private boolean debugMenu = true;
    private boolean physicsDebug = true;
    private boolean muteAudio;
    private OnAction actionListener;

    public DebugGUI() {
        instance = this;
    }

    public static DebugGUI getInstance() {
        return instance;
    }

    public boolean isAudioMuted() {
        return muteAudio;
    }

    public boolean isPhysicsDebugEnabled() {
        return physicsDebug;
    }

    public boolean isDebugMenuEnabled() {
        return debugMenu;
    }

    public void draw() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F3))
            debugMenu = !debugMenu;

        if (Gdx.input.isKeyJustPressed(Input.Keys.P))
            physicsDebug = !physicsDebug;

        if (Gdx.input.isKeyJustPressed(Input.Keys.M))
            muteAudio = !muteAudio;

        if (Gdx.input.isKeyJustPressed(Input.Keys.K))
            actionListener.onKillAll();

        if (Gdx.input.isKeyJustPressed(Input.Keys.R))
            actionListener.onRestart();

        if (!debugMenu)
            return;

        BitmapFont font = ProjectShitstorm.getInstance().getFont("size12");
        int margin = 10;
        StringBuilder sb = new StringBuilder();
        sb.append("[F3] Toggle debug menu [" + onOff(debugMenu) + "]\n");
        sb.append("[P] Toggle physic body outlines [" + onOff(physicsDebug) + "]\n");
        sb.append("[M] Mute audio [" + onOff(muteAudio) + "]\n");
        sb.append("[K] Kill all enemies\n");
        sb.append("[R] Restart game\n");
        sb.append("FPS: " + Gdx.graphics.getFramesPerSecond());

        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        font.draw(batch, sb.toString(), margin, Gdx.graphics.getHeight() - margin);
        batch.end();
    }

    private String onOff(boolean bool) {
        return bool ? "On" : "Off";
    }

    public void setListener(OnAction listener) {
        this.actionListener = listener;
    }

    public interface OnAction {
        void onKillAll();
        void onRestart();
    }
}
