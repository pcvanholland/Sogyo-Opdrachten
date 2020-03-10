package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Single_Test extends Play_Test_Helper
{
    @Test
    public void test_playValiditySingleCard()
    {
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.SINGLE);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_init()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));

        new Single(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_initFailsWhenWrong()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));

        new Single(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidSingleException.class)
    public void test_initFailsWhenTriedWithDifferentType()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        new Single(cards, TEST_PLAYER);
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
