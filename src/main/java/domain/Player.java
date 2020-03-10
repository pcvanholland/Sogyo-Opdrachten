package taipan.domain;

import java.util.ArrayList;

final class Player
{
    static final int NUM_PLAYERS = 4;

    private Table table;
    private Dealer dealer;
    private Player neighbour;

    private ArrayList<Card> cards = new ArrayList<Card>();
    private ArrayList<Trick> wonTricks = new ArrayList<Trick>();
    private boolean inTurn = false;
    private int handsDrawn = 0;

    /**
     * The default constructor for the first Player.
     * This initiates a chain to make four Players in total.
     *
     * @param sharedTable {Table} - The Table these Players exist around.
     */
    Player(final Table sharedTable)
    {
        this(sharedTable, new Dealer());
    }

    /**
     * The constructor for the first Player with a predefined seed.
     * This initiates a chain to make four Players in total.
     *
     * @param sharedTable {Table} - The Table these Players exist around.
     * @param seed {int} - The seed to use for the Dealer.
     */
    Player(final Table sharedTable, final int seed)
    {
        this(sharedTable, new Dealer(seed));
    }

    /**
     * The constructor for the first Player with a predefined sharedDealer.
     * This initiates a chain to make four Players in total.
     *
     * @param sharedTable {Table} - The Table these Players exist around.
     * @param sharedDealer {Dealer} - The Dealer accompanying these Players.
     */
    private Player(final Table sharedTable, final Dealer sharedDealer)
    {
        int numberOfPlayersCreated = 0;

        this.table = sharedTable;
        this.dealer = sharedDealer;

        this.neighbour = new Player(this, sharedTable,
            sharedDealer, ++numberOfPlayersCreated
        );
    }

    /**
     * The constructor for any non-first Player.
     * This continues a chain to make four Players in total.
     *
     * @param firstPlayer {Player} - The Player that initiated the chain.
     * @param sharedTable {Table} - The Table these Players exist around.
     * @param sharedDealer {Dealer} - The Dealer accompanying these Players.
     * @param numberOfPlayersCreated {int} - # Players already made.
     */
    private Player(final Player firstPlayer,
        final Table sharedTable,
        final Dealer sharedDealer,
        int numberOfPlayersCreated
    )
    {
        this.table = sharedTable;
        this.dealer = sharedDealer;

        if (++numberOfPlayersCreated < NUM_PLAYERS)
        {
            this.neighbour = new Player(firstPlayer,
                sharedTable,
                sharedDealer,
                numberOfPlayersCreated
            );
        }
        else
        {
            this.neighbour = firstPlayer;
        }
    }

    /**
     * Returns the Player at the specified position CCW.
     *
     * @param position {int} - The position which ought to be returned.
     * @return {Player} - The Player at the specified position.
     */
    final Player getPlayerAtPositionCCW(int position) throws
        InvalidPositionException
    {
        if (position < 0 || position > NUM_PLAYERS)
        {
            throw new InvalidPositionException();
        }
        return position == 0 ? this :
            this.getNeighbour().getPlayerAtPositionCCW(--position);
    }

    /**
     * Returns the neighbour of this Player.
     * Please remember the game goes CCW.
     *
     * @return {Player} - The neighboring Player.
     */
    private Player getNeighbour()
    {
        return this.neighbour;
    }

    /**
     * Returns the shared Dealer.
     *
     * @return {Dealer} - The Card Dealer.
     */
    private Dealer getDealer()
    {
        return this.dealer;
    }

    /**
     * @return {Table} - The Table associated with the Players.
     */
    private Table getTable()
    {
        return this.table;
    }

    /**
     * @return {Card[]} - The Cards held by this Player.
     */
    ArrayList<Card> getCards()
    {
        return this.cards;
    }

