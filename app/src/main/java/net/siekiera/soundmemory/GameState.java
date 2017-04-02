package net.siekiera.soundmemory;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Wojciech Siekiera on 01.04.2017.
 */

public class GameState {
    private Card firstCardSelected;
    private Card secondCardSelected;
    private Context context;
    private Map<Integer, Card> cards = new HashMap<Integer, Card>();
    private MediaPlayer mp;
    private GameBoard gameBoard;

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameState(Context context) {
        this.context = context;
    }

    public void fillCards(Map<Integer, Card> cardArrayMap) {
        ArrayList<Integer> obverses = new ArrayList<Integer>();
        ArrayList<Integer> sounds = new ArrayList<Integer>();
        int i = 1;
        int j = 0;
        obverses.add(R.drawable.a);
        obverses.add(R.drawable.b);
        obverses.add(R.drawable.c);
        obverses.add(R.drawable.d);
        obverses.add(R.drawable.e);
        obverses.add(R.drawable.f);
        sounds.add(R.raw.arrow);
        sounds.add(R.raw.ges);
        sounds.add(R.raw.kaczka);
        sounds.add(R.raw.robot);
        sounds.add(R.raw.lever);
        sounds.add(R.raw.sms);
        for (Integer obverse : obverses) {
            int resid1 = context.getResources().getIdentifier("card" + i, "id", context.getPackageName());
            int resid2 = context.getResources().getIdentifier("card" + (i + 1), "id", context.getPackageName());
            cardArrayMap.put(resid1, new Card(obverse, sounds.get(j)));
            cardArrayMap.put(resid2, new Card(obverse, sounds.get(j)));
            i += 2;
            j++;
        }
    }

    public Map<Integer, Card> getCards() {
        return cards;
    }

    public Card getCardById(int id) {
        return cards.get(id);
    }

    public void initialize() {
        fillCards(cards);
        cards = shuffleCards(cards);
    }

    public void cardClicked(View view) {
        ImageView imageView = (ImageView) view;
        Card card = getCardById(view.getId());
        if (card.isGuessed() || !card.isReversed() || card.isSelected()) {
            return;
        }
        if (noCardsSelected()) {
            deselectAllCards();
            firstCardSelected = card;
            firstCardSelected.setSelected(true);
            playSoundFromCard(card);
        } else if (oneCardSelected()) {
            secondCardSelected = card;
            secondCardSelected.setSelected(true);
            if (firstCardSelected.getObverse() == secondCardSelected.getObverse()) {
                playSoundFromCardAndShowCards(card);

            } else {
                playSoundFromCardAndDeselectAll(card);
                secondCardSelected.setSelected(true);
                firstCardSelected = null;
                secondCardSelected = null;
            }
        }
        gameBoard.drawCardsFromGameStateObject();
    }

    private void deselectAllCards() {
        for (Map.Entry<Integer, Card> mapEntry : cards.entrySet()) {
            Card card = mapEntry.getValue();
            card.setSelected(false);
        }
    }

    public boolean noCardsSelected() {
        return (firstCardSelected == null && secondCardSelected == null);
    }

    public boolean oneCardSelected() {
        return (firstCardSelected != null && secondCardSelected == null);
    }

    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    public void playSoundFromCard(Card card) {
        stopPlaying();
        mp = MediaPlayer.create(context, card.getSound());
        mp.start();
    }

    public void playSoundFromCardAndDeselectAll(Card card) {
        stopPlaying();
        mp = MediaPlayer.create(context, card.getSound());
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                deselectAllCards();
                gameBoard.drawCardsFromGameStateObject();
            }
        });
    }

    private void playSoundFromCardAndShowCards(Card card) {
        stopPlaying();
        mp = MediaPlayer.create(context, card.getSound());
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                firstCardSelected.flip();
                secondCardSelected.flip();
                firstCardSelected.setSelected(false);
                secondCardSelected.setSelected(false);
                firstCardSelected = null;
                secondCardSelected = null;
                gameBoard.drawCardsFromGameStateObject();
            }
        });
    }

    public Map<Integer, Card> shuffleCards(Map<Integer, Card> cards) {
        Map<Integer, Card> shuffledMap = new HashMap<Integer, Card>();
        List<Integer> keys = new ArrayList(cards.keySet());
        List<Card> values = new ArrayList(cards.values());
        Collections.shuffle(keys);
        Collections.shuffle(values);
        int i = 0;
        for (Integer key : keys) {
            shuffledMap.put(key, values.get(i));
            i++;
        }
        return shuffledMap;
    }
}
