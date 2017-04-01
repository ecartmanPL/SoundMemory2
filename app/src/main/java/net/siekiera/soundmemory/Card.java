package net.siekiera.soundmemory;

/**
 * Created by Wojciech Siekiera on 01.04.2017.
 */

public class Card {

    private final int REVERSE = R.drawable.rewers;
    private int obverse; // awers
    private int reverse; //rewers
    private boolean reversed = true; // by default initialize every card as reveresd (obverse is hidden)

    public Card(int obverse) {
        this.obverse = obverse;
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
            return REVERSE;
        } else {
            return this.getObverse();
        }
    }
}
