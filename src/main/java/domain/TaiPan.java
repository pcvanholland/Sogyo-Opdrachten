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
     * @param cards {String[]} - An array of the Cards to play.
     * @param type {String} - How the Play should be performed.
     */
    public void play(final int playerNumber,
        final String[] cards,
        final String type
    )
    {
        Player player = this.getPlayer(playerNumber);
        System.out.println("Nothingness");
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
        this.getPlayer(playerNumber).drawCards();
    }

    /**
     * Returns the types of Play this set of Cards can have.
     *
     * @param cards {String[]} - An ArrayList of Cards to check.
     * @return {String[]} - An ArrayList of the types of Plays.
     */
    public ArrayList<String> getTypesOfPlay(final String[] cards)
    {
        ArrayList<Card> realCards = new ArrayList<Card>();
        for (String card : cards)
        {
            Card realCard;
            String[] cardProps = card.split(",");
            if (cardProps[0].equals("SPECIAL"))
            {
                realCard = new SpecialCard(SpecialRank.valueOf(cardProps[1]));
            }
            else
            {
                realCard = new PlayingCard(
                    StandardSuit.valueOf(cardProps[0]),
                    StandardRank.valueOf(cardProps[1])
                );
            }
            realCards.add(realCard);
        }

        ArrayList<String> result = new ArrayList<String>();
        for (Set set : PlayHelper.determineTypesOfSet(realCards))
        {
            result.add(set.toString());
        }
        return result;
    }

    /**
     * @param playerNumber {int} - The Player to query.
     * @return {Player} - The Player at the specified location.
     */
    protected Player getPlayer(final int playerNumber)
    {
        return this.firstPlayer.getPlayerAtPositionCCW(playerNumber);
    }
}
