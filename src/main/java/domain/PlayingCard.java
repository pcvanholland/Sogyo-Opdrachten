package taipan.domain;

class PlayingCard extends Card
{
    private Suit suit;

    /**
     * Constructor.
     *
     * @param newSuit {Suit} - The suit this card ought to have.
     * @param newRank {StandardRank} - The rank this card ought to have.
     */
    PlayingCard(final Suit newSuit, final StandardRank newRank)
    {
        super(newRank);
        this.suit = newSuit;
    }

    /**
     * @return {Suit} - The suit of this card.
     */
    protected Suit getSuit()
    {
        return this.suit;
    }

    /**
     * Returns the value of this playing card.
     * This is used to determine a street.
     *
     * @return {int} - The value of this card.
     */
    protected int getValue()
    {
        return ((StandardRank) getRank()).getValue();
    }
}
