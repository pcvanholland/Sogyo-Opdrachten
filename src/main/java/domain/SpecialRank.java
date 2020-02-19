package taipan.domain;

/**
 * The ranks that the special cards can have.
 */
public enum SpecialRank implements IRank
{
    DOG(0), MAHJONG(0), PHOENIX(-25), DRAGON(25);

    private int score;

    SpecialRank(final int newScore)
    {
        this.score = newScore;
    }

    /**
     * @return {int} - The score associated with this rank.
     */
    public int getScore()
    {
        return this.score;
    }
}