    /**
     * Ask a hand of Cards from the Dealer.
     */
    void drawCards() throws CantDrawTooManyTimesException
    {
        if (this.handsDrawn() == 0)
        {
            this.cards.addAll(this.getDealer().drawFirstHand());
            this.handsDrawn++;
        }
        else if (this.handsDrawn() == 1)
        {
            this.cards.addAll(this.getDealer().drawSecondHand());
            this.handsDrawn++;
            this.inTurn = this.hasMahJong();
        }
        else
        {
            throw new CantDrawTooManyTimesException();
        }
    }

    /**
     * @return {int} - How many times this Player has
     *                drawn a hand from the Dealer.
     */
    private int handsDrawn()
    {
        return this.handsDrawn;
    }

    /**
     * @return {boolean} - Whether this Player can draw a hand from the Dealer.
     */
    boolean canDrawCards()
    {
        return this.handsDrawn() < 2;
    }

    /**
     * @return {boolean} - Whether this Player currently has the Mah Jong.
     */
    private boolean hasMahJong()
    {
        for (Card card : this.getCards())
        {
            if (card.getRank() == SpecialRank.MAHJONG)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @return {boolean} - Whether this Player is in turn.
     */
    boolean isInTurn()
    {
        return this.inTurn;
    }

    /**
     * @param play {Play} - The Play to verify.
     * @return {boolean} - Whether this Player can play the specified Play.
     */
    boolean canPlay(final Play play)
    {
        return (this.isInTurn() || play instanceof Bomb) &&
            this.getTable().canPlay(play);
    }

    /**
     * @param play {Play} - The Play to play.
     */
    private void play(final Play play) throws
        CantPlayTableException
    {
        this.getTable().play(play);
        this.handleTurnPassing(play);
    }

    /**
     * @param cardsToPlay {Card[]} - Cards to play.
     * @param type {Set} - The type of combination to play the Cards as.
     */
    void play(
        final CardCollection cardsToPlay,
        final Set type
    ) throws CantPlayException
    {
        if (!this.hasCards(cardsToPlay))
        {
            throw new PlayerDontHasCardException();
        }
        if (!this.canPlay(cardsToPlay.createPlay(this, type)))
        {
            throw new CantPlayPlayerException();
        }
        CardCollection takenCards = this.takeCards(cardsToPlay);
        this.play(takenCards.createPlay(this, type));
    }

    /**
     * This is a helper function that removes certain Cards from
     * this Players deck.
     *
     * @param cardsToTake {Card[]} - The type of Cards to take.
     * @return {Card[]} - The corresponding Cards taken from the Player's deck.
     */
    private CardCollection takeCards(final CardCollection cardsToTake)
    {
        CardCollection takenCards = new CardCollection();
        for (Card card : cardsToTake.getCards())
        {
            for (int i = 0; i < this.cards.size(); ++i)
            {
                if (this.cards.get(i).equals(card))
                {
                    takenCards.add(this.cards.remove(i));
                    break;
                }
            }
        }
        return takenCards;
    }

    /**
     * This checks whether a Player has all the specified Cards from
     * in their deck.
     *
     * @param cardsToCheck {Card[]} - The type of Cards to check.
     * @return {boolean} - Whether all the Cards are in the Player's deck.
     */
    private boolean hasCards(final CardCollection cardsToCheck)
    {
        for (Card card : cardsToCheck.getCards())
        {
            boolean hasCard = false;
            for (int i = 0; i < this.cards.size(); ++i)
            {
                if (this.cards.get(i).equals(card))
                {
                    hasCard = true;
                    break;
                }
            }
            if (!hasCard)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * This handles the end of a Play, where the turn needs to be reset.
     *
     * @param play {Play} - The Play which was played.
     */
    private void handleTurnPassing(final Play play)
    {
        if (play instanceof Bomb)
        {
            this.handleBombing();
        }
        else if (play.getCards().get(0).getRank() == SpecialRank.DOG)
        {
            this.handleDogging();
        }
        else
        {
            this.passTurnTo(this.getNeighbour());
        }
    }

    /**
     * Passes the turn from this Player to their neighbour
     * without playing any Card.
     */
    void pass() throws CantPassException
    {
        if (!this.mayPass())
        {
            throw new CantPassException();
        }
        this.passTurnToNeighbour();
    }

    /**
     * Passes the turn from this Player to the specified Player.
     *
     * @param player {Player} - The Player to pass the turn to.
     */
    private void passTurnTo(final Player player)
    {
        this.takeTurn();
        player.giveTurn();
    }

    /**
     * Passes the turn the the neighbouring Player.
     */
    private void passTurnToNeighbour()
    {
        this.passTurnTo(this.getNeighbour());
    }

    /**
     * @return {boolean} - Whether this Player may pass.
     */
    boolean mayPass()
    {
        return this.isInTurn() &&
            this.getTable().getCurrentPlays().size() > 0;
    }

    /**
     * Takes the turn from this Player.
     */
    private void takeTurn()
    {
        this.inTurn = false;
    }

    /**
     * This steals the turn from whoever is currently in turn.
     * And gives the turn to the neighbour.
     */
    private void handleBombing()
    {
        this.getNeighbour().stealsTurn(this.getNeighbour());
    }

    /**
     * This gives the turn to the specified Player when we were in turn.
     *
     * @param player {Player} - The Player to pass the turn to.
     */
    private void stealsTurn(final Player player)
    {
        if (this.isInTurn())
        {
            this.passTurnTo(player);
        }
        else
        {
            this.getNeighbour().stealsTurn(player);
        }
    }

    /**
     * Gives the turn to this Player.
     */
    private void giveTurn()
    {
        this.inTurn = true;
        this.handleTurnRecieve();
    }

    /**
     * Handles the receiving of a turn.
     */
    private void handleTurnRecieve()
    {
        this.checkWinTrick();
        this.checkEmptyCarded();
        if (this.isLastPlayerWithCards())
        {
            this.handleRoundEnd();
        }
    }

    /**
     * Checks whether the passing has gone round and the trick thus needs
     * to be won.
     */
    private void checkWinTrick()
    {
        if (this.getTable().getCurrentTrick() != null &&
            this.getTable().getLastPlay().getOwner() == this)
        {
            this.wonTricks.add(this.getTable().giveTrick());
        }
    }

    /**
     * Checks whether the Player has no more Cards left and thus needs to
     * give the turn to its neighbour.
     */
    private void checkEmptyCarded()
    {
        if (this.getCards().size() == 0)
        {
            this.passTurnToNeighbour();
        }
    }

    /**
     * @return {Trick[]} - The Tricks won by this Player.
     */
    ArrayList<Trick> getWonTricks()
    {
        return this.wonTricks;
    }

    /**
     * Handles a Dog being played.
     */
    private void handleDogging()
    {
        this.checkWinTrick();
        this.passTurnTo(this.getPlayerAtPositionCCW(2));
    }

    /**
     * Checks whether this is the last Player with Cards left.
     * This initiates a questioning circle.
     *
     * @return {boolean} - Whether this is the only Player with Cards left.
     */
    private boolean isLastPlayerWithCards()
    {
        return this.getNeighbour().isLastPlayerWithCards(this);
    }

    /**
     * Checks whether this is the last Player with Cards left.
     *
     * @param player {Player} - The Player that asked first.
     * @return {boolean} - Whether there is only one Player with Cards left.
     */
    private boolean isLastPlayerWithCards(final Player player)
    {
        if (player == this)
        {
            return true;
        }
        if (this.getCards().size() > 0)
        {
            return false;
        }
        return this.getNeighbour().isLastPlayerWithCards(player);
    }

    /**
     * Handle the end of a round.
     */
    private void handleRoundEnd()
    {
        this.inTurn = false;
        this.getTable().clear();
        this.getNeighbour().handleRoundEnd(this);
    }

    /**
     * Handles the ending of the round started by the specified Player.
     *
     * @param player {Player} - The Player who initiated the chain.
     */
    private void handleRoundEnd(final Player player)
    {
        this.getDealer().reset();
        this.getCards().clear();
        this.handsDrawn = 0;

        if (player == this)
        {
            return;
        }
        this.getNeighbour().handleRoundEnd(player);
    }
}
