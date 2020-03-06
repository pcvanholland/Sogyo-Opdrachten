package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Phoenix_Straight_Test extends Play_Test_Helper
{
    /**
     * Helper function to create a Straight with a Phoenix,
     * with the specified start and end values.
     *
     * @param start {int} - The lowest value of the Straight.
     * @param end {int} - The highest value of the Straight.
     * @param phoenix {int} - The value of the Phoenix in this Straight.
     *
     * @return {Play} - The resulting Straight.
     */
    private Play createStraightWithPhoenix(
        final int start, final int end, final int phoenix
    )
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int current = start; current < end + 1; ++current)
        {
            if (current == phoenix)
            {
                cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
            }
            else
            {
                cards.add(createRandomCard(current));
            }
        }
        java.util.Collections.shuffle(cards);
        return new Straight(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInValidityTooShortArray()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
        new Straight(cards, TEST_PLAYER);
    }

    @Test
    public void test_playValidityStraightPhoenixOnEnd()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
        new Straight(cards, TEST_PLAYER);
    }

    @Test
    public void test_playValidityStraightPhoenixOnBegin()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
        new Straight(cards, TEST_PLAYER);
    }

    @Test
    public void test_playValidityStraightPhoenixWithin()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
        new Straight(cards, TEST_PLAYER);
    }

    @Test
    public void test_playValidityStraightPhoenixAsExtra()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
        new Straight(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityBrokenStraight()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(7));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
        new Straight(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityBrokenStraight2()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(6));
        cards.add(createRandomCard(7));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
        new Straight(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityBrokenStraight3()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));
        cards.add(createRandomCard(7));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
        new Straight(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityStraightWithExtra()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(9));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
        new Straight(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityStraightWithPair()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(4));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
        new Straight(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityTripleWithTwoSingles()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
        new Straight(cards, TEST_PLAYER);
    }
/*
    @Test
    public void test_playInvalidityStraightWithOnlyPairs()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
        new Straight(cards, TEST_PLAYER);
    }
*/
    @Test
    public void test_higherBeatsLowerPhoenix()
    {
        Play firstPlay = this.createStraightWithPhoenix(2, 6, 3);
        Play secondPlay = createStraight(3, 7);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }
/*
    @Test
    public void test_higherPhoenixBeatsLower()
    {
        Play firstPlay = createStraight(2, 6);
        Play secondPlay = this.createStraightWithPhoenix(3, 7, 7);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }
*/
    @Test
    public void test_equalsNotBeats()
    {
        Play firstPlay = this.createStraightWithPhoenix(2, 6, 5);
        Play secondPlay = createStraight(2, 6);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats2()
    {
        Play firstPlay = createStraight(2, 6);
        Play secondPlay = this.createStraightWithPhoenix(2, 6, 5);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerPhoenixNotBeatsHigher()
    {
        Play firstPlay = createStraight(3, 7);
        Play secondPlay = this.createStraightWithPhoenix(2, 6, 3);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigherPhoenix()
    {
        Play firstPlay = this.createStraightWithPhoenix(3, 7, 7);
        Play secondPlay = createStraight(2, 6);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }
}
