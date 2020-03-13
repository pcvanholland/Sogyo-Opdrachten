package taipan.domain;

import java.util.ArrayList;

public final class Player implements IPlayer
{
    static final int NUM_PLAYERS = 4;

    private Table table;
    private Dealer dealer;
    private Player neighbour;

    private int handsDrawn = 0;
    private final Player[] finishedPlayers = new Player[4];
    private final ArrayList<Card> cards = new ArrayList<Card>();
    private final ArrayList<Trick> wonTricks = new ArrayList<Trick>();
    private boolean inTurn = false;
    private int score = 0;

    /**
     * The default constructor for the first Player.
     * This initiates a chain to make four Players in total.
     *
     * @param sharedTable {Table} - The Table these Players exist around.
     */
    Player(final Table sharedTable) throws InvalidRankException
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
    Player(final Table sharedTable, final int seed) throws InvalidRankException
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
    Player getPlayerAtPositionCCW(int position) throws
        InvalidPositionException
    {
        if (position < 0)
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
    @Override
    public ArrayList<Card> getCards()
    {
        return this.cards;
    }

    /**
     * Ask a hand of Cards from the Dealer.
     */
    void drawCards() throws
        CantDrawTooManyTimesException, DealerOutOfCardsException
    {
        if (this.handsDrawn() == 0)
        {
            this.addCards(this.getDealer().drawFirstHand());
            this.handsDrawn++;
        }
        else if (this.handsDrawn() == 1)
        {
            this.addCards(this.getDealer().drawSecondHand());
            this.handsDrawn++;
            this.inTurn = this.hasMahJong();
        }
        else
        {
            throw new CantDrawTooManyTimesException();
        }
    }

    /**
     * Adds the specified Cards to this Player's Cards.
     * PackageProtected so we can use it in our tests.
     *
     * @param cardsToAdd {Card[]} - The ArrayList of Cards to add.
     */
    void addCards(final ArrayList<Card> cardsToAdd)
    {
        for (Card card : cardsToAdd)
        {
            this.addCard(card);
        }
    }

    /**
     * Adds a specified Card to this Player's hand of Cards.
     *
     * @param cardToAdd {Card} - The Card to add.
     */
    private void addCard(final Card cardToAdd)
    {
        this.cards.add(cardToAdd);
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
    @Override
    public boolean canDrawCards()
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
    @Override
    public boolean isInTurn()
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
    private void play(final Play play) throws CantPlayTableException,
        InvalidPositionException, InvalidRankException
    {
        this.getTable().play(play);
        this.handleTurnPassing(play);
        this.checkAndHandleLastCardsPlayed();
    }

    /**
     * @param cardsToPlay {Card[]} - Cards to play.
     * @param type {Set} - The type of combination to play the Cards as.
     */
    void play(
        final CardCollection cardsToPlay,
        final Set type
    ) throws CantPlayException, PlayerDontHasCardException,
        InvalidPlayException, InvalidPositionException, InvalidRankException
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
    private void handleTurnPassing(final Play play) throws
        InvalidPositionException, InvalidRankException
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
            this.passTurnToNeighbour();
        }
    }

    /**
     * Passes the turn from this Player to their neighbour
     * without playing any Card.
     */
    void pass() throws CantPassException, InvalidPositionException,
        InvalidRankException
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
    private void passTurnToPlayer(final Player player) throws
        InvalidPositionException, InvalidRankException
    {
        this.takeTurn();
        player.giveTurn();
    }

    /**
     * Passes the turn the the neighbouring Player.
     */
    private void passTurnToNeighbour() throws
        InvalidPositionException, InvalidRankException
    {
        this.passTurnToPlayerAtPosition(1);
    }

    /**
     * Passes the turn to the Player at the specified location, CCW.
     *
     * @param position {int} - The relative position of the Player
     *                      to give the turn to.
     */
    private void passTurnToPlayerAtPosition(int position) throws
        InvalidPositionException, InvalidRankException
    {
        this.takeTurn();
        if (position == 0)
        {
            this.giveTurn();
        }
        else if (position > 0)
        {
            this.getNeighbour().passTurnToPlayerAtPosition(--position);
        }
        else
        {
            throw new InvalidPositionException();
        }
    }

    /**
     * @return {boolean} - Whether this Player may pass.
     */
    @Override
    public boolean mayPass()
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
    private void handleBombing() throws
        InvalidPositionException, InvalidRankException
    {
        this.stealsTurn(this.getNeighbour());
    }

    /**
     * This gives the turn to the specified Player when we were in turn.
     *
     * @param player {Player} - The Player to pass the turn to.
     */
    private void stealsTurn(final Player player) throws
        InvalidPositionException, InvalidRankException
    {
        if (this.isInTurn())
        {
            this.passTurnToPlayer(player);
        }
        else
        {
            this.getNeighbour().stealsTurn(player);
        }
    }

