package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Straight_Test
{
    private Straight createStraight(final int start, final int stop)
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        int current = start - 2;
        while (current < stop - 1)
        {
            cards.add(new PlayingCard(Suit.SWORD, StandardRank.values()[current++]));
        }
        return new Straight(cards);
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

        ArrayList<Card> secondCards = new ArrayList<Card>();
        secondCards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        secondCards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        secondCards.add(new PlayingCard(Suit.STAR, StandardRank.FIVE));
        secondCards.add(new PlayingCard(Suit.SWORD, StandardRank.SEVEN));
        secondCards.add(new PlayingCard(Suit.JADE, StandardRank.SIX));
        Play secondPlay = new Straight(secondCards);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        ArrayList<Card> firstCards = new ArrayList<Card>();
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        firstCards.add(new PlayingCard(Suit.STAR, StandardRank.FIVE));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.SIX));
        Play firstPlay = new Straight(firstCards);

        ArrayList<Card> secondCards = new ArrayList<Card>();
        secondCards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        secondCards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        secondCards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        secondCards.add(new PlayingCard(Suit.STAR, StandardRank.FIVE));
        secondCards.add(new PlayingCard(Suit.JADE, StandardRank.SIX));
        Play secondPlay = new Straight(secondCards);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        ArrayList<Card> firstCards = new ArrayList<Card>();
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        firstCards.add(new PlayingCard(Suit.STAR, StandardRank.FIVE));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.SIX));
        Play firstPlay = new Straight(firstCards);

        ArrayList<Card> secondCards = new ArrayList<Card>();
        secondCards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        secondCards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        secondCards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        secondCards.add(new PlayingCard(Suit.JADE, StandardRank.SIX));
        Play secondPlay = new Straight(secondCards);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_straightNotBeatSingle()
    {
        ArrayList<Card> firstCards = new ArrayList<Card>();
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        firstCards.add(new PlayingCard(Suit.STAR, StandardRank.FIVE));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.SIX));
        Play firstPlay = new Straight(firstCards);

        ArrayList<Card> secondCard = new ArrayList<Card>();
        secondCard.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        Play secondPlay = new Single(secondCard);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }

    @Test
    public void test_sizeMatters()
    {
        ArrayList<Card> firstCards = new ArrayList<Card>();
        firstCards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        firstCards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        firstCards.add(new PlayingCard(Suit.STAR, StandardRank.FIVE));
        firstCards.add(new PlayingCard(Suit.JADE, StandardRank.SIX));
        Play firstPlay = new Straight(firstCards);

        ArrayList<Card> secondCards = new ArrayList<Card>();
        secondCards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        secondCards.add(new PlayingCard(Suit.JADE, StandardRank.THREE));
        secondCards.add(new PlayingCard(Suit.PAGODA, StandardRank.FOUR));
        secondCards.add(new PlayingCard(Suit.STAR, StandardRank.FIVE));
        secondCards.add(new PlayingCard(Suit.JADE, StandardRank.SIX));
        secondCards.add(new PlayingCard(Suit.JADE, StandardRank.SEVEN));
        Play secondPlay = new Straight(secondCards);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }
}
