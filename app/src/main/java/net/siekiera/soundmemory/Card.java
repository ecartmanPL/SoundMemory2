package net.siekiera.soundmemory;

/**
 * Created by Wojciech Siekiera on 01.04.2017.
 */

public class Card {

    private final int REVERSE = R.drawable.rewers;
    private final int SELECTED = R.drawable.selected;
    private int obverse; // obrazek awers
    private int reverse; //obrazek rewers

    private int sound;
    private boolean reversed = true; // by default initialize every card as reveresd (obverse is hidden)
    private boolean guessed = false;
    private boolean selected = false;
    private int viewId;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public boolean isGuessed() {
        return guessed;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }

    public int getSound() {
        return sound;
    }

    public Card(int obverse, int sound) {
        this.obverse = obverse;
        this.sound = sound;

        this.reverse = REVERSE;
    }

    public int getObverse() {

        return obverse;
    }

    public void setObverse(int obverse) {
        this.obverse = obverse;
    }

    public int getReverse() {
        return reverse;
    }

    public void setReverse(int reverse) {
        this.reverse = reverse;
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    public void flip() {
        this.reversed = !this.reversed;
    }

    public int getVisibleImage() {
        if (isReversed()) {
            if (isSelected()) {
                return SELECTED;
            } else {
                return REVERSE;
            }
        } else {
            return this.getObverse();
        }
    }
}
