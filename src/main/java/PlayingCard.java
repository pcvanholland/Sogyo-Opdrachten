package taipan.domain;

class PlayingCard extends Card
{
    private Suit suit;
    private int value;

    /**
     * Constructor.
     *
     * @param {Suit} suit - The suit this card ought to have.
     * @param {Rank} rank - The rank this card ought to have.
     */
    PlayingCard(Suit suit, StandardRank rank)
    {
        super(rank);
        this.suit = suit;
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
        return ((StandardRank) this.rank).getValue();
    }
}
