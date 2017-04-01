package net.siekiera.soundmemory;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Wociech Siekiera on 01.04.2017.
 */

public class SoundMemoryApplication extends Application {
    private ArrayList<Card> cards = new ArrayList<Card>();

    @Override
    public void onCreate() {
        super.onCreate();
        fillCardsArray(cards);
    }

    public void fillCardsArray(ArrayList<Card> cardArrayList) {
        ArrayList<Integer> obverses = new ArrayList<Integer>();
        obverses.add(R.drawable.a);
        obverses.add(R.drawable.b);
        obverses.add(R.drawable.c);
        obverses.add(R.drawable.d);
        obverses.add(R.drawable.e);
        obverses.add(R.drawable.f);
        for (Integer obverse : obverses) {
            cardArrayList.add(new Card(obverse));
            cardArrayList.add(new Card(obverse));
        }
    }
}
