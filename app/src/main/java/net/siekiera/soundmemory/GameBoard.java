package net.siekiera.soundmemory;

import android.app.Application;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

public class GameBoard extends AppCompatActivity {
    SoundMemoryApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        application = (SoundMemoryApplication) this.getApplication();
        application.getGameState().setGameBoard(this);
        drawCardsFromGameStateObject();
    }

    public void drawCardsFromGameStateObject() {
        Map<Integer, Card> cards = application.getGameState().getCards();
        for (Map.Entry<Integer, Card> singleCard : cards.entrySet()) {
            ImageView imageView = (ImageView) findViewById(singleCard.getKey());
            imageView.setImageResource(singleCard.getValue().getVisibleImage());
            singleCard.getValue().setViewId(imageView.getId());
        }
    }

    public void cardClick(View view) {
        application.getGameState().cardClicked(view);
    }

}
