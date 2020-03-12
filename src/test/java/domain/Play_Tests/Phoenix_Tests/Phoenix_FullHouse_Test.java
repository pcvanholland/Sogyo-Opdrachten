package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Phoenix_FullHouse_Test extends Play_Test_Helper
{
    /**
     * Helper function to create a FullHouse with a Phoenix.
     * A value of 2 is used for the FullHouse, for it doens't matter.
     *
     * @param value {int} - The value of the Triple in the Full House.
     * @return {Play} - The FullHouse.
     */
    private Play createFullHouseWithPhoenix(final int value)
    {
        try
        {
            CardCollection cards = new CardCollection();
            cards.add(createRandomCard(value));
            cards.add(createRandomCard(value));
            cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

            cards.add(createRandomCard(2));
            cards.add(createRandomCard(2));

            return cards.createPlay(TEST_PLAYER, Set.FULLHOUSE);
        }
        catch (TaiPanException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void test_initAsPartOfTriple() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.FULLHOUSE);

        Assert.assertEquals(result, cards.determineTypesOfSet());

        cards.createPlay(TEST_PLAYER, Set.FULLHOUSE);
    }

    @Test
    public void test_initAsPartOfHighPair() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(3));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.FULLHOUSE);

        Assert.assertEquals(result, cards.determineTypesOfSet());

        cards.createPlay(TEST_PLAYER, Set.FULLHOUSE);
    }

    @Test
    public void test_initAsPartOfLowPair() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.FULLHOUSE);

        Assert.assertEquals(result, cards.determineTypesOfSet());

        cards.createPlay(TEST_PLAYER, Set.FULLHOUSE);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_initFailsWhenWrong() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(3));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());

        cards.createPlay(TEST_PLAYER, Set.FULLHOUSE);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_initFailsWhenPhoenixIsExcess() throws
        InvalidRankException, InvalidPlayException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());

        cards.createPlay(TEST_PLAYER, Set.FULLHOUSE);
    }

    @Test
    public void test_higherBeatsLowerPhoenix()
    {
        Play firstPlay = this.createFullHouseWithPhoenix(3);
        Play secondPlay = createFullHouse(4, 2);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_higherPhoenixBeatsLower()
    {
        Play firstPlay = createFullHouse(3, 2);
        Play secondPlay = this.createFullHouseWithPhoenix(4);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    // Impossible, I know!
    public void test_equalsNotBeats()
    {
        Play firstPlay = this.createFullHouseWithPhoenix(3);
        Play secondPlay = createFullHouse(3, 2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    // Impossible, I know!
    public void test_equalsNotBeats2()
    {
        Play firstPlay = createFullHouse(3, 2);
        Play secondPlay = this.createFullHouseWithPhoenix(3);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerPhoenixNotBeatsHigher()
    {
        Play firstPlay = createFullHouse(4, 2);
        Play secondPlay = this.createFullHouseWithPhoenix(3);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigherPhoenix()
    {
        Play firstPlay = this.createFullHouseWithPhoenix(4);
        Play secondPlay = createFullHouse(3, 2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }
}
