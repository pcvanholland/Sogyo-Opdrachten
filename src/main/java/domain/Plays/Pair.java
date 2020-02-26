package taipan.domain;

import java.util.ArrayList;

class Pair extends Play
{
    /**
     * The constructor for a Pair.
     *
     * @param newCards {Card[]} - An ArrayList of Cards.
     */
    Pair(final ArrayList<Card> newCards)
    {
        super(newCards);
    }

    /**
     * @param cardsToCheck - An ArrayList of Cards to check.
     * @return {boolean} - Whether this collection of Cards is a Pair.
     */
    protected static boolean isPair(final ArrayList<Card> cardsToCheck)
    {
        return cardsToCheck.size() == 2 && areCardsOfEqualRank(cardsToCheck);
    }
}
