package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

public class SpecialCard_Test
{
    @Test
    public void test_init() throws InvalidRankException
    {
        for (SpecialRank r : SpecialRank.values())
        {
            SpecialCard.createSpecialCard(r);
        }
    }

    @Test
    public void test_getRank() throws InvalidRankException
    {
        for (SpecialRank r : SpecialRank.values())
        {
            SpecialCard card = SpecialCard.createSpecialCard(r);
            Assert.assertEquals(r, card.getRank());
        }
    }

    @Test
    public void test_getSuit() throws InvalidRankException
    {
        for (SpecialRank r : SpecialRank.values())
        {
            SpecialCard card = SpecialCard.createSpecialCard(r);
            Assert.assertEquals(SpecialSuit.SPECIAL, card.getSuit());
        }
    }

    @Test
    public void test_getScore() throws InvalidRankException
    {
        for (SpecialRank r : SpecialRank.values())
        {
            SpecialCard card = SpecialCard.createSpecialCard(r);
            Assert.assertEquals(r.getScore(), card.getScore());
        }
    }
}
