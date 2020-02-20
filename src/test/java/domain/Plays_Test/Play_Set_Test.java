package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Set_Test
{
    @Test
    public void test_playValiditySet()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));

        Assert.assertTrue(Play.isValidPlay(cards));
    }

    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));

        new Set(cards);
    }

    @Test
    public void test_playInvalidityNonSet()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));

        Assert.assertFalse(Play.isValidPlay(cards));
    }

    @Test
    public void test_playValidityTriple()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.TWO));

        Assert.assertTrue(Play.isValidPlay(cards));
    }

    @Test
    public void test_playInvalidityTriple()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));

        Assert.assertFalse(Play.isValidPlay(cards));
    }

    @Test
    public void test_playInvalidityTripleWithTwoEqual()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.TWO));

        Assert.assertFalse(Play.isValidPlay(cards));
    }

    @Test
    public void test_playValidityBomb()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));

        Assert.assertTrue(Play.isValidPlay(cards));
    }

    @Test
    public void test_playInvalidityBomb()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FIVE));

        Assert.assertFalse(Play.isValidPlay(cards));
    }

    @Test
    public void test_playInvalidityBombWithTwoEqual()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));

        Assert.assertFalse(Play.isValidPlay(cards));
    }

    @Test
    public void test_playInvalidityBombWithThreeEqual()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));

        Assert.assertFalse(Play.isValidPlay(cards));
    }
}
