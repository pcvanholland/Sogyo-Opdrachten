package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Dragon_Test extends Play_Test_Helper
{
    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.DRAGON));

        new Single(cards);
    }

    @Test
    public void test_playValiditySingle()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.DRAGON));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.SINGLE);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_dragonBeatsAllSingles()
    {
        Play firstPlay;
        Play secondPlay = createDragon();
        for (int i = 2; i < StandardRank.values().length + 2; ++i)
        {
            firstPlay = createSingle(i);
            Assert.assertTrue(secondPlay.beats(firstPlay));
        }
    }

    @Test
    public void test_dragonNotBeatenByAnySingle()
    {
        Play firstPlay = createDragon();
        Play secondPlay;
        for (int i = 2; i < StandardRank.values().length + 2; ++i)
        {
            secondPlay = createSingle(i);
            Assert.assertFalse(secondPlay.beats(firstPlay));
        }
    }
}
