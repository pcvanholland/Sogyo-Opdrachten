package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Phoenix_Pair_Test extends Play_Test_Helper
{
    /**
     * Helper function to create a Pair with a Phoenix.
     *
     * @param value {int} - The value the Pair ought to have.
     * @return {Play} - The Pair.
     */
    private Play createPairWithPhoenix(final int value)
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(value));
        cards.add(new SpecialCard(SpecialRank.PHOENIX));

        return new Pair(cards, TEST_PLAYER);
    }

    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(new SpecialCard(SpecialRank.PHOENIX));

        new Pair(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_initFailsWhenWrong()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(new SpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));

        new Pair(cards, TEST_PLAYER);
    }

    @Test
    public void test_higherBeatsLowerPhoenix()
    {
        Play firstPlay = this.createPairWithPhoenix(2);
        Play secondPlay = createPair(3);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_higherPhoenixBeatsLower()
    {
        Play firstPlay = createPair(2);
        Play secondPlay = this.createPairWithPhoenix(3);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        Play firstPlay = this.createPairWithPhoenix(2);
        Play secondPlay = createPair(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats2()
    {
        Play firstPlay = createPair(2);
        Play secondPlay = this.createPairWithPhoenix(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerPhoenixNotBeatsHigher()
    {
        Play firstPlay = createPair(3);
        Play secondPlay = this.createPairWithPhoenix(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigherPhoenix()
    {
        Play firstPlay = this.createPairWithPhoenix(3);
        Play secondPlay = createPair(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }
}
