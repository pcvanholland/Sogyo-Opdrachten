package taipan.domain;

final class Bomb extends Play
{
    /**
     * Constructor for a Bomb.
     *
     * @param newCards {Card[]} - An ArrayList of Cards comprising the new Bomb.
     * @param newOwner {Player} - The Player who played this Bomb.
     */
    Bomb(final CardCollection newCards, final Player newOwner) throws
        InvalidPlayException
    {
        super(newCards, newOwner);
        if (!isBomb(newCards))
        {
            throw new InvalidBombException();
        }
    }

    /**
     * Test whether this Play beats the given Play.
     *
     * @param playToBeat {Play} - The Play to beat.
     * @return {boolean} - Whether this Play is higher in rank than the given.
     */
    @Override
    boolean beats(final Play playToBeat)
    {
        return this.getClass() != playToBeat.getClass() ||
            this.beatsBomb((Bomb) playToBeat);
    }

    /**
     * Test whether this Bomb beats the given Bomb.
     *
     * @param bombToBeat {Bomb} - The Bomb to beat.
     * @return {boolean} - Whether this Bomb is higher in rank than the given.
     */
    private boolean beatsBomb(final Bomb bombToBeat)
    {
        return this.getLength() > bombToBeat.getLength() ||
            this.getLength() == bombToBeat.getLength() &&
            this.getValue() > bombToBeat.getValue();
    }

    /**
     * @param cardsToCheck - An ArrayList of Cards to check.
     * @return {boolean} - Whether this collection of Cards
      *                     can be played as a Bomb.
     */
    static boolean isBomb(final CardCollection cardsToCheck)
    {
        return isFOAKBomb(cardsToCheck) ||
            isStraightBomb(cardsToCheck);
    }

    /**
     * @param cardsToCheck - An ArrayList of Cards to check.
     * @return {boolean} - Whether this collection of Cards is a FOAK bomb.
     */
    private static boolean isFOAKBomb(final CardCollection cardsToCheck)
    {
        return cardsToCheck.size() == StandardSuit.values().length &&
            cardsToCheck.areCardsOfEqualRank();
    }

    /**
     * @param cardsToCheck - An ArrayList of Cards to check.
     * @return {boolean} - Whether this collection of Cards can be
     *      played as a Straight bomb.
     */
    private static boolean isStraightBomb(final CardCollection cardsToCheck)
    {
        return Straight.isStraight(cardsToCheck) &&
            cardsToCheck.areCardsOfEqualSuit();
    }
}
