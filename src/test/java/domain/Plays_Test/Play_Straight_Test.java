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
        // First two cards ought to be different to prevent an accidental bomb.
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.values()[current++]));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.values()[current++]));
        while (current < stop - 1)
        {//ToDo: randomInt for suit, shuffle Play.
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
        Play firstPlay = this.createStraight(2, 6);
        Play secondPlay = this.createStraight(3, 7);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        Play firstPlay = this.createStraight(2, 6);
        Play secondPlay = this.createStraight(2, 6);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        Play firstPlay = this.createStraight(3, 7);
        Play secondPlay = this.createStraight(2, 6);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_straightNotBeatSingle()
    {
        Play firstPlay = this.createStraight(2, 6);

        ArrayList<Card> secondCard = new ArrayList<Card>();
        secondCard.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        Play secondPlay = new Single(secondCard);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }

    @Test
    public void test_sizeMatters()
    {
        Play firstPlay = this.createStraight(2, 6);
        Play secondPlay = this.createStraight(2, 7);

        Assert.assertFalse(secondPlay.beats(firstPlay));
        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
