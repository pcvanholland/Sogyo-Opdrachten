package taipan.domain;

import java.util.ArrayList;

class Triple extends Play
{
    /**
     * The constructor for a Triple.
     *
     * @param newCards {Card[]} - An ArrayList of Cards.
     * @param newOwner {Player} - The Player who played this Triple.
     */
    Triple(final ArrayList<Card> newCards, final Player newOwner)
    {
        super(newCards, newOwner);
        if (!isTriple(newCards))
        {
            throw new InvalidTripleException();
        }
    }

    /**
     * @param cardsToCheck - An ArrayList of Cards to check.
     * @return {boolean} - Whether this collection of Cards is a Triple.
     */
    protected static boolean isTriple(final ArrayList<Card> cardsToCheck)
    {
        return cardsToCheck.size() == 3 &&
            PlayHelper.areCardsOfEqualRank(cardsToCheck);
    }
}
