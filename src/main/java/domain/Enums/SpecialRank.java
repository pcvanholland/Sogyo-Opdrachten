package taipan.domain;

/**
 * The Ranks that the Special Cards can have.
 */
public enum SpecialRank implements IRank
{
    DOG(-1, 0), MAHJONG(1, 0), PHOENIX(-1, -25), DRAGON(16, 25);

    private int value;
    private int score;

    SpecialRank(final int newValue, final int newScore)
    {
        this.value = newValue;
        this.score = newScore;
    }

    /**
     * @return {int} - The score associated with this Rank.
     */
    public int getScore()
    {
        return this.score;
    }

    /**
     * Gets the value of a Rank, this can be used for checking the
     * order of multiple cards.
     *
     * @return {int} - The value associated with a Rank.
     */
    public int getValue()
    {
        return this.value;
    }
}
