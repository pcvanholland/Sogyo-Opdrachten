package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Stair_Test
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
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));

        new Stair(cards);
    }

    @Test
    public void test_playValidityDoubleSetAddedOrderDoesNotMatter()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));

        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));

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
    public void test_playValidityTripleSetAddedOrderDoesNotMatter()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.FOUR));

        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));

        Assert.assertTrue(Play.isValidPlay(cards));
    }

    @Test
    public void test_playInvalidityDoubleSetWithTwoSingles()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.THREE));

        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.FIVE));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.FOUR));

        Assert.assertFalse(Play.isValidPlay(cards));
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

    @Test
    public void test_playValidityLongSet()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (StandardRank rank : StandardRank.values())
        {
            cards.add(new PlayingCard(Suit.SWORD, rank));
            cards.add(new PlayingCard(Suit.JADE, rank));
        }

        Assert.assertTrue(Play.isValidPlay(cards));
    }

    @Test
    public void test_higherBeatsLower()
    {
        ArrayList<Card> firstCards = new ArrayList<Card>();
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));
        Play firstPlay = new Stair(firstCards);

        ArrayList<Card> secondCards = new ArrayList<Card>();
        secondCards.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        secondCards.add(new PlayingCard(Suit.STAR, StandardRank.THREE));
        secondCards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        secondCards.add(new PlayingCard(Suit.JADE, StandardRank.FOUR));
        Play secondPlay = new Stair(secondCards);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        ArrayList<Card> firstCards = new ArrayList<Card>();
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));
        Play firstPlay = new Stair(firstCards);

        ArrayList<Card> secondCards = new ArrayList<Card>();
        secondCards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));
        secondCards.add(new PlayingCard(Suit.STAR, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));
        Play secondPlay = new Stair(secondCards);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        ArrayList<Card> firstCards = new ArrayList<Card>();
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.FOUR));
        firstCards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        Play firstPlay = new Stair(firstCards);

        ArrayList<Card> secondCards = new ArrayList<Card>();
        secondCards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));
        secondCards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));
        Play secondPlay = new Stair(secondCards);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_stairNotBeatSingle()
    {
        ArrayList<Card> firstCards = new ArrayList<Card>();
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        Play firstPlay = new Stair(firstCards);

        ArrayList<Card> secondCard = new ArrayList<Card>();
        secondCard.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        Play secondPlay = new Single(secondCard);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
