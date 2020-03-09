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
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(value));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        return new Pair(cards, TEST_PLAYER);
    }

    @Test
    public void test_init()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.PAIR);

        Assert.assertEquals(result, cards.determineTypesOfSet());

        new Pair(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_initFailsWhenWrong()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(3));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());

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
