package taipan.domain;

import java.util.ArrayList;

public class Player
{
    protected static final int NUM_PLAYERS = 4;

    private int id;
    private Dealer dealer;
    private Player neighbour;

    private ArrayList<Card> cards = new ArrayList<Card>();
    private boolean inTurn = false;
    private int handsDrawn = 0;

    /**
     * The default constructor for a Player.
     * This initiates a chain to make four Players in total.
     */
    Player()
    {
        this.dealer = new Dealer();
        int numberOfPlayersCreated = 0;
        this.id = numberOfPlayersCreated++;
        this.neighbour = new Player(this,
            this.dealer, numberOfPlayersCreated
        );
    }

    /**
     * The constructor for any non-first Player.
     * This continues a chain to make four Players in total.
     *
     * @param firstPlayer {Player} - The Player that initiated the chain.
     * @param sharedDealer {Dealer} - The Dealer accompanying these Players.
     * @param numberOfPlayersCreated {int} - # Players to already made.
     */
    Player(final Player firstPlayer,
        final Dealer sharedDealer,
        int numberOfPlayersCreated
    )
    {
        this.dealer = sharedDealer;
        this.id = numberOfPlayersCreated++;
        if (numberOfPlayersCreated < NUM_PLAYERS)
        {
            this.neighbour = new Player(firstPlayer,
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
    protected final Player getPlayerAtPositionCCW(int position)
    {
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
     * @return {Card[]} - The Cards held by this Player.
     */
    protected ArrayList<Card> getCards()
    {
        return this.cards;
    }

    /**
     * Ask a hand of Cards from the Dealer.
     */
    protected void drawCards()
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
    protected boolean canDrawCards()
    {
        return this.handsDrawn() < 2;
    }

    /**
     * @return {boolean} - Whether this player is in turn.
     */
    protected boolean isInTurn()
    {
        return this.inTurn;
    }

    /**
     * @return {int} - The playerID associated with this Player.
     */
    protected int getPlayerID()
    {
        return this.id;
    }
}
