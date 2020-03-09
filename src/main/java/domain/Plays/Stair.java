package taipan.domain;

class Stair extends Play
{
    /**
     * The constructor for a Stair.
     *
     * @param newCards {Card[]} - An ArrayList of Cards.
     * @param newOwner {Player} - The Player who played this Stair.
     */
    Stair(final CardCollection newCards, final Player newOwner)
    {
        super(newCards, newOwner);
        if (!isStair(newCards))
        {
            throw new InvalidStairException();
        }
    }

    /**
     * @param cardsToCheck - An ArrayList of Cards to check.
     * @return {boolean} - Whether this collection of Cards is a Triple.
     */
    protected static boolean isStair(final CardCollection cardsToCheck)
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
        final CardCollection cardsToCheck
    )
    {
         return cardsToCheck.containsOnlyNumberOfEqualRanks(2);
    }

    /**
     * This tests whether the given list of Pairs is sequential.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of Pairs to verify.
     * @return {boolean} - Whether the Pairs are consecutive.
     */
    private static boolean arePairsOfCardsSequential(
        final CardCollection cardsToCheck
    )
    {
        CardCollection uniquelyRankedCards = new CardCollection();
        java.util.HashSet<Integer> uniqueRanks =
            new java.util.HashSet<Integer>();
        for (Card card : cardsToCheck.getCards())
        {
            if (!(card instanceof Phoenix) &&
                uniqueRanks.add(card.getValue())
            )
            {
                uniquelyRankedCards.add(card);
            }
        }
        return uniquelyRankedCards.areCardsSequential();
    }
}
