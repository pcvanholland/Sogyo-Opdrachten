package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Phoenix_Stair_Test extends Play_Test_Helper
{
    /**
     * Helper function to create a Stair with a Phoenix,
     * with the specified start and end values.
     *
     * @param start {int} - The lowest value of the Stair.
     * @param end {int} - The highest value of the Stair.
     * @param phoenix {int} - The value of the Phoenix in this Stair.
     *
     * @return {Play} - The resulting Stair.
     */
    private Play createStairWithPhoenix(
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
                    cards.add(createRandomCard(current));
                }
                else
                {
                    cards.add(createRandomCard(current));
                    cards.add(createRandomCard(current));
                }
            }
            return cards.createPlay(TEST_PLAYER, Set.STAIR);
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
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.TRIPLE);

        Assert.assertEquals(result, cards.determineTypesOfSet());
        cards.createPlay(TEST_PLAYER, Set.STAIR);
    }

    @Test
    public void test_playValidityStairPhoenixOnEnd() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, cards.determineTypesOfSet());
        cards.createPlay(TEST_PLAYER, Set.STAIR);
    }

    @Test
    public void test_playValidityStairPhoenixOnBegin() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, cards.determineTypesOfSet());
        cards.createPlay(TEST_PLAYER, Set.STAIR);
    }

    @Test
    public void test_playValidityStairPhoenixWithin() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, cards.determineTypesOfSet());
        cards.createPlay(TEST_PLAYER, Set.STAIR);
    }

    @Test
    public void test_playValidityStairPhoenixAsExtra() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, cards.determineTypesOfSet());
        cards.createPlay(TEST_PLAYER, Set.STAIR);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityBrokenStair() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(5));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
        cards.createPlay(TEST_PLAYER, Set.STAIR);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityBrokenStair2() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(6));
        cards.add(createRandomCard(6));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
        cards.createPlay(TEST_PLAYER, Set.STAIR);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityBrokenStair3() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(5));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
        cards.createPlay(TEST_PLAYER, Set.STAIR);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityStairWithExtra() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(6));
        cards.add(createRandomCard(6));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
        cards.createPlay(TEST_PLAYER, Set.STAIR);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityStairWithTriple() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
        cards.createPlay(TEST_PLAYER, Set.STAIR);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_playInvalidityStairWithPhoenixExtra() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
        cards.createPlay(TEST_PLAYER, Set.STAIR);
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
        cards.createPlay(TEST_PLAYER, Set.STAIR);
    }

    @Test
    public void test_higherBeatsLowerPhoenix()
    {
        Play firstPlay = this.createStairWithPhoenix(2, 6, 3);
        Play secondPlay = createStair(3, 7);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_higherPhoenixBeatsLower()
    {
        Play firstPlay = createStair(2, 6);
        Play secondPlay = this.createStairWithPhoenix(3, 7, 7);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        Play firstPlay = this.createStairWithPhoenix(2, 6, 5);
        Play secondPlay = createStair(2, 6);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats2()
    {
        Play firstPlay = createStair(2, 6);
        Play secondPlay = this.createStairWithPhoenix(2, 6, 5);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerPhoenixNotBeatsHigher()
    {
        Play firstPlay = createStair(3, 7);
        Play secondPlay = this.createStairWithPhoenix(2, 6, 3);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigherPhoenix()
    {
        Play firstPlay = this.createStairWithPhoenix(3, 7, 7);
        Play secondPlay = createStair(2, 6);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }
}
