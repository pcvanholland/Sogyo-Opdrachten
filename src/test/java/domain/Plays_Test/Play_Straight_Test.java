package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Straight_Test extends Play_Test
{
    @Test
    public void test_playInValidityTooShortArray()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FIVE));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityStraight()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FIVE));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.SIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityBrokenStraight()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FIVE));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.SEVEN));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityStraightWithExtra()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FIVE));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.SIX));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.EIGHT));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityStraightWithPair()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FIVE));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.SIX));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityTripleWithTwoSingles()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityStraightWithTriple()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FIVE));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.SIX));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityStraightWithOnlyPairs()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FIVE));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FIVE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.SIX));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.SIX));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FIVE));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.SIX));

        new Straight(cards);
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
