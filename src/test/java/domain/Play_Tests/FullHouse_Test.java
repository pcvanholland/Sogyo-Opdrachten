package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class FullHouse_Test extends Play_Test_Helper
{
    @Test
    public void test_playInValidityTooShortArray()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playValidityFullHouse()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.FULLHOUSE);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playInValidityTripleWithTwoSingles()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playInValidityTwoPairWithSingle()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playInValidityPairWithThreeSingles()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_initFromArray()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));

        new FullHouse(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_initFailsWhenWrong()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(3));

        new FullHouse(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidFullHouseException.class)
    public void test_initFailsWhenDifferentType()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        new FullHouse(cards, TEST_PLAYER);
    }

    @Test
    public void test_higherBeatsLower()
    {
        Play firstPlay = createFullHouse(2, 3);
        Play secondPlay = createFullHouse(3, 4);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_pairDoesNotMatterToLose()
    {
        Play firstPlay = createFullHouse(3, 2);
        Play secondPlay = createFullHouse(2, 4);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_pairDoesNotMatterToWin()
    {
        Play firstPlay = createFullHouse(3, 4);
        Play secondPlay = createFullHouse(4, 2);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        Play firstPlay = createFullHouse(2, 3);
        Play secondPlay = createFullHouse(2, 3);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        Play firstPlay = createFullHouse(3, 4);
        Play secondPlay = createFullHouse(2, 3);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_fullHouseNotBeatSingle()
    {
        Play firstPlay = createFullHouse(3, 4);
        Play secondPlay = createSingle(2);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
