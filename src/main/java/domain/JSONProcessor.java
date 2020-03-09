package taipan.domain;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

final class JSONProcessor
{
    /**
     * Squashes a Game into a JSONObject.
     *
     * @param game {TaiPan} - The Game to JSONify.
     * @return {JSONObject} - The JSON representation of the Game.
     */
    static JSONObject createJSONGame(final TaiPan game)
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
    static JSONArray createJSONPlayers(final TaiPan game)
    {
        JSONArray result = new JSONArray();
        for (int i = 0; i < Player.NUM_PLAYERS; ++i)
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
    static JSONObject createJSONPlayer(final Player player)
    {
        JSONObject result = new JSONObject();
        result.put("cards", createJSONCards(player.getCards()));
        result.put("inTurn", player.isInTurn());
        result.put("mayPass", player.mayPass());
        result.put("canDraw", player.canDrawCards());

        return result;
    }

    /**
     * Squashes a Table to a JSONArray.
     *
     * @param table {Table} - The Table to JSONify.
     * @return {JSONObject} - The JSON representation of the Table.
     */
    static JSONObject createJSONTable(final Table table)
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
        final ArrayList<Play> plays
    )
    {
        JSONArray result = new JSONArray();
        for (Play play : plays)
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
    private static JSONObject createJSONPlay(final Play play)
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
        final ArrayList<Card> cards
    )
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
    static JSONObject createJSONCard(final Card card)
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
    static CardCollection createCardsFromJSON(final String cards)
    {
        CardCollection realCards = new CardCollection();
        if (cards.length() <= 5)
        {
            return realCards;
        }
        String process = cards.substring(2, cards.length() - 2);
        String[] splitCards = process.split("\",\"");
        for (String card : splitCards)
        {
            Card realCard;
            String[] cardProps = card.split(",");
            if (cardProps[0].equals("SPECIAL"))
            {
                realCard = SpecialCard.createSpecialCard(
                    SpecialRank.valueOf(cardProps[1])
                );
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
        return realCards;
    }
}
