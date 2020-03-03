package taipan.domain;

import java.util.ArrayList;

class Pair extends Play
{
    /**
     * The constructor for a Pair.
     *
     * @param newCards {Card[]} - An ArrayList of Cards.
     * @param newOwner {Player} - The Player who played this Pair.
     */
    Pair(final ArrayList<Card> newCards, final Player newOwner)
    {
        super(newCards, newOwner);
        if (!isPair(newCards))
        {
            throw new InvalidPairException();
        }
    }

    /**
     * @param cardsToCheck - An ArrayList of Cards to check.
     * @return {boolean} - Whether this collection of Cards is a Pair.
     */
    protected static boolean isPair(final ArrayList<Card> cardsToCheck)
    {
        return cardsToCheck.size() == 2 &&
            PlayHelper.areCardsOfEqualRank(cardsToCheck);
    }
}
