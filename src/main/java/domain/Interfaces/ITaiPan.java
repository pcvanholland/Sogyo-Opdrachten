package taipan.domain;

import java.util.ArrayList;

public interface ITaiPan
{
    /**
    * Lets the specified Player draw Cards.
    *
    * @param playerNumber {int} - The Player that wants to draw Cards.
    */
    void letPlayerDrawCards(int playerNumber) throws TaiPanException;

    /**
    * Lets the Player pass.
    *
    * @param playerNumber {int} - The Player that wants to pass.
    */
    void pass(int playerNumber) throws TaiPanException;

    /**
     * Returns the types of Play this set of Cards can have.
     *
     * @param cards {Card[]} - An ArrayList of Cards to check.
     * @return {Set[]} - An ArrayList of the types of Plays.
     */
    ArrayList<Set> getTypesOfPlay(ArrayList<Card> cards);

    /**
     * Lets the specified Player play a Play.
     *
     * @param playerNumber {int} - The Player that wants to play Cards.
     * @param cards {Card[]} - An array of the Cards to play.
     * @param type {Set} - What type the Play should be.
     */
    void play(int playerNumber, ArrayList<Card> cards, Set type) throws
        TaiPanException;

    /**
     * @return {Table} - The Table at which the Players play.
     */
    Table getPlayingTable();

    /**
     * @param playerNumber {int} - The Player to query.
     * @return {Player} - The Player at the specified location.
     */
    Player getPlayer(int playerNumber) throws InvalidPositionException;

    /**
     * @return {int[]} - The score of the two teams.
     */
    int[] getScore() throws InvalidPositionException;
}
