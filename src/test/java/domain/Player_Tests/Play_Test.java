package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Test extends Player_Test_Helper
{
    @Test
    public void test_canPlayWhenInTurn()
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
    public void test_canPlayBombWhenInTurn()
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
        Player firstPlayer = new Player(playingTable, START_SEED);
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
        Player firstPlayer = new Player(playingTable, START_SEED);

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
    public void test_playRemovesCard() throws CantPlayException
    {
        Player firstPlayer = this.createSeededGame(START_SEED);

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
        Player firstPlayer = new Player(playingTable, START_SEED);
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

        Assert.assertEquals(8, firstPlayer.getCards().size());
    }

    @Test(expected = PlayerDontHasCardException.class)
    public void test_playCantTakeCardsAPlayerDoesntHave() throws
        CantPlayException
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
        CantPlayException
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
    public void test_playTurnsPlayerOutOfTurn() throws CantPlayException
    {
        Player firstPlayer = this.createSeededGame(START_SEED);
        CardCollection cards = new CardCollection();

        // A Card we are certain the Player in turn has.
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertFalse(firstPlayer.isInTurn());
    }

    @Test
    public void test_playGivesTurnToNext() throws CantPlayException
    {
        Player firstPlayer = this.createSeededGame(START_SEED);
        CardCollection cards = new CardCollection();

        // A Card we are certain the Player in turn has.
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertTrue(firstPlayer.getPlayerAtPositionCCW(1).isInTurn());
    }

    @Test
    public void test_playBombGivesTurnToNeighbour() throws
        CantDrawTooManyTimesException, CantPlayException,
        CantPassException
    {
        Player firstPlayer = createSeededGame(START_BOMB_SEED);
        CardCollection bomb = new CardCollection();
        ArrayList<Card> playerCards = firstPlayer.getCards();
        for (int i = 0; i < 6; ++i)
        {
            Card card = playerCards.get(i);
            if (card.getRank() != SpecialRank.MAHJONG)
            {
                bomb.add(card);
            }
        }

        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));
        firstPlayer.play(cards, Set.SINGLE);
        firstPlayer.getPlayerAtPositionCCW(1).pass();

        firstPlayer.play(bomb, Set.BOMB);

        Assert.assertTrue(firstPlayer.getPlayerAtPositionCCW(1).isInTurn());
    }

    @Test
    public void test_playDogPassesTurnToOpposite() throws CantPlayException
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
        Player firstPlayer = createSeededPlayer(START_SEED);
        firstPlayer.getPlayerAtPositionCCW(2).drawCards();

        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertTrue(firstPlayer.getPlayerAtPositionCCW(2).isInTurn());
    }

    @Test
    public void test_emptyHandPassesTurnToNextWithDog() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Player firstPlayer = createSeededPlayer(START_DOG_SEED);
        firstPlayer.getPlayerAtPositionCCW(3).drawCards();

        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.DOG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertTrue(firstPlayer.getPlayerAtPositionCCW(3).isInTurn());
    }

    @Test
    public void test_lastPlayerStopsRound() throws CantPlayException
    {
        Player firstPlayer = createPlayerInTurn();

        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertEquals(0, firstPlayer.getCards().size());
    }

    @Test
    public void test_roundEndCanRedraw() throws CantPlayException
    {
        Player firstPlayer = createPlayerInTurn();

        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        firstPlayer.play(cards, Set.SINGLE);

        for (int i = 0; i < Player.NUM_PLAYERS; ++i)
        {
            Assert.assertTrue(
                firstPlayer.getPlayerAtPositionCCW(i).canDrawCards()
            );
        }
    }

    @Test
    public void test_roundEndRedrawFillsPlayers() throws
        CantDrawTooManyTimesException, CantPlayException
    {
        Player firstPlayer = createPlayerInTurn();

        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        firstPlayer.play(cards, Set.SINGLE);

        for (int i = 0; i < Player.NUM_PLAYERS; ++i)
        {
            firstPlayer.getPlayerAtPositionCCW(i).drawCards();
            firstPlayer.getPlayerAtPositionCCW(i).drawCards();
            Assert.assertEquals(
                14,
                firstPlayer.getPlayerAtPositionCCW(i).getCards().size()
            );
        }
    }
}
