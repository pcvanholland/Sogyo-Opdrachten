package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

public class Draw_Test extends Player_Test_Helper
{
    @Test
    public void test_zeroHandsDrawn()
    {
        Player firstPlayer = new Player(new Table());

        Assert.assertTrue(firstPlayer.canDrawCards());
    }

    @Test
    public void test_drawFirstCards() throws CantDrawTooManyTimesException
    {
        Player firstPlayer = new Player(new Table());

        firstPlayer.drawCards();

        Assert.assertEquals(8, firstPlayer.getCards().size());
    }

    @Test
    public void test_drawFirstCardsAreSeeded() throws
        CantDrawTooManyTimesException
    {
        Dealer refDealer = new Dealer(START_SEED);
        Player firstPlayer = new Player(new Table(), START_SEED);

        firstPlayer.drawCards();

        int i = 0;
        for (Card card : refDealer.drawFirstHand())
        {
            Assert.assertTrue(
                card.getSuit() == firstPlayer.getCards().get(i).getSuit() &&
                card.getRank() == firstPlayer.getCards().get(i).getRank()
            );
            i++;
        }
    }

    @Test
    public void test_canDrawSecondHandOfCards() throws
        CantDrawTooManyTimesException
    {
        Player firstPlayer = new Player(new Table());

        firstPlayer.drawCards();

        Assert.assertTrue(firstPlayer.canDrawCards());
    }

    @Test
    public void test_drawSecondCards() throws CantDrawTooManyTimesException
    {
        Player firstPlayer = new Player(new Table());

        firstPlayer.drawCards();
        firstPlayer.drawCards();

        Assert.assertEquals(14, firstPlayer.getCards().size());
    }

    @Test
    public void test_cantDrawThirdHandOfCards() throws
        CantDrawTooManyTimesException
    {
        Player firstPlayer = new Player(new Table());

        firstPlayer.drawCards();
        firstPlayer.drawCards();

        Assert.assertFalse(firstPlayer.canDrawCards());
    }

    @Test(expected = CantDrawTooManyTimesException.class)
    public void test_cantDrawToManyCards() throws CantDrawTooManyTimesException
    {
        Player firstPlayer = new Player(new Table());

        firstPlayer.drawCards();
        firstPlayer.drawCards();
        firstPlayer.drawCards();

        Assert.assertEquals(14, firstPlayer.getCards().size());
    }
}
