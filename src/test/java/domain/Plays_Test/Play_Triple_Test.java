package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Triple_Test extends Play_Test
{
    /**
     * Creates a Triple Play with random Suits.
     *
     * @param value {int} - The value the Cards ought to have.
     * @return {Triple} - The Play created.
     */
    private final Triple createTriple(final int value)
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(value));
        cards.add(createRandomCard(value));
        cards.add(createRandomCard(value));
        return new Triple(cards);
    }

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
        Play firstPlay = this.createTriple(2);
        Play secondPlay = this.createTriple(3);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    // Yes, yes, impossible, I know!
    public void test_equalsNotBeats()
    {
        Play firstPlay = this.createTriple(2);
        Play secondPlay = this.createTriple(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        Play firstPlay = this.createTriple(3);
        Play secondPlay = this.createTriple(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_TripleNotBeatSingle()
    {
        Play firstPlay = this.createTriple(3);

        ArrayList<Card> secondCard = new ArrayList<Card>();
        secondCard.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        Play secondPlay = new Single(secondCard);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
