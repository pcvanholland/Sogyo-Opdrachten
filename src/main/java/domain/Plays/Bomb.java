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
    }

    /**
     * Test whether this play beats the given play.
     *
     * @param playToBeat {Play} - The play to beat.
     * @return {boolean} - Whether this play is higher in rank than the given.
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
     * @param cardsToCheck - An ArrayList of cards to check.
     * @return {boolean} - Whether this collection of Cards can be played as a bomb.
     */
    protected static boolean isBomb(final ArrayList<Card> cardsToCheck)
    {
        return isFOAKBomb(cardsToCheck) ||
            isStraightBomb(cardsToCheck);
    }

    /**
     * @param cardsToCheck - An ArrayList of cards to check.
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
        return cardsToCheck.size() == 4 && areCardsOfEqualRank(cardsToCheck);
    }

    /**
     * @param cardsToCheck - An ArrayList of cards to check.
     * @return {boolean} - Whether this collection of Cards can be
     *      played as a straight bomb.
     */
    private static boolean isStraightBomb(final ArrayList<Card> cardsToCheck)
    {
        return Straight.isStraight(cardsToCheck) &&
            areCardsOfEqualSuit(cardsToCheck);
    }
}
