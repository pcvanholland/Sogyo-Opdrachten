package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

public class Dealer_Test
{
    @Test
    public void test_init()
    {
        Dealer dealer = new Dealer();
    }

    @Test
    public void test_drawFirstHandGivesCards()
    {
        Dealer dealer = new Dealer();
        Assert.assertTrue(dealer.drawFirstHand().get(0) instanceof Card);
    }

    @Test
    public void test_drawFirstHandGivesSixCards()
    {
        Dealer dealer = new Dealer();
        Assert.assertEquals(6, dealer.drawFirstHand().size());
    }

    @Test
    public void test_drawSecondHandGivesCards()
    {
        Dealer dealer = new Dealer();
        Assert.assertTrue(dealer.drawSecondHand().get(0) instanceof Card);
    }

    @Test
    public void test_drawSecondHandGivesEightCards()
    {
        Dealer dealer = new Dealer();
        Assert.assertEquals(8, dealer.drawSecondHand().size());
    }

    @Test
    public void test_canTakeEnoughCards()
    {
        Dealer dealer = new Dealer();
        for (int i = 0; i < 4; ++i)
        {
            dealer.drawFirstHand();
            dealer.drawSecondHand();
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_cantTakeTooManyCards()
    {
        Dealer dealer = new Dealer();
        for (int i = 0; i < 4; ++i)
        {
            dealer.drawFirstHand();
            dealer.drawSecondHand();
        }
        dealer.drawFirstHand();
    }

    @Test
    public void test_noDuplicatesInDrawnCards()
    {
        java.util.ArrayList<Card> cards = new java.util.ArrayList<Card>();
        Dealer dealer = new Dealer();

        // Deal cards to all four players.
        for (int i = 0; i < 4; ++i)
        {
            cards.addAll(dealer.drawFirstHand());
            cards.addAll(dealer.drawSecondHand());
        }

        boolean dupes = false;
        for (int i = 0; i < cards.size(); ++i)
        {
            for (int j = i + 1; j < cards.size(); ++j)
            {
                if (cards.get(i).equals(cards.get(j)))
                {
                    dupes = true;
                }
            }
        }
        Assert.assertFalse(dupes);
    }
}
