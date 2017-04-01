package net.siekiera.soundmemory;

import android.app.Application;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class GameBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        ImageView imageView = (ImageView)findViewById(R.id.card1);
        SoundMemoryApplication application = (SoundMemoryApplication)this.getApplication();
        //imageView.setImageResource(application.card.getVisibleImage());
    }

    public void cardClick(View view) {
        ImageView imageView = (ImageView) view;
        SoundMemoryApplication application = (SoundMemoryApplication)this.getApplication();
        //application.card.flip();
        //imageView.setImageResource(application.card.getVisibleImage());
    }

}
