package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Triple_Test
{
    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));

        new Triple(cards);
    }

    @Test
    public void test_higherBeatsLower()
    {
        ArrayList<Card> firstCards = new ArrayList<Card>();
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));
        Play firstPlay = new Triple(firstCards);

        ArrayList<Card> secondCards = new ArrayList<Card>();
        secondCards.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        secondCards.add(new PlayingCard(Suit.STAR, StandardRank.THREE));
        secondCards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));
        Play secondPlay = new Triple(secondCards);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    // Yes, yes, impossible, I know!
    public void test_equalsNotBeats()
    {
        ArrayList<Card> firstCards = new ArrayList<Card>();
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));
        Play firstPlay = new Triple(firstCards);

        ArrayList<Card> secondCards = new ArrayList<Card>();
        secondCards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));
        secondCards.add(new PlayingCard(Suit.STAR, StandardRank.TWO));
        secondCards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        Play secondPlay = new Triple(secondCards);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        ArrayList<Card> firstCards = new ArrayList<Card>();
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));
        Play firstPlay = new Triple(firstCards);

        ArrayList<Card> secondCards = new ArrayList<Card>();
        secondCards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));
        secondCards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        secondCards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        Play secondPlay = new Triple(secondCards);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_TripleNotBeatSingle()
    {
        ArrayList<Card> firstCards = new ArrayList<Card>();
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.PAGODA, StandardRank.THREE));
        Play firstPlay = new Triple(firstCards);

        ArrayList<Card> secondCard = new ArrayList<Card>();
        secondCard.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        Play secondPlay = new Single(secondCard);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
