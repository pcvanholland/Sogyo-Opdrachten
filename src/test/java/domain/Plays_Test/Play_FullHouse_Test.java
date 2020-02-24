package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_FullHouse_Test extends Play_Test
{
    @Test
    public void test_playInValidityTooShortArray()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityFullHouse()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.FULLHOUSE);

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
    public void test_playInValidityTwoPairWithSingle()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInValidityPairWithThreeSingles()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.FIVE));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_init()
    {
        new FullHouse(createTriple(2), createPair(3));
        new FullHouse(createPair(2), createTriple(3));
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
