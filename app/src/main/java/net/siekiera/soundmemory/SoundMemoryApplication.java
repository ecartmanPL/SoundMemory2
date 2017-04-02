package net.siekiera.soundmemory;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wociech Siekiera on 01.04.2017.
 */

public class SoundMemoryApplication extends Application {
    private GameState gameState;

    @Override
    public void onCreate() {
        gameState = new GameState(this);
        super.onCreate();
        gameState.initialize();
    }

    public GameState getGameState() {
        return gameState;
    }
}
