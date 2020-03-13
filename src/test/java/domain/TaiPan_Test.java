package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TaiPan_Test extends TaiPan_Test_Helper
{
    @Test
    public void test_init() throws TaiPanException
    {
        new TaiPan();
        new TaiPan(Player_Test_Helper.START_SEED);
    }

    @Test
    public void test_drawCards() throws TaiPanException
    {
        int playerID = 0;
        TaiPan tp = new TaiPan();
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        Assert.assertEquals(14, tp.getPlayer(playerID).getCards().size());
    }

    @Test(expected = CantDrawTooManyTimesException.class)
    public void test_drawTooMuchCards() throws TaiPanException
    {
        int playerID = 0;
        TaiPan tp = new TaiPan();
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        Assert.assertEquals(14, tp.getPlayer(playerID).getCards().size());
    }

    @Test
    public void test_play() throws TaiPanException
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test_Helper.START_SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID + 1);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        tp.play(playerID, cards, Set.SINGLE);

        Assert.assertEquals(13, tp.getPlayer(playerID).getCards().size());
    }

    @Test
    public void test_playWithPhoenix() throws TaiPanException
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test_Helper.START_STREET_PHOENIX_SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID + 1);

        ArrayList<Card> cards = new ArrayList<Card>();
        for (Card card : tp.getPlayer(playerID).getCards())
        {
            cards.add(card);
        }

        tp.play(playerID, cards, Set.STRAIGHT);

        Assert.assertEquals(0, tp.getPlayer(playerID).getCards().size());
    }

    @Test(expected = CantPlayPlayerException.class)
    public void test_cantPlayNotInTurn() throws TaiPanException
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test_Helper.START_SEED);
        tp.letPlayerDrawCards(playerID);
        int amount = tp.getPlayer(playerID).getCards().size();

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        tp.play(playerID, cards, Set.SINGLE);

        Assert.assertEquals(amount, tp.getPlayer(playerID).getCards().size());
    }

    @Test(expected = PlayerDontHasCardException.class)
    public void test_cantPlayNotInTurnOtherPlayer() throws TaiPanException
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test_Helper.START_SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        tp.play(playerID + 1, cards, Set.SINGLE);

        Assert.assertEquals(14, tp.getPlayer(playerID).getCards().size());
    }

    @Test(expected = PlayerDontHasCardException.class)
    public void test_cantPlayHasNoCards() throws TaiPanException
    {
        int playerID = 0;
        TaiPan tp = new TaiPan();

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        tp.play(playerID, cards, Set.SINGLE);

        Assert.assertEquals(0, tp.getPlayer(playerID).getCards().size());
    }

    @Test(expected = InvalidPlayException.class)
    public void test_cantPlayInvalidPlay() throws TaiPanException
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test_Helper.START_SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        tp.play(playerID, cards, Set.PAIR);

        Assert.assertEquals(14, tp.getPlayer(playerID).getCards().size());
    }

    @Test(expected = PlayerDontHasCardException.class)
    public void test_cantPlayDontHasCards() throws TaiPanException
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test_Helper.START_SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));

        tp.play(playerID, cards, Set.SINGLE);

        Assert.assertEquals(14, tp.getPlayer(playerID).getCards().size());
    }

    @Test
    public void test_pass() throws TaiPanException
    {
        int playerID = 0;
        TaiPan tp = createSeededGame(Player_Test_Helper.START_SEED);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        tp.play(playerID, cards, Set.SINGLE);

        tp.pass(playerID + 1);

        Assert.assertFalse(tp.getPlayer(playerID).isInTurn());
        Assert.assertFalse(tp.getPlayer(playerID + 1).isInTurn());
        Assert.assertTrue(tp.getPlayer(playerID + 2).isInTurn());
    }

    @Test(expected = CantPassException.class)
    public void test_cantPassWhenNotInTurn() throws TaiPanException
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test_Helper.START_SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        tp.pass(playerID + 1);

        Assert.assertTrue(tp.getPlayer(playerID).isInTurn());
        Assert.assertFalse(tp.getPlayer(playerID + 1).isInTurn());
    }

    @Test
    public void test_emptyScore() throws TaiPanException
    {
        int playerID = 0;
        int[] expectedResult = new int[] {0, 0};
        TaiPan tp = createSeededGame(Player_Test_Helper.START_SEED);
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        tp.play(playerID, cards, Set.SINGLE);
        tp.pass(playerID + 1);
        tp.pass(playerID + 2);
        tp.pass(playerID + 3);

        for (int i = 0; i < tp.getScore().length; ++i)
        {
            Assert.assertEquals(expectedResult[i], tp.getScore()[i]);
        }
    }
/*
    @Test
    public void test_nonEmptyScore() throws TaiPanException
    {
        int playerID = 0;
        int[] expectedResult = new int[] {25, 0};
        TaiPan tp = createSeededGame(Player_Test_Helper.START_STREET_SEED);

        ArrayList<Card> cards = new ArrayList<Card>();
        for (Card card : tp.getPlayer(playerID).getCards())
        {
            cards.add(card);
        }

        tp.play(playerID, cards, Set.STRAIGHT);
        tp.pass(playerID + 1);
        tp.pass(playerID + 2);
        tp.pass(playerID + 3);

        for (int i = 0; i < tp.getScore().length; ++i)
        {
            Assert.assertEquals(expectedResult[i], tp.getScore()[i]);
        }
    }*/
}
