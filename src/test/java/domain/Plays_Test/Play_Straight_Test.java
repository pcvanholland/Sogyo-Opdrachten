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
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FIVE));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityStraight()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.SIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityBrokenStraight()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.SEVEN));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityBrokenStraight2()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SEVEN));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityStraightWithExtra()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.SIX));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.EIGHT));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityStraightWithPair()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.SIX));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityTripleWithTwoSingles()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityStraightWithTriple()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.SIX));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityStraightWithOnlyPairs()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.SIX));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.SIX));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));

        new Straight(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_initFailsWhenWrong()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.THREE));

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
