package taipan.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This is a class that processes JSON to String and vice versa.
 */
public class JSONProcessor {

	/**
	 * @param mancala
	 * @param player1
	 * @param player2
	 * @return
	 */
	public static String createJSONResponse(String textfornow)
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
	 * @param player {Player} - The Player to JSONify.
     *
	 * @return {JSONObject} - A JSON representation of a player.
	 *
	private static JSONObject createJSONPlayer(Player player)
    {
		JSONObject jsonPlayer = new JSONObject();

        jsonPlayer.put("id", player.getID());
		jsonPlayer.put("name", ??.getPlayerName(player.getID()));
        jsonPlayer.put("cards", this.createJSONCards(player.getCards()));
        jsonPlayer.put("inTurn", player.isInTurn());

		return jsonPlayer;
	}

    /**
     * Creates a JSON from a set of cards.
     *
     * @param cards {Card[]} - The cards to JSONify.
     * @return {JSONObject} - A JSON representation of a set of cards.
     *
     private static JSONObject createJSONCards(ArrayList<Card> cards)
     {
         JSONObject jsonCards = new JSONObject();
         for (Card card : cards)
         {
             jsonCards.put(this.createJSONCard(card));
         }
         return jsonCards;
     }

     /**
      * Creates a JSON from a card.
      *
      * @param card {Card} - The card to JSONify.
      * @return {JSONObject} - A JSON representation of a card.
      *
      private static JSONObject createJSONCard(Card card)
      {
          JSONObject jsonCard = new JSONObject();
          jsonCard.put("suit", card.getSuit());
          jsonCard.put("rank", card.getRank());
          return jsonCard;
      }
     */
}
