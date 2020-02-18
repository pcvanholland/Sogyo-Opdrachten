package taipan.domain;

/**
 * This is the abstract class representing all cards.
 */
abstract class Card
{
    IRank rank;

    /**
     * Constructor.
     *
     * @param newRank {IRank} - The rank the new card ought to have.
     */
    Card(final IRank newRank)
    {
        this.rank = newRank;
    }

    /**
     * @return {IRank} - The rank of this card.
     */
    protected IRank getRank()
    {
        return this.rank;
    }

    /**
     * @return {int} - The scoring value of this card.
     */
    protected int getScore()
    {
        return this.rank.getScore();
    }
}
