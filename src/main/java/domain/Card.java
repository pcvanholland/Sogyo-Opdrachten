package taipan.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This is the abstract class representing all Cards.
 */
abstract class Card
{
    private ISuit suit;
    private IRank rank;

    /**
     * Constructor.
     *
     * @param newRank {IRank} - The Rank the new Card ought to have.
     * @param newSuit {ISuit} - The Suit the new Card ought to have.
     */
    Card(final ISuit newSuit, final IRank newRank)
    {
        this.suit = newSuit;
        this.rank = newRank;
    }

    /**
     * @return {ISuit} - The Suit of this Card.
     */
    protected final ISuit getSuit()
    {
        return this.suit;
    }

    /**
     * @return {IRank} - The Rank of this Card.
     */
    protected final IRank getRank()
    {
        return this.rank;
    }

    /**
     * @return {int} - The scoring value of this Card.
     */
    protected final int getScore()
    {
        return this.getRank().getScore();
    }

    /**
     * Returns the value of this Playing Card.
     * This is used to determine a Straight.
     *
     * @return {int} - The value of this Card.
     */
    protected int getValue()
    {
        return this.getRank().getValue();
    }

    /**
     * @param card {Card} - The Card to check this Card against.
     * @return {boolean} - Whether this Card has a Rank
     *                  equal to the reference Card.
     */
    protected final boolean hasEqualRank(final Card card)
    {
        return this.getValue() == card.getValue();
    }
}
