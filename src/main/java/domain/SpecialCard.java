package taipan.domain;

class SpecialCard extends Card
{
    /**
     * Constructor of a Special Card.
     *
     * @param newRank {Rank} - The Rank this Card ought to have.
     */
    SpecialCard(final SpecialRank newRank)
    {
        super(SpecialSuit.SPECIAL, newRank);
    }
}
