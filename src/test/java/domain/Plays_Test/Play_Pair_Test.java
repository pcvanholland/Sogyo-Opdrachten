package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Pair_Test extends Play_Test_Helper
{
    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));

        new Pair(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_initFailsWhenWrong()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.THREE));

        new Pair(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPairException.class)
    public void test_initFailsWhenTriedWithDifferentType()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));

        new Pair(cards, TEST_PLAYER);
    }

    @Test
    public void test_higherBeatsLower()
    {
        Play firstPlay = createPair(2);
        Play secondPlay = createPair(3);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        Play firstPlay = createPair(2);
        Play secondPlay = createPair(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        Play firstPlay = createPair(3);
        Play secondPlay = createPair(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_pairNotBeatSingle()
    {
        Play firstPlay = createPair(3);
        Play secondPlay = createSingle(2);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
