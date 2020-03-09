package taipan.domain;

abstract class SpecialCard extends Card
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

    /**
     * Create Special Cards from given input.
     *
     * @param rank {SpecialRank} - The Rank the new Card ought to have.
     * @return {SpecialCard} - The constructed Card.
     */
    protected static SpecialCard createSpecialCard(final SpecialRank rank)
        throws InvalidRankException
    {
        if (rank == SpecialRank.DOG)
        {
            return new Dog();
        }
        if (rank == SpecialRank.MAHJONG)
        {
            return new MahJong();
        }
        if (rank == SpecialRank.DRAGON)
        {
            return new Dragon();
        }
        if (rank == SpecialRank.PHOENIX)
        {
            return new Phoenix();
        }
        // We can't arrive here.
        throw new InvalidRankException();
    }
}
