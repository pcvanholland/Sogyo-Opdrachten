package taipan.domain;

import java.util.ArrayList;

public class Player
{
    private static final int NUM_PLAYERS = 4;
    private Dealer dealer;
    private Player neighbour;
    private ArrayList<Card> cards = new ArrayList<Card>();

    private boolean inTurn = false;

    /**
     * The default constructor for a Player.
     * This initiates a chain to make four Players in total.
     */
    Player()
    {
        this.dealer = new Dealer();
        int numberOfPlayersLeftToCreate = NUM_PLAYERS - 1;
        this.neighbour = new Player(this,
            this.dealer, numberOfPlayersLeftToCreate
        );
    }

    /**
     * The constructor for any non-first Player.
     * This continues a chain to make four Players in total.
     *
     * @param firstPlayer {Player} - The Player that initiated the chain.
     * @param sharedDealer {Dealer} - The Dealer accompanies these Players.
     * @param numberOfPlayersLeftToCreate {int} - # Players to be made.
     */
    Player(final Player firstPlayer,
        final Dealer sharedDealer,
        int numberOfPlayersLeftToCreate
    )
    {
        this.dealer = sharedDealer;
        numberOfPlayersLeftToCreate--;
        if (numberOfPlayersLeftToCreate > 0)
        {
            this.neighbour = new Player(firstPlayer,
                sharedDealer,
                numberOfPlayersLeftToCreate
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
     * Returns the Cards that this Player holds.
     *
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
         // ToDo: This ought to be improved!
         if (this.getCards().size() == Dealer.FIRST_HAND_SIZE)
         {
             this.cards.addAll(this.getDealer().drawSecondHand());
         }
         else
         {
             this.cards.addAll(this.getDealer().drawFirstHand());
         }
     }

    /**
     * Returns the Cards that this Player holds.
     *
     * @return {Card[]} - The Cards held by this Player.
     */
     protected boolean isInTurn()
     {
         return this.inTurn;
     }
}
