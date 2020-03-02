package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Single_Test extends Play_Test
{
    @Test
    public void test_playValiditySingleCard()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.SINGLE);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));

        new Single(cards);
    }

    @Test
    public void test_higherBeatsLower()
    {
        Play firstPlay = createSingle(2);
        Play secondPlay = createSingle(3);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        Play firstPlay = createSingle(2);
        Play secondPlay = createSingle(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        Play firstPlay = createSingle(3);
        Play secondPlay = createSingle(3);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_singleNotBeatDouble()
    {
        Play firstPlay = createSingle(3);
        Play secondPlay = createPair(2);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
