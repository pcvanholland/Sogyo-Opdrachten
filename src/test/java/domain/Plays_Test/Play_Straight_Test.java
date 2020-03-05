package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Straight_Test extends Play_Test_Helper
{
    @Test
    public void test_playInValidityTooShortArray()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityStraight()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityBrokenStraight()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(7));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityBrokenStraight2()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(6));
        cards.add(createRandomCard(7));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityBrokenStraight3()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));
        cards.add(createRandomCard(7));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityStraightWithExtra()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));
        cards.add(createRandomCard(8));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityStraightWithPair()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));
        cards.add(createRandomCard(4));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityTripleWithTwoSingles()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityStraightWithTriple()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityStraightWithOnlyPairs()
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
        cards.add(createRandomCard(6));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));

        new Straight(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_initFailsWhenWrong()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));

        new Straight(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidStraightException.class)
    public void test_initFailsWhenTriedWithDifferentType()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));

        new Straight(cards, TEST_PLAYER);
    }

    @Test
    public void test_higherBeatsLower()
    {
        Play firstPlay = createStraight(2, 6);
        Play secondPlay = createStraight(3, 7);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        Play firstPlay = createStraight(2, 6);
        Play secondPlay = createStraight(2, 6);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        Play firstPlay = createStraight(3, 7);
        Play secondPlay = createStraight(2, 6);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_straightNotBeatSingle()
    {
        Play firstPlay = createStraight(2, 6);
        Play secondPlay = createSingle(2);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }

    @Test
    public void test_sizeMatters()
    {
        Play firstPlay = createStraight(2, 6);
        Play secondPlay = createStraight(2, 7);

        Assert.assertFalse(secondPlay.beats(firstPlay));
        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
