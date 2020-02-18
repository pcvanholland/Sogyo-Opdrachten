package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_MultiSets_Test
{
    @Test
    public void test_playValiditySingleSet()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));

        Assert.assertTrue(Play.isValidPlay(cards));
    }

    @Test
    public void test_playInValidityOddSizedArray()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(Suit.STAR, StandardRank.THREE));

        Assert.assertFalse(Play.isValidPlay(cards));
    }

    @Test
    public void test_playValidityDoubleSet()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));

        Assert.assertTrue(Play.isValidPlay(cards));
    }

    @Test
    public void test_playInvalidityDoubleSetNotInOrder()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.FOUR));

        Assert.assertFalse(Play.isValidPlay(cards));
    }

    @Test
    public void test_playInvalidityDoubleTriple()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));

        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));

        Assert.assertFalse(Play.isValidPlay(cards));
    }

    @Test
    public void test_playInvalidityDoubleWithTwoSingles()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.FOUR));

        Assert.assertFalse(Play.isValidPlay(cards));
    }

    @Test
    public void test_playValidityTripleSet()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));

        cards.add(new PlayingCard(Suit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));

        Assert.assertTrue(Play.isValidPlay(cards));
    }

    @Test
    public void test_playInvalidityTripleSetSlightlyNotInOrder()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(Suit.SWORD, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));

        cards.add(new PlayingCard(Suit.JADE, StandardRank.FIVE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FIVE));

        Assert.assertFalse(Play.isValidPlay(cards));
    }

    @Test
    public void test_playInvalidityTripleSetNotInOrder()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(Suit.SWORD, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));

        cards.add(new PlayingCard(Suit.JADE, StandardRank.SIX));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.SIX));

        Assert.assertFalse(Play.isValidPlay(cards));
    }
}
