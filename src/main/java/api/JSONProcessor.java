package taipan.api;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This is a class that processes JSON to String and vice versa.
 */
public abstract class JSONProcessor
{
    /**
     * @param textfornow {String} - Some text to JSONify.
     * @return {String} - JSONString of the given text.
     */
    public static String createJSONResponse(final String textfornow)
    {
        JSONObject result = new JSONObject();
        result.put("result", textfornow);

        return result.toJSONString();
    }

    /**
     * This creates a JSONified Game list.
     *
     * @param games {Game[]} - An ArrayList of active games.
     * @return {String} - A JSON-String representing the active games.
     */
    public static String createJSONGameList(
        final ArrayList<Game> games
    )
    {
        JSONArray result = new JSONArray();
        for (Game game : games)
        {
            JSONObject gameJSON = new JSONObject();
            gameJSON.put("id", game.getID());
            gameJSON.put("host", game.getHostName());
            gameJSON.put("full", game.isFull());
            result.add(gameJSON);
        }
        return result.toJSONString();
    }

    /**
     * This converts a TaiPan GameState to a JSONString.
     *
     * @param game {TaiPan} - A TaiPan-game to get the GameState from.
     * @param play {String} - A JSONified set of Cards.
     *
     * @return {String} - A JSON-String representing the gamestate.
     */
    public static String createJSONPlayTypes(
        final taipan.domain.TaiPan game,
        final String play
    )
    {
        JSONObject result = new JSONObject();
        JSONArray sets = new JSONArray();

        if (play.length() > 2)
        {
            ArrayList<taipan.domain.Set> types = game.getTypesOfPlay(
                createCardsFromJSON(play)
            );
            for (taipan.domain.Set type : types)
            {
                sets.add(type.toString());
            }
        }

        result.put("sets", sets);
        return result.toJSONString();
    }

    /**
     * Squashes a Tai-Pan Game into a JSONObject.
     *
     * @param game {TaiPan} - The Game to JSONify.
     * @return {JSONObject} - The JSON representation of the Game.
     */
    static JSONObject createJSONGameState(final taipan.domain.TaiPan game)
    {
        JSONObject result = new JSONObject();
        result.put("players", createJSONPlayers(game));
        result.put("table", createJSONTable(game.getPlayingTable()));
        return result;
    }

    /**
     * Squashes the Players of a Game into a JSONArray.
     *
     * @param game {TaiPan} - The game to JSONify the Players of.
     * @return {JSONArray} - The JSON representation of the Players.
     */
    static JSONArray createJSONPlayers(final taipan.domain.TaiPan game)
    {
        JSONArray result = new JSONArray();
        for (int i = 0; i < 4; ++i)
        {
            JSONObject player = createJSONPlayer(game.getPlayer(i));
            player.put("id", i);
            result.add(player);
        }
        return result;
    }

    /**
     * Squashes a Player to a JSONObject.
     *
     * @param player {Player} - The Player to JSONify.
     * @return {JSONObject} - The JSON representation of the Player.
     */
    static JSONObject createJSONPlayer(final taipan.domain.Player player)
    {
        JSONObject result = new JSONObject();
        result.put("cards", createJSONCards(player.getCards()));
        result.put("inTurn", player.isInTurn());
        result.put("mayPass", player.mayPass());
        result.put("canDraw", player.canDrawCards());
        result.put("score", player.getScore());

        return result;
    }

    /**
     * Squashes a Table to a JSONArray.
     *
     * @param table {Table} - The Table to JSONify.
     * @return {JSONObject} - The JSON representation of the Table.
     */
    static JSONObject createJSONTable(final taipan.domain.Table table)
    {
        JSONObject result = new JSONObject();
        result.put("trick", createJSONPlays(
            table.getCurrentPlays())
        );

        return result;
    }

    /**
     * Squashes Plays to a JSONArray.
     *
     * @param plays {Play[]} - The Plays to JSONify.
     * @return {JSONArray} - The JSON representation of the Plays.
     */
    private static JSONArray createJSONPlays(
        final ArrayList<taipan.domain.Play> plays
    )
    {
        JSONArray result = new JSONArray();
        for (taipan.domain.Play play : plays)
        {
            result.add(createJSONPlay(play));
        }

        return result;
    }

    /**
     * Squashes a Play to a JSONObject.
     *
     * @param play {Play} - The Play to JSONify.
     * @return {JSONObject} - The JSON representation of the Play.
     */
    private static JSONObject createJSONPlay(final taipan.domain.Play play)
    {
        JSONObject result = new JSONObject();
        result.put("cards", createJSONCards(play.getCards()));

        return result;
    }

    /**
     * Squashes an ArrayList of Cards to a JSONObject.
     *
     * @param cards {Card[]} - The Cards to JSONify.
     * @return {JSONArray} - The JSON representation of the Cards.
     */
    static JSONArray createJSONCards(
        final ArrayList<taipan.domain.Card> cards
    )
    {
        JSONArray result = new JSONArray();
        for (taipan.domain.Card card : cards)
        {
            result.add(createJSONCard(card));
        }
        return result;
    }

    /**
     * Squashes a Card to a JSONObject.
     *
     * @param card {Card} - The Card to JSONify.
     * @return {JSONObject} - The JSON representation of the Card.
     */
    static JSONObject createJSONCard(final taipan.domain.Card card)
    {
        JSONObject result = new JSONObject();
        result.put("suit", card.getSuit().toString());
        result.put("rank", card.getRank().toString());

        return result;
    }

    /**
     * This converts a JSONified array of Cards to an ArrayList of Cards.
     *
     * @param cards {String} - A JSONified String of Cards.
     * @return {Card[]} - An ArrayList of the Cards.
     */
    static ArrayList<taipan.domain.Card> createCardsFromJSON(
        final String cards
    )
    {
        ArrayList<taipan.domain.Card> realCards =
            new ArrayList<taipan.domain.Card>();
        if (cards.equals("[]"))
        {
            return realCards;
        }
        String process = cards.substring(2, cards.length() - 2);
        String[] splitCards = process.split("\",\"");
        for (String card : splitCards)
        {
            taipan.domain.Card realCard;
            String[] cardProps = card.split(",");
            if (cardProps[0].equals("SPECIAL"))
            {
                realCard = taipan.domain.SpecialCard.createSpecialCard(
                    taipan.domain.SpecialRank.valueOf(cardProps[1])
                );
            }
            else
            {
                realCard = new taipan.domain.PlayingCard(
                    taipan.domain.StandardSuit.valueOf(cardProps[0]),
                    taipan.domain.StandardRank.valueOf(cardProps[1])
                );
            }
            realCards.add(realCard);
        }
        return realCards;
    }

    /**
     * This converts a JSONified set to a Set-type.
     *
     * @param set {String} - The JSON-String to convert.
     * @return {Set} - The type of Set.
     */
    static taipan.domain.Set createSetFromJSON(final String set)
    {
        return taipan.domain.Set.valueOf(set);
    }
}
