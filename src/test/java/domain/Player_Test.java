package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Player_Test
{
    // Seed of 3 ensures the first Player to draw Cards gets the turn.
    // Seed of 10 ensures a FOAK Bomb in the first hand.
    // Seed of 100813 is a Straight on the first two hands combined.
    // Seed of 1661 is a Straight Bomb on the first hand.
    // Seed of 28774 is a Straight Bomb on the first hand with MJ.
    protected static final int SEED = 3;
    private static final int START_BOMB_SEED = 28774;
    private static final int START_DOG_SEED = 3;

    /**
     * Helper function to create a Player who starts in turn.
     *
     * @return {Player} - A Player who is in turn.
     */
    private Player createPlayerInTurn() throws CantDrawTooManyTimesException
    {
        return this.createSeededPlayer(SEED);
    }

    /**
     * Helper function to create a Player who starts in turn.
     *
     * @param seed {int} - The seed to use for the Dealer.
     * @return {Player} - A Player who is in turn.
     */
    private Player createSeededPlayer(final int seed) throws
        CantDrawTooManyTimesException
    {
        Player player = new Player(new Table(), seed);

        // Draw Cards to get in turn.
        player.drawCards();
        player.drawCards();

        return player;
    }

    /**
     * Helper function to create a full game.
     *
     * @param seed {int} - The seed to use for the Dealer.
     * @return {Player} - The first Player.
     */
    private Player createSeededGame(final int seed) throws
        CantDrawTooManyTimesException
    {
        Player player = this.createSeededPlayer(seed);
        player.getPlayerAtPositionCCW(1).drawCards();
        player.getPlayerAtPositionCCW(1).drawCards();
        player.getPlayerAtPositionCCW(2).drawCards();
        player.getPlayerAtPositionCCW(2).drawCards();
        player.getPlayerAtPositionCCW(3).drawCards();
        player.getPlayerAtPositionCCW(3).drawCards();

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
    public void test_playersStartWithoutWonTricks() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Player firstPlayer = new Player(new Table());

        Assert.assertEquals(
            new ArrayList<Trick>(),
            firstPlayer.getWonTricks()
        );
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
    public void test_canPlayBombWhenInTurn() throws
        CantDrawTooManyTimesException
    {
        Player firstPlayer = this.createPlayerInTurn();

        Assert.assertTrue(
            firstPlayer.canPlay(Play_Test_Helper.createFOAKBomb(2))
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
    public void test_cantPlayWrongType() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Table playingTable = new Table();
        Player firstPlayer = new Player(playingTable, SEED);
        for (int i = 0; i < Player.NUM_PLAYERS; ++i)
        {
            firstPlayer.getPlayerAtPositionCCW(i).drawCards();
            firstPlayer.getPlayerAtPositionCCW(i).drawCards();
        }

        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertFalse(
            firstPlayer.getPlayerAtPositionCCW(1).
                canPlay(Play_Test_Helper.createPair(2))
        );
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

        // Give the next Player cards to prevent the turn from returning.
        firstPlayer.getPlayerAtPositionCCW(1).drawCards();

        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

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
        Player firstPlayer = this.createPlayerInTurn();

        CardCollection cards = new CardCollection();

        // A Card we are certain the Player in turn has.
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertEquals(13, firstPlayer.getCards().size());
    }

    @Test
    public void test_playNotRemovesCardWhenOutOfTurn() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Table playingTable = new Table();
        Player firstPlayer = new Player(playingTable, SEED);
        firstPlayer.drawCards();
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

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
        Player firstPlayer = this.createPlayerInTurn();

        // We don't have the SWORD SEVEN with seed 3.
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SEVEN));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.SEVEN));

        firstPlayer.play(cards, Set.PAIR);
    }

    @Test
    public void test_playCantTakeCardsAPlayerDoesntHaveAndLosesNoCards() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Player firstPlayer = this.createPlayerInTurn();

        // We don't have the SWORD SEVEN with seed 3.
        CardCollection cards = new CardCollection();
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

    @Test
    public void test_passTurnsPlayerOutOfTurn() throws
        CantDrawTooManyTimesException, CantPlayException,
        CantPassException
    {
        Player firstPlayer = this.createSeededGame(SEED);
        CardCollection cards = new CardCollection();

        // A Card we are certain the Player in turn has.
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));
        firstPlayer.play(cards, Set.SINGLE);

        firstPlayer.getPlayerAtPositionCCW(1).pass();

        Assert.assertFalse(
            firstPlayer.getPlayerAtPositionCCW(1).isInTurn()
        );
    }

    @Test(expected = CantPassException.class)
    public void test_cantPassWhenOutOfTurn() throws
        CantDrawTooManyTimesException, CantPlayException,
        CantPassException
    {
        Player firstPlayer = this.createPlayerInTurn();

        firstPlayer.getPlayerAtPositionCCW(1).pass();
    }

    @Test(expected = CantPassException.class)
    public void test_cantPassWhenLeading() throws
        CantDrawTooManyTimesException, CantPlayException,
        CantPassException
    {
        Player firstPlayer = this.createPlayerInTurn();

        firstPlayer.pass();
    }

    @Test
    public void test_playTurnsPlayerOutOfTurn() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Player firstPlayer = this.createSeededGame(SEED);
        CardCollection cards = new CardCollection();

        // A Card we are certain the Player in turn has.
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertFalse(firstPlayer.isInTurn());
    }

    @Test
    public void test_playGivesTurnToNext() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Player firstPlayer = this.createSeededGame(SEED);
        CardCollection cards = new CardCollection();

        // A Card we are certain the Player in turn has.
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertTrue(firstPlayer.getPlayerAtPositionCCW(1).isInTurn());
    }

    @Test
    public void test_allPassTakesTrick() throws
        CantDrawTooManyTimesException, CantPlayException,
        CantPassException
    {
        Player firstPlayer = this.createSeededGame(SEED);
        CardCollection cards = new CardCollection();

        // A Card we are certain the Player in turn has.
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));
        firstPlayer.play(cards, Set.SINGLE);
        firstPlayer.getPlayerAtPositionCCW(1).pass();
        firstPlayer.getPlayerAtPositionCCW(2).pass();
        firstPlayer.getPlayerAtPositionCCW(3).pass();

        Assert.assertEquals(1, firstPlayer.getWonTricks().size());
    }

    @Test
    public void test_playBombGivesTurnToNeighbour() throws
        CantDrawTooManyTimesException, CantPlayException,
        CantPassException
    {
        Player firstPlayer = new Player(new Table(), START_BOMB_SEED);
        firstPlayer.drawCards();
        CardCollection bomb = new CardCollection();
        for (Card card : firstPlayer.getCards())
        {
            if (card.getRank() != SpecialRank.MAHJONG)
            {
                bomb.add(card);
            }
        }
        firstPlayer.drawCards();
        firstPlayer.getPlayerAtPositionCCW(1).drawCards();
        firstPlayer.getPlayerAtPositionCCW(1).drawCards();
        firstPlayer.getPlayerAtPositionCCW(2).drawCards();
        firstPlayer.getPlayerAtPositionCCW(2).drawCards();
        firstPlayer.getPlayerAtPositionCCW(3).drawCards();
        firstPlayer.getPlayerAtPositionCCW(3).drawCards();

        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));
        firstPlayer.play(cards, Set.SINGLE);
        firstPlayer.getPlayerAtPositionCCW(1).pass();

        firstPlayer.play(bomb, Set.BOMB);

        Assert.assertTrue(firstPlayer.getPlayerAtPositionCCW(1).isInTurn());
    }

    @Test
    public void test_playDogPassesTurnToOpposite() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Player firstPlayer = createSeededGame(START_DOG_SEED);

        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.DOG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertTrue(firstPlayer.getPlayerAtPositionCCW(2).isInTurn());
    }

    @Test
    public void test_playDogLeavesTableEmpty() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Player firstPlayer = createSeededGame(START_DOG_SEED);

        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.DOG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertFalse(firstPlayer.getPlayerAtPositionCCW(2).mayPass());
    }

    @Test
    public void test_emptyHandPassesTurnToNext() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Player firstPlayer = createSeededPlayer(SEED);

        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertTrue(firstPlayer.isInTurn());
    }

    @Test
    public void test_emptyHandPassesTurnToNextWithDog() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Player firstPlayer = createSeededPlayer(START_DOG_SEED);

        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.DOG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertTrue(firstPlayer.isInTurn());
    }
}
