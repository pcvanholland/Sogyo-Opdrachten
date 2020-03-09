package taipan.domain;

class Triple extends Play
{
    static final int TRIPLE_SIZE = 3;
    /**
     * The constructor for a Triple.
     *
     * @param newCards {Card[]} - An ArrayList of Cards.
     * @param newOwner {Player} - The Player who played this Triple.
     */
    Triple(final CardCollection newCards, final Player newOwner)
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
    protected static boolean isTriple(final CardCollection cardsToCheck)
    {
        return cardsToCheck.size() == TRIPLE_SIZE &&
            cardsToCheck.areCardsOfEqualRank();
    }
}
