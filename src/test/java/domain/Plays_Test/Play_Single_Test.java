package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Single_Test
{
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
        ArrayList<Card> firstCard = new ArrayList<Card>();
        firstCard.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));
        Play firstPlay = new Single(firstCard);

        ArrayList<Card> secondCard = new ArrayList<Card>();
        secondCard.add(new PlayingCard(Suit.SWORD, StandardRank.THREE));
        Play secondPlay = new Single(secondCard);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }
}
