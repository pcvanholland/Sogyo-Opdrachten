package taipan.domain;

import java.util.ArrayList;

class Triple extends Play
{
    /**
     * The constructor for a Triple.
     *
     * @param newCards {Card[]} - An ArrayList of Cards.
     */
    Triple(final ArrayList<Card> newCards)
    {
        super(newCards);
    }

    /**
     * @param cardsToCheck - An ArrayList of cards to check.
     * @return {boolean} - Whether this collection of Cards is a Triple.
     */
    protected static boolean isTriple(final ArrayList<Card> cardsToCheck)
    {
        return cardsToCheck.size() == 3 && areCardsOfEqualRank(cardsToCheck);
    }
}
