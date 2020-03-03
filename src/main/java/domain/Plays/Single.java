package taipan.domain;

import java.util.ArrayList;

class Single extends Play
{
    /**
     * The constructor for a Single.
     *
     * @param newCards {Card[]} - ArrayList containing the Card of the Single.
     * @param newOwner {Player} - The Player who played this Single.
     */
    Single(final ArrayList<Card> newCards, final Player newOwner)
    {
        super(newCards, newOwner);
        if (!isSingle(newCards))
        {
            throw new InvalidSingleException();
        }
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
