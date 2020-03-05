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
            ArrayList<String> types = game.getTypesOfPlay(play);
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
        return game.getJSONGameState().toJSONString();
    }
}
