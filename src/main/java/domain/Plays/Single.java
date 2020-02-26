package taipan.domain;

import java.util.ArrayList;

class Single extends Play
{
    /**
     * The constructor for a Single.
     *
     * @param newCards {Card[]} - ArrayList containing the Card of the Single.
     */
    Single(final ArrayList<Card> newCards)
    {
        super(newCards);
    }

    /**
     * @param cardsToCheck - An ArrayList of Cards to check.
     * @return {boolean} - Whether this collection of Cards is a Single.
     */
    protected static boolean isSingle(final ArrayList<Card> cardsToCheck)
    {
        return cardsToCheck.size() == 1;
    }
}
