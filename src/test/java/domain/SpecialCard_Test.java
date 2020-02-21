package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

public class SpecialCard_Test
{
    @Test
    public void test_init()
    {
        for (SpecialRank r : SpecialRank.values())
        {
            new SpecialCard(r);
        }
    }

    @Test
    public void test_getRank()
    {
        for (SpecialRank r : SpecialRank.values())
        {
            SpecialCard card = new SpecialCard(r);
            Assert.assertEquals(r, card.getRank());
        }
    }

    @Test
    public void test_getScore()
    {
        for (SpecialRank r : SpecialRank.values())
        {
            SpecialCard card = new SpecialCard(r);
            Assert.assertEquals(r.getScore(), card.getScore());
        }
    }
}
