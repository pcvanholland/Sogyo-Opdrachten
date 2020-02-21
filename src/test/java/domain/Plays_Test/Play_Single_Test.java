package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Single_Test extends Play_Test
{
    /**
     * Creates a Single Play with a random Suit.
     *
     * @param value {int} - The value the card ought to have.
     * @return {Single} - The Play created.
     */
    private final Single createSingle(final int value)
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(value));
        return new Single(cards);
    }

    @Test
    public void test_playValiditySingleCard()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));

        Assert.assertTrue(Play.isValidPlay(cards));
    }

    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));

        new Single(cards);
    }

    @Test
    public void test_higherBeatsLower()
    {
        Play firstPlay = this.createSingle(2);
        Play secondPlay = this.createSingle(3);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        Play firstPlay = this.createSingle(2);
        Play secondPlay = this.createSingle(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        Play firstPlay = this.createSingle(3);
        Play secondPlay = this.createSingle(3);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_singleNotBeatDouble()
    {
        Play firstPlay = this.createSingle(3);

        ArrayList<Card> secondCards = new ArrayList<Card>();
        secondCards.add(createRandomCard(2));
        secondCards.add(createRandomCard(2));
        Play secondPlay = new Pair(secondCards);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
