package taipan.domain;

class Pair extends Play
{
    /**
     * The constructor for a Pair.
     *
     * @param newCards {Card[]} - An ArrayList of Cards.
     * @param newOwner {Player} - The Player who played this Pair.
     */
    Pair(final CardCollection newCards, final Player newOwner)
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
    static boolean isPair(final CardCollection cardsToCheck)
    {
        return cardsToCheck.size() == 2 &&
            cardsToCheck.areCardsOfEqualRank();
    }
}
