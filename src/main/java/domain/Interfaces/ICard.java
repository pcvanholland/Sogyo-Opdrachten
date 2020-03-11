package taipan.domain;

public interface ICard
{
    /**
     * @return {ISuit} - The Suit of this Card.
     */
    ISuit getSuit();

    /**
     * @return {IRank} - The Rank of this Card.
     */
    IRank getRank();
}
