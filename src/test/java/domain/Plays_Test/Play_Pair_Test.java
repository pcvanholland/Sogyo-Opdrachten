package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Pair_Test extends Play_Test
{
    /**
     * Creates a Pair Play with random Suits.
     *
     * @param value {int} - The value the Cards ought to have.
     * @return {Pair} - The Play created.
     */
    private final Pair createPair(final int value)
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(value));
        cards.add(createRandomCard(value));
        return new Pair(cards);
    }

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
        Play firstPlay = this.createPair(2);
        Play secondPlay = this.createPair(3);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        Play firstPlay = this.createPair(2);
        Play secondPlay = this.createPair(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        Play firstPlay = this.createPair(3);
        Play secondPlay = this.createPair(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_pairNotBeatSingle()
    {
        Play firstPlay = this.createPair(3);

        ArrayList<Card> secondCard = new ArrayList<Card>();
        secondCard.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        Play secondPlay = new Single(secondCard);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
