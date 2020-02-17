package taipan.domain;

public enum SpecialRank implements IRank
{
    DOG(0), MAHJONG(0), PHOENIX(-25), DRAGON(25);

    private int score;

    SpecialRank(int score)
    {
        this.score = score;
    }

    public int getScore()
    {
        return this.score;
    }
}
