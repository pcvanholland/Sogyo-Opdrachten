package taipan.domain;

class Straight extends Play
{
    private static final int MIN_STRAIGHT_LENGTH = 5;

    /**
     * The default constructor for a Straight.
     *
     * @param newCards {Card[]} - An ArrayList of Cards.
     * @param newOwner {Player} - The Player who played this Straight.
     */
    Straight(final CardCollection newCards, final Player newOwner)
    {
        super(newCards, newOwner);
        if (!isStraight(newCards))
        {
            throw new InvalidStraightException();
        }
    }

    /**
     * @param cardsToCheck - An ArrayList of Cards to check.
     * @return {boolean} - Whether this collection of Cards is a Straight.
     */
    protected static boolean isStraight(final CardCollection cardsToCheck)
    {
        return cardsToCheck.size() >= MIN_STRAIGHT_LENGTH &&
            cardsToCheck.areCardsSequential();
    }
}