    /**
     * Gives the turn to this Player.
     */
    private void giveTurn() throws
        InvalidPositionException, InvalidRankException
    {
        this.inTurn = true;
        this.handleTurnRecieve();
    }

    /**
     * Handles the receiving of a turn.
     */
    private void handleTurnRecieve() throws
        InvalidPositionException, InvalidRankException
    {
        this.checkAndHandleWinTrick();
        this.checkAndHandleEmptyCarded();
        this.checkAndHandleLastPlayerOut();
    }

    /**
     * Checks whether the passing has gone round and the trick thus needs
     * to be won.
     */
    private void checkAndHandleWinTrick()
    {
        if (this.checkWinTrick())
        {
            this.getTable().giveTrickToWinner();
        }
    }

    /**
     * Gives a trick to this Player.
     *
     * @param trickToGive {Trick} - The Trick to give to this Player.
     */
    void giveTrick(final Trick trickToGive)
    {
        this.getWonTricks().add(trickToGive);
    }

    /**
     * @return {boolean} - Whether the current Trick has gone round.
     */
    private boolean checkWinTrick()
    {
        return this.getTable().getCurrentTrick() != null &&
            this.getTable().getLastPlay().getOwner() == this;
    }

    /**
     * Checks whether the Player has no more Cards left and thus needs to
     * give the turn to its neighbour.
     */
    private void checkAndHandleEmptyCarded() throws
        InvalidPositionException, InvalidRankException
    {
        if (this.checkEmptyCarded())
        {
            this.passTurnToNeighbour();
        }
    }

    /**
     * @return {boolean} - Whether this Player has no Cards left.
     */
    private boolean checkEmptyCarded()
    {
        return this.getCards().isEmpty();
    }

    /**
     * Checks whether this is the last Player with Cards left
     * which means the round is over.
     */
    private void checkAndHandleLastPlayerOut() throws InvalidRankException
    {
        if (this.isLastPlayerWithCards())
        {
            this.handleRoundEnd();
        }
    }

    /**
     * Handles when this Player just played its last Cards.
     */
    private void checkAndHandleLastCardsPlayed()
    {
        if (this.checkEmptyCarded())
        {
            this.declareFinished();
        }
    }

    /**
     * @return {Trick[]} - The Tricks won by this Player.
     */
    private ArrayList<Trick> getWonTricks()
    {
        return this.wonTricks;
    }

    /**
     * Handles a Dog being played.
     */
    private void handleDogging() throws
        InvalidPositionException, InvalidRankException
    {
        this.checkAndHandleWinTrick();
        this.passTurnToPlayerAtPosition(2);
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
    private void handleRoundEnd() throws InvalidRankException
    {
        this.takeTurn();
        this.getTable().handleRoundEnd();
        this.getDealer().reset();

        this.getNeighbour().handleRoundEnd(this);
    }

    /**
     * Handles the ending of the round started by the specified Player.
     *
     * @param player {Player} - The Player who initiated the chain.
     */
    private void handleRoundEnd(final Player player)
    {
        this.coinScore();
        this.getCards().clear();
        this.resetHandsDrawn();

        if (player != this)
        {
            this.getNeighbour().handleRoundEnd(player);
        }
    }

    /**
     * Resets the hands drawn to zero.
     */
    private void resetHandsDrawn()
    {
        this.handsDrawn = 0;
    }

    /**
     * Coins the potential score to actual scoring.
     */
    private void coinScore()
    {
        for (Trick trick : this.getWonTricks())
        {
            this.score += trick.getScore();
        }
        for (Card card : this.getCards())
        {
            this.score += card.getScore();
        }
    }

    /**
     * @return {int} - The current score of this Player.
     */
    int getScore()
    {
        return this.score;
    }

    /**
     * Clears the array of finished Players to null.
     */
    private void clearFinishedPlayers()
    {
        for (int i = 0; i < this.getFinishedPlayers().length; ++i)
        {
            finishedPlayers[i] = null;
        }
    }

    /**
     * @return {Player[]} - The order at which the Players
     *                  got rid of their Cards.
     */
    private Player[] getFinishedPlayers()
    {
        return this.finishedPlayers;
    }

    /**
     * @return {Player} - The Player who first got rid of their Cards.
     */
    Player getFirstPlayerOut()
    {
        return this.getFinishedPlayers()[0];
    }

    /**
     * Declares that this Player is finished to all Players.
     */
    private void declareFinished()
    {
        this.getNeighbour().declareFinished(this);
    }

    /**
     * Is called by a Player who has played their last Card.
     *
     * @param {Player} - The calling Player.
     */
    private void declareFinished(final Player player)
    {
        for (int i = 0; i < this.getFinishedPlayers().length; ++i)
        {
            if (finishedPlayers[i] == null)
            {
                finishedPlayers[i] = player;
                break;
            }
        }
        if (player != this)
        {
            this.getNeighbour().declareFinished(player);
        }
    }
}
