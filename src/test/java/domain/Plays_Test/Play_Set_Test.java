package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Set_Test
{
    @Test
    public void test_playValidityPair()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.PAIR);

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityNonSet()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityTriple()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.TWO));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.TRIPLE);

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityTriple()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityTripleWithTwoEqual()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.TWO));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityBomb()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.BOMB);

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityBomb()
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
    public void test_playInvalidityBombWithTwoEqual()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityBombWithThreeEqual()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }
}
