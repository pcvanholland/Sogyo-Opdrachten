package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Stair_Test extends Play_Test
{
    /**
     * Creates a Stair Play with random Suits.
     *
     * @param start {int} - The lowest value of the Stair.
     * @param end {int} - The highest value of the Stair.
     *
     * @return {Stair} - The Play created.
     */
    private final Stair createStair(final int start, final int end)
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int current = start; current < end + 1; ++current)
        {
            cards.add(createRandomCard(current));
            cards.add(createRandomCard(current));
        }
        java.util.Collections.shuffle(cards);
        return new Stair(cards);
    }

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
        Play firstPlay = this.createStair(2, 3);
        Play secondPlay = this.createStair(3, 4);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        Play firstPlay = this.createStair(2, 3);
        Play secondPlay = this.createStair(2, 3);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        Play firstPlay = this.createStair(3, 4);
        Play secondPlay = this.createStair(2, 3);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_stairNotBeatSingle()
    {
        Play firstPlay = this.createStair(3, 4);

        ArrayList<Card> secondCard = new ArrayList<Card>();
        secondCard.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        Play secondPlay = new Single(secondCard);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
