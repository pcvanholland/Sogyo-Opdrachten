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
     * @param {Rank} rank - The rank the new card ought to have.
     */
    Card(IRank rank)
    {
        this.rank = rank;
    }

    /**
     * @return {Rank} - The rank of this card.
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
