package taipan.domain;

public enum StandardRank implements IRank
{
    TWO(2, 0), THREE(3, 0), FOUR(4, 0), FIVE(5, 5),
    SIX(6, 0), SEVEN(7, 0), EIGHT(8, 0), NINE(9, 0), TEN(10, 10),
    JACK(11, 0), QUEEN(12, 0), KING(13, 10), ACE(14, 0);

    private int value;
    private int score;

    StandardRank(int value, int score)
    {
        this.value = value;
        this.score = score;
    }

    public int getScore()
    {
        return this.score;
    }

    protected int getValue()
    {
        return this.value;
    }
}
