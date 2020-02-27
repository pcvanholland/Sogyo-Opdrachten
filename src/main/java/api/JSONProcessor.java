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
/*
        JSONObject jsonPlayer1 = this.createJSONPlayer(1, mancala, 1);
        JSONObject jsonPlayer2 = this.createJSONPlayer(8, mancala, 2);

        JSONArray players = new JSONArray();
        players.add(jsonPlayer1);
        players.add(jsonPlayer2);

        result.put("players", players);

        JSONObject gameStatus = new JSONObject();
        if (mancala.isEndOfGame()) {
            gameStatus.put("endOfGame", "true");
            gameStatus.put("winner", mancala.getWinnersName());
        } else {
            gameStatus.put("endOfGame", "false");
        }
        result.put("gameStatus", gameStatus);
*/
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
            String process = play.substring(2, play.length() - 2);
            String[] cards = process.split("\",\"");

            ArrayList<String> types = game.getTypesOfPlay(cards);
            for (String type : types)
            {
                sets.add(type);
            }
        }

        result.put("sets", sets);
        return result.toJSONString();
    }

    /**
     * This converts a TaiPan GameState to a JSONString.
     *
     * @param game {TaiPan} - A TaiPan-game to get the GameState from.
     * @return {String} - A JSON-String representing the gamestate.
     */
    public static String createJSONGameState(final taipan.domain.TaiPan game)
    {
        JSONObject result = new JSONObject();
        JSONArray players = new JSONArray();
        for (int i = 0; i < PlayerData.MAX_NUMBER_OF_PLAYERS; i++)
        {
            players.add(createJSONPlayer(game, i));
        }

        result.put("players", players);

        return result.toJSONString();
    }

    /**
     * This creates a JSON-String of a Player from a TaiPan GameState.
     *
     * @param game {TaiPan} - A TaiPan-game to query the Player of.
     * @param player {int} - The number of the Player to JSONify.
     *
     * @return {JSONObject} - A JSON representation of a player.
     */
    private static JSONObject createJSONPlayer(
        final taipan.domain.TaiPan game,
        final int player
    )
    {
        JSONObject jsonPlayer = new JSONObject();

        //jsonPlayer.put("id", player.getID());
        //jsonPlayer.put("name", ??.getPlayerName(player.getID()));
        jsonPlayer.put("cards", createJSONCards(
            game.getCardsOfPlayer(player)
        ));
        jsonPlayer.put("inTurn", game.isPlayerInTurn(player));

        return jsonPlayer;
    }

    /**
     * Creates a JSON from a set of cards.
     *
     * @param cards {Card[]} - The Cards to JSONify.
     *
     * @return {JSONObject} - A JSON representation of a set of Cards.
     */
    private static JSONArray createJSONCards(
        final ArrayList<ArrayList<String>> cards
    )
    {
        JSONArray jsonCards = new JSONArray();
        for (ArrayList<String> card : cards)
        {
            jsonCards.add(createJSONCard(card));
        }
        return jsonCards;
    }

    /**
     * Creates a JSON-Object from a Card.
     *
     * @param card {Card} - The Card to JSONify.
     *
     * @return {JSONObject} - A JSON representation of a Card.
     */
    private static JSONObject createJSONCard(
        final ArrayList<String> card
    )
    {
        JSONObject jsonCard = new JSONObject();
        jsonCard.put("suit", card.get(0));
        jsonCard.put("rank", card.get(1));
        return jsonCard;
    }
}
