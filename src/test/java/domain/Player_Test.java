package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Player_Test
{
    // Seed of three (3) ensures the first Player gets the Mah Jong and
    // starts thus in turn.
    protected static final int SEED = 3;

    /**
     * Helper function to create a Player who starts in turn.
     *
     * @return {Player} - A Player who is in turn.
     */
    private Player createPlayerInTurn() throws CantDrawTooManyTimesException
    {
        Table playingTable = new Table();
        Player player = new Player(playingTable, SEED);

        // Draw Cards to get in turn.
        player.drawCards();
        player.drawCards();

        return player;
    }

    @Test
    public void test_init()
    {
        new Player(new Table());
    }

    @Test
    public void test_neighboursAreNotSelf() throws InvalidPositionException
    {
        Player firstPlayer = new Player(new Table());

        Assert.assertNotEquals(firstPlayer,
            firstPlayer.getPlayerAtPositionCCW(1));
        Assert.assertNotEquals(firstPlayer,
            firstPlayer.getPlayerAtPositionCCW(2));
        Assert.assertNotEquals(firstPlayer,
            firstPlayer.getPlayerAtPositionCCW(3));
    }

    @Test
    public void test_circleIsRound() throws InvalidPositionException
    {
        Player firstPlayer = new Player(new Table());

        Assert.assertEquals(firstPlayer, firstPlayer.getPlayerAtPositionCCW(4));
    }

    @Test(expected = InvalidPositionException.class)
    public void test_cantAskNegativePos() throws InvalidPositionException
    {
        Player firstPlayer = new Player(new Table());

        firstPlayer.getPlayerAtPositionCCW(-1);
    }

    @Test(expected = InvalidPositionException.class)
    public void test_cantAskTooLargePos() throws InvalidPositionException
    {
        Player firstPlayer = new Player(new Table());

        firstPlayer.getPlayerAtPositionCCW(5);
    }

    @Test
    public void test_playersStartsEmptyHanded() throws InvalidPositionException
    {
        Player firstPlayer = new Player(new Table());
        for (int i = 0; i < 4; ++i)
        {
            Assert.assertEquals(0, firstPlayer.getPlayerAtPositionCCW(i).
                getCards().size());
        }
    }

    @Test
    public void test_playersStartNotInTurn() throws InvalidPositionException
    {
        Player firstPlayer = new Player(new Table());
        for (int i = 0; i < 4; ++i)
        {
            Assert.assertFalse(firstPlayer.getPlayerAtPositionCCW(i).
                isInTurn());
        }
    }

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

        Assert.assertEquals(6, firstPlayer.getCards().size());
    }

    @Test
    public void test_drawFirstCardsAreSeeded() throws
        CantDrawTooManyTimesException
    {
        Dealer refDealer = new Dealer(SEED);
        Player firstPlayer = new Player(new Table(), SEED);

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

    @Test
    public void test_canPlayWhenInTurn() throws CantDrawTooManyTimesException
    {
        Player firstPlayer = this.createPlayerInTurn();

        Assert.assertTrue(
            firstPlayer.canPlay(Play_Test_Helper.createSingle(2))
        );
    }

    @Test
    public void test_cantPlayWhenOutOfTurn()
    {
        Player firstPlayer = new Player(new Table());

        Assert.assertFalse(
            firstPlayer.canPlay(Play_Test_Helper.createSingle(2))
        );
    }

    @Test
    public void test_canPlayBombWhenOutOfTurn()
    {
        Player firstPlayer = new Player(new Table());

        Assert.assertTrue(
            firstPlayer.canPlay(Play_Test_Helper.createFOAKBomb(2))
        );
    }

    @Test
    public void test_playWithPlay() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Table playingTable = new Table();
        Player firstPlayer = new Player(playingTable, SEED);

        // Draw Cards to get in turn.
        firstPlayer.drawCards();
        firstPlayer.drawCards();

        Play play = Play_Test_Helper.createSingle(2);
        firstPlayer.play(play);

        Assert.assertEquals(play, playingTable.getLastPlay());
    }

    @Test
    public void test_playWithArrayListOfCards() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Table playingTable = new Table();
        Player firstPlayer = new Player(playingTable, SEED);

        // Draw Cards to get in turn.
        firstPlayer.drawCards();
        firstPlayer.drawCards();

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.MAHJONG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertEquals(
            SpecialRank.MAHJONG.getValue(),
            playingTable.getLastPlay().getValue()
        );
    }

    @Test
    public void test_playRemovesCard() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Table playingTable = new Table();
        Player firstPlayer = new Player(playingTable, SEED);

        // Draw Cards to get in turn.
        firstPlayer.drawCards();
        firstPlayer.drawCards();

        ArrayList<Card> cards = new ArrayList<Card>();

        // Only card we are certain the Player in turn has.
        cards.add(new SpecialCard(SpecialRank.MAHJONG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertEquals(13, firstPlayer.getCards().size());
    }

    @Test
    public void test_playNotRemovesCardWhenOutOfTurn() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Table playingTable = new Table();
        Player firstPlayer = new Player(playingTable, SEED);

        // Draw Cards to get in turn.
        firstPlayer.drawCards();

        ArrayList<Card> cards = new ArrayList<Card>();

        // Only card we are certain the Player in turn has.
        cards.add(new SpecialCard(SpecialRank.MAHJONG));

        try
        {
            firstPlayer.play(cards, Set.SINGLE);
        }
        catch (CantPlayException e)
        {
            System.out.println("We know this error happens," +
                " but we ought to test it.");
        }

        Assert.assertEquals(6, firstPlayer.getCards().size());
    }

    @Test(expected = PlayerDontHasCardException.class)
    public void test_playCantTakeCardsAPlayerDoesntHave() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Table playingTable = new Table();
        Player firstPlayer = new Player(playingTable, SEED);

        // Draw Cards to get in turn.
        firstPlayer.drawCards();
        firstPlayer.drawCards();

        // We don't have the SWORD SEVEN with seed 3.
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SEVEN));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.SEVEN));

        firstPlayer.play(cards, Set.PAIR);
    }

    @Test
    public void test_playCantTakeCardsAPlayerDoesntHaveAndLosesNoCards() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Table playingTable = new Table();
        Player firstPlayer = new Player(playingTable, SEED);

        // Draw Cards to get in turn.
        firstPlayer.drawCards();
        firstPlayer.drawCards();

        // We don't have the SWORD SEVEN with seed 3.
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SEVEN));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.SEVEN));

        try
        {
            firstPlayer.play(cards, Set.PAIR);
        }
        catch (PlayerDontHasCardException e)
        {
            System.out.println("We know this error happens," +
                " but we ought to test it.");
        }

        Assert.assertEquals(14, firstPlayer.getCards().size());
    }
}
