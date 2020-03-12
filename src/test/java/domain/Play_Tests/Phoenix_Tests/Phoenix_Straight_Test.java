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
        try
        {
            CardCollection cards = new CardCollection();
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
            return cards.createPlay(TEST_PLAYER, Set.STRAIGHT);
        }
        catch (TaiPanException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInValidityTooShortArray() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
        new Straight(cards, TEST_PLAYER);
    }

    @Test
    public void test_playValidityStraightPhoenixOnEnd() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, cards.determineTypesOfSet());
        new Straight(cards, TEST_PLAYER);
    }

    @Test
    public void test_playValidityStraightPhoenixOnBegin() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, cards.determineTypesOfSet());
        new Straight(cards, TEST_PLAYER);
    }

    @Test
    public void test_playValidityStraightPhoenixWithin() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, cards.determineTypesOfSet());
        new Straight(cards, TEST_PLAYER);
    }

    @Test
    public void test_playValidityStraightPhoenixAsExtra() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, cards.determineTypesOfSet());
        new Straight(cards, TEST_PLAYER);
    }

    @Test
    public void test_playValidityStraightMahjongAndPhoenix() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, cards.determineTypesOfSet());
        new Straight(cards, TEST_PLAYER);
    }

    @Test
    public void test_playValidityStraightPhoenixAfterMahjong() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));
        cards.add(createRandomCard(7));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, cards.determineTypesOfSet());
        new Straight(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityBrokenStraight() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(7));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
        new Straight(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityBrokenStraight2() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(6));
        cards.add(createRandomCard(7));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
        new Straight(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityBrokenStraight3() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));
        cards.add(createRandomCard(7));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
        new Straight(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityStraightWithExtra() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(9));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
        new Straight(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityStraightWithPair() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(4));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
        new Straight(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityTripleWithTwoSingles() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
        new Straight(cards, TEST_PLAYER);
    }
/*
    @Test(expected = InvalidPlayException.class)
    public void test_playInvaliditySuperLongStraight() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        for (StandardRank rank : StandardRank.values())
        {
            cards.add(createRandomCard(rank.getValue()));
        }
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
        cards.createPlay(TEST_PLAYER, Set.STRAIGHT);
    }
*/
    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityStraightWithOnlyPairs() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
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

        Assert.assertEquals(result, cards.determineTypesOfSet());
        cards.createPlay(TEST_PLAYER, Set.STRAIGHT);
    }

    @Test
    public void test_higherBeatsLowerPhoenix()
    {
        Play firstPlay = this.createStraightWithPhoenix(2, 6, 3);
        Play secondPlay = createStraight(3, 7);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_higherPhoenixBeatsLower()
    {
        Play firstPlay = createStraight(2, 6);
        Play secondPlay = this.createStraightWithPhoenix(3, 7, 7);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

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
