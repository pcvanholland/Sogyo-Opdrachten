package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Pair_Test
{
    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));

        new Pair(cards);
    }

    @Test
    public void test_higherBeatsLower()
    {
        ArrayList<Card> firstCards = new ArrayList<Card>();
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        Play firstPlay = new Pair(firstCards);

        ArrayList<Card> secondCards = new ArrayList<Card>();
        secondCards.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        secondCards.add(new PlayingCard(Suit.STAR, StandardRank.THREE));
        Play secondPlay = new Pair(secondCards);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        ArrayList<Card> firstCards = new ArrayList<Card>();
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        Play firstPlay = new Pair(firstCards);

        ArrayList<Card> secondCards = new ArrayList<Card>();
        secondCards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));
        secondCards.add(new PlayingCard(Suit.STAR, StandardRank.TWO));
        Play secondPlay = new Pair(secondCards);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        ArrayList<Card> firstCards = new ArrayList<Card>();
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        Play firstPlay = new Pair(firstCards);

        ArrayList<Card> secondCards = new ArrayList<Card>();
        secondCards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));
        secondCards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        Play secondPlay = new Pair(secondCards);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_pairNotBeatSingle()
    {
        ArrayList<Card> firstCards = new ArrayList<Card>();
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        Play firstPlay = new Pair(firstCards);

        ArrayList<Card> secondCard = new ArrayList<Card>();
        secondCard.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        Play secondPlay = new Single(secondCard);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
