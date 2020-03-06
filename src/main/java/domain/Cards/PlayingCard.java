package taipan.domain;

class PlayingCard extends Card
{
    /**
     * Constructor.
     *
     * @param newSuit {StandardSuit} - The Suit this Card ought to have.
     * @param newRank {StandardRank} - The Rank this Card ought to have.
     */
    PlayingCard(final StandardSuit newSuit, final StandardRank newRank)
    {
        super(newSuit, newRank);
    }
}
