package taipan.domain;

import java.util.ArrayList;

class Straight extends Play
{
    /**
     * The default constructor for a Straight.
     *
     * @param newCards {Card[]} - An ArrayList of Cards.
     */
    Straight(final ArrayList<Card> newCards)
    {
        super(newCards);
    }

    /**
     * @param cardsToCheck - An ArrayList of cards to check.
     * @return {boolean} - Whether this collection of Cards is a Straight.
     */
    protected static boolean isStraight(final ArrayList<Card> cardsToCheck)
    {
        return cardsToCheck.size() >= 5 &&
            areCardsSequential(cardsToCheck);
    }
}
