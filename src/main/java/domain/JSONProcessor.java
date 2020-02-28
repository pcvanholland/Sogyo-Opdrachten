package taipan.domain;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class JSONProcessor
{

    /**
     * Squashes a Player to a JSONObject.
     *
     * @param player {Player} - The Player to JSONify.
     * @return {JSONObject} - The JSON representation of the Player.
     */
    protected static final JSONObject createJSONPlayer(Player player)
    {
        JSONObject result = new JSONObject();
        result.put("cards", createJSONCards(player.getCards()));
        result.put("inTurn", player.isInTurn());
        result.put("canDraw", player.canDrawCards());
        return result;
    }

    /**
     * Squashes a Player to a JSONObject.
     *
     * @param player {Player} - The Player to JSONify.
     * @return {JSONObject} - The JSON representation of the Player.
     */
    protected static final JSONArray createJSONCards(ArrayList<Card> cards)
    {
        JSONArray result = new JSONArray();
        for (Card card : cards)
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
    protected static final JSONObject createJSONCard(Card card)
    {
        JSONObject result = new JSONObject();
        result.put("suit", card.getSuit());
        result.put("rank", card.getRank());
        return result;
    }
}
