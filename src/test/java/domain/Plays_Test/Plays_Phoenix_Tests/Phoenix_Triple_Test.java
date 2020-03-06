package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Phoenix_Triple_Test extends Play_Test_Helper
{
    /**
     * Helper function to create a Triple with a Phoenix.
     *
     * @param value {int} - The value the Triple ought to have.
     * @return {Play} - The Triple.
     */
    private Play createTripleWithPhoenix(final int value)
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(value));
        cards.add(createRandomCard(value));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        return new Triple(cards, TEST_PLAYER);
    }

    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.TRIPLE);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));

        new Triple(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_initFailsWhenWrong()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(3));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));

        new Triple(cards, TEST_PLAYER);
    }

    @Test
    public void test_higherBeatsLowerPhoenix()
    {
        Play firstPlay = this.createTripleWithPhoenix(2);
        Play secondPlay = createTriple(3);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_higherPhoenixBeatsLower()
    {
        Play firstPlay = createTriple(2);
        Play secondPlay = this.createTripleWithPhoenix(3);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        Play firstPlay = this.createTripleWithPhoenix(2);
        Play secondPlay = createTriple(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats2()
    {
        Play firstPlay = createTriple(2);
        Play secondPlay = this.createTripleWithPhoenix(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerPhoenixNotBeatsHigher()
    {
        Play firstPlay = createTriple(3);
        Play secondPlay = this.createTripleWithPhoenix(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigherPhoenix()
    {
        Play firstPlay = this.createTripleWithPhoenix(3);
        Play secondPlay = createTriple(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }
}
