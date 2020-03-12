package taipan.domain;

import java.util.ArrayList;

public class TaiPan implements ITaiPan
{
    private Player firstPlayer;
    private Table playingTable;

    /**
     * Default constructor of a Tai-Pan game.
     */
    public TaiPan() throws InvalidRankException
    {
        this.playingTable = new Table();
        this.firstPlayer = new Player(this.playingTable);
    }

    /**
     * Constructor of a Tai-Pan game with a predefined random seed.
     *
     * @param seed {int} - The seed to use for the Dealer.
     */
    public TaiPan(final int seed) throws InvalidRankException
    {
        this.playingTable = new Table();
        this.firstPlayer = new Player(this.playingTable, seed);
    }

    /**
     * Lets the specified Player play a Play.
     *
     * @param playerNumber {int} - The Player that wants to play Cards.
     * @param cards {Card[]} - An array of the Cards to play.
     * @param type {Set} - How the Play should be performed.
     */
    @Override
    public void play(final int playerNumber,
        final ArrayList<Card> cards,
        final Set type
    ) throws TaiPanException
    {
        this.getPlayer(playerNumber).play(
            new CardCollection(cards),
            type
        );
    }

    /**
     * Lets the Player pass.
     *
     * @param playerNumber {int} - The Player that wants to pass.
     */
    @Override
    public void pass(final int playerNumber) throws TaiPanException
    {
        this.getPlayer(playerNumber).pass();
    }

    /**
     * Lets the specified Player draw Cards.
     *
     * @param playerNumber {int} - The Player that wants to draw Cards.
     */
    @Override
    public void letPlayerDrawCards(final int playerNumber) throws
        TaiPanException
    {
        this.getPlayer(playerNumber).drawCards();
    }

    /**
     * Returns the types of Play this set of Cards can have.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of Cards to check.
     * @return {Set[]} - An ArrayList of the types of Plays.
     */
    @Override
    public ArrayList<Set> getTypesOfPlay(final ArrayList<Card> cardsToCheck)
    {
        CardCollection cards = new CardCollection(cardsToCheck);

        return cards.determineTypesOfSet();
    }

    /**
     * @param playerNumber {int} - The Player to query.
     * @return {Player} - The Player at the specified location.
     */
    @Override
    public Player getPlayer(final int playerNumber) throws
        InvalidPositionException
    {
        if (playerNumber < 0 || playerNumber > Player.NUM_PLAYERS)
        {
            throw new InvalidPositionException();
        }
        return this.firstPlayer.getPlayerAtPositionCCW(playerNumber);
    }

    /**
     * @return {Table} - The Table at which the Players play.
     */
    @Override
    public Table getPlayingTable()
    {
        return this.playingTable;
    }

    /**
     * @return {int[]} - The score of the two teams.
     */
    @Override
    public int[] getScore() throws InvalidPositionException
    {
        int[] score = {0, 0};
        score[0] = this.getPlayer(0).getScore() +
            this.getPlayer(2).getScore();
        score[1] = this.getPlayer(1).getScore() +
            this.getPlayer(3).getScore();
        return score;
    }
}
