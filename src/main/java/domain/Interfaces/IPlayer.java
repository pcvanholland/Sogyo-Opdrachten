package taipan.domain;

import java.util.ArrayList;

public interface IPlayer
{
    /**
     * @return {Card[]} - The Cards held by this Player.
     */
    ArrayList<Card> getCards();

    /**
     * @return {boolean} - Whether this Player can draw a hand from the Dealer.
     */
    boolean canDrawCards();

    /**
     * @return {boolean} - Whether this Player is in turn.
     */
    boolean isInTurn();

    /**
     * @return {boolean} - Whether this Player may pass.
     */
    boolean mayPass();

    int getScore();
}
