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
