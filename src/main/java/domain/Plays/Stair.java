package taipan.domain;

import java.util.ArrayList;

class Stair extends Play
{
    /**
     * The constructor for a Stair.
     *
     * @param newCards {Card[]} - An ArrayList of Cards.
     */
    Stair(final ArrayList<Card> newCards)
    {
        super(newCards);
    }

    /**
     * @param cardsToCheck - An ArrayList of Cards to check.
     * @return {boolean} - Whether this collection of Cards is a Triple.
     */
    protected static boolean isStair(final ArrayList<Card> cardsToCheck)
    {
        // An odd-sized array is never a valid Stair.
        if (cardsToCheck.size() % 2 != 0)
        {
            return false;
        }

        return areCardsOnlyPairs(cardsToCheck) &&
            arePairsOfCardsSequential(cardsToCheck);
    }

    /**
     * This tests whether the given list of Cards only contains Pairs.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of Cards to verify.
     * @return {boolean} - Whether the list only contains Pairs.
     */
    private static boolean areCardsOnlyPairs(
        final ArrayList<Card> cardsToCheck
    )
    {
         return containsOnlyNumberOfEqualRanks(cardsToCheck, 2);
    }

    /**
     * This tests whether the given list of Pairs is sequential.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of Pairs to verify.
     * @return {boolean} - Whether the Pairs are consecutive.
     */
    private static boolean arePairsOfCardsSequential(
        final ArrayList<Card> cardsToCheck
    )
    {
        ArrayList<Card> uniquelyRankedCards = new ArrayList<Card>();
        java.util.HashSet<IRank> uniqueRanks = new java.util.HashSet<IRank>();
        for (Card card : cardsToCheck)
        {
            if (uniqueRanks.add(card.getRank()))
            {
                uniquelyRankedCards.add(card);
            }
        }
        return areCardsSequential(uniquelyRankedCards);
    }
}
