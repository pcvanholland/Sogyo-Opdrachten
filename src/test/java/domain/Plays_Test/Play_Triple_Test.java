package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Triple_Test extends Play_Test_Helper
{
    @Test
    public void test_init()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        new Triple(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_initFailsWhenWrong()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));

        new Triple(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidTripleException.class)
    public void test_initFailsWhenTriedWithDifferentType()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        new Triple(cards, TEST_PLAYER);
    }

    @Test
    public void test_higherBeatsLower()
    {
        Play firstPlay = createTriple(2);
        Play secondPlay = createTriple(3);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    // Yes, yes, impossible, I know!
    public void test_equalsNotBeats()
    {
        Play firstPlay = createTriple(2);
        Play secondPlay = createTriple(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        Play firstPlay = createTriple(3);
        Play secondPlay = createTriple(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_TripleNotBeatSingle()
    {
        Play firstPlay = createTriple(3);
        Play secondPlay = createSingle(2);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
