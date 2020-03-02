package taipan.domain;

import java.util.ArrayList;

class Bomb extends Play
{
    /**
     * Constructor for a Bomb.
     *
     * @param newCards {Card[]} - An ArrayList of Cards comprising the new Bomb.
     */
    Bomb(final ArrayList<Card> newCards)
    {
        super(newCards);
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
    protected boolean beats(final Play playToBeat)
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
    protected static boolean isBomb(final ArrayList<Card> cardsToCheck)
    {
        return isFOAKBomb(cardsToCheck) ||
            isStraightBomb(cardsToCheck);
    }

    /**
     * @param cardsToCheck - An ArrayList of Cards to check.
     * @return {boolean} - Whether this collection of Cards is a FOAK bomb.
     */
    private static boolean isFOAKBomb(final ArrayList<Card> cardsToCheck)
    {
        // for (Card card : cardsToCheck)
        // {
        //     if (card.getRank() instanceof SpecialRank)
        //     {
        //         return false;
        //     }
        // }
        return cardsToCheck.size() == StandardSuit.values().length &&
            PlayHelper.areCardsOfEqualRank(cardsToCheck);
    }

    /**
     * @param cardsToCheck - An ArrayList of Cards to check.
     * @return {boolean} - Whether this collection of Cards can be
     *      played as a Straight bomb.
     */
    private static boolean isStraightBomb(final ArrayList<Card> cardsToCheck)
    {
        return Straight.isStraight(cardsToCheck) &&
            PlayHelper.areCardsOfEqualSuit(cardsToCheck);
    }
}
