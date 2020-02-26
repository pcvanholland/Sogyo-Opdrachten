package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

public class PlayingCard_Test
{
    @Test
    public void test_init()
    {
        for (StandardSuit s : StandardSuit.values())
        {
            for (StandardRank r : StandardRank.values())
            {
                new PlayingCard(s, r);
            }
        }
    }

    @Test
    public void test_getRank()
    {
        PlayingCard card = new PlayingCard(
            StandardSuit.SWORD, StandardRank.TWO
        );
        Assert.assertEquals(StandardRank.TWO, card.getRank());
    }

    @Test
    public void test_getSuit()
    {
        PlayingCard card = new PlayingCard(
            StandardSuit.SWORD, StandardRank.TWO
        );
        Assert.assertEquals(StandardSuit.SWORD, card.getSuit());
    }

    @Test
    public void test_getScore()
    {
        for (StandardRank r : StandardRank.values())
        {
            PlayingCard card = new PlayingCard(StandardSuit.SWORD, r);
            Assert.assertEquals(r.getScore(), card.getScore());
        }
    }

    @Test
    public void test_getValue()
    {
        int i = 2;
        for (StandardRank r : StandardRank.values())
        {
            PlayingCard card = new PlayingCard(StandardSuit.SWORD, r);
            Assert.assertEquals(i++, card.getValue());
        }
    }
}
