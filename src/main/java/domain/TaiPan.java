package taipan.domain;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class TaiPan
{
    private Player firstPlayer;
    private Table playingTable;

    /**
     * Default constructor of a Tai-Pan game.
     */
    public TaiPan()
    {
        this.playingTable = new Table();
        this.firstPlayer = new Player(this.playingTable);
    }

    /**
     * Constructor of a Tai-Pan game with a predefined random seed.
     *
     * @param seed {int} - The seed to use for the Dealer.
     */
    public TaiPan(final int seed)
    {
        this.playingTable = new Table();
        this.firstPlayer = new Player(this.playingTable, seed);
    }

    /**
     * Lets the specified Player play a Play.
     *
     * @param playerNumber {int} - The Player that wants to play Cards.
     * @param cards {String} - An array of the Cards to play.
     * @param type {String} - How the Play should be performed.
     */
    public void play(final int playerNumber,
        final String cards,
        final String type
    )
    {
        try
        {
            this.getPlayer(playerNumber).play(
                JSONProcessor.createCardsFromJSON(cards),
                Set.valueOf(type)
            );
        }
        catch (CantPlayException e)
        {
            //e.printStackTrace();
            System.out.println(
                "Player " + playerNumber + " has tried to play out of turn."
            );
        }
        catch (PlayerDontHasCardException e)
        {
            //e.printStackTrace();
            System.out.println(
                "Player " + playerNumber + " has tried to play Cards which " +
                "they don't have."
            );
        }
        catch (InvalidPlayException e)
        {
            //e.printStackTrace();
            System.out.println(
                "Player " + playerNumber + " has tried to play an invalid Play."
            );
        }
    }

    /**
     * Lets the Player pass.
     *
     * @param playerNumber {int} - The Player that wants to pass.
     */
    public void pass(final int playerNumber)
    {
        try
        {
            this.getPlayer(playerNumber).pass();
        }
        catch (CantPassException e)
        {
            //e.printStackTrace();
            System.out.println(
                "Player " + playerNumber + " can't pass at the moment."
            );
        }
    }

    /**
     * Gets the current state of the game.
     *
     * @return {JSONObject} - An JSONObject of the GameState.
     */
    public JSONObject getJSONGameState()
    {
        return JSONProcessor.createJSONGame(this);
    }

    /**
     * Lets the specified Player draw Cards.
     *
     * @param playerNumber {int} - The Player that wants to draw Cards.
     */
    public void letPlayerDrawCards(final int playerNumber)
    {
        try
        {
            this.getPlayer(playerNumber).drawCards();
        }
        catch (CantDrawTooManyTimesException e)
        {
            //e.printStackTrace();
            System.out.println(
                "Player " + playerNumber + " has tried draw too many Cards."
            );
        }
    }

    /**
     * Returns the types of Play this set of Cards can have.
     *
     * @param cardsJSON {String} - An JSON-String of Cards to check.
     * @return {String[]} - An ArrayList of the types of Plays.
     */
    public ArrayList<String> getTypesOfPlay(final String cardsJSON)
    {
        CardCollection cards = JSONProcessor.createCardsFromJSON(cardsJSON);

        ArrayList<String> result = new ArrayList<String>();
        for (Set set : cards.determineTypesOfSet())
        {
            result.add(set.toString());
        }
        return result;
    }

    /**
     * @param playerNumber {int} - The Player to query.
     * @return {Player} - The Player at the specified location.
     */
    Player getPlayer(final int playerNumber)
    {
        return this.firstPlayer.getPlayerAtPositionCCW(playerNumber);
    }

    /**
     * @return {Table} - The Table at which the Players play.
     */
    Table getPlayingTable()
    {
        return this.playingTable;
    }
}
