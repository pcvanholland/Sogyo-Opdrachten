package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONProcessor_Test
{
    @Test
    public void test_cardToJSON()
    {
        StandardSuit suit = StandardSuit.JADE;
        StandardRank rank = StandardRank.FOUR;
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("suit", suit.toString());
        expectedResult.put("rank", rank.toString());

        Card card = new PlayingCard(suit, rank);

        Assert.assertEquals(expectedResult, JSONProcessor.createJSONCard(card));
    }

    @Test
    public void test_playerToJSON()
    {
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("cards", new JSONArray());
        expectedResult.put("inTurn", false);
        expectedResult.put("canDraw", true);
        expectedResult.put("id", 0);

        Player player = new Player();

        Assert.assertEquals(expectedResult,
            JSONProcessor.createJSONPlayer(player));
    }

    @Test
    public void test_playerToJSONWithCards()
    {
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("cards", new JSONArray());
        expectedResult.put("inTurn", false);
        expectedResult.put("canDraw", true);
        expectedResult.put("id", 0);

        Player player = new Player();
        player.drawCards();

        Assert.assertNotEquals(expectedResult,
            JSONProcessor.createJSONPlayer(player));
    }

    @Test
    public void test_gameToJSON()
    {
        JSONObject expectedResult = new JSONObject();
        JSONArray players = new JSONArray();
        for (int i = 0; i < 4; ++i)
        {
            JSONObject player = new JSONObject();
            player.put("cards", new JSONArray());
            player.put("inTurn", false);
            player.put("canDraw", true);
            player.put("id", i);
            players.add(player);
        }
        expectedResult.put("players", players);

        TaiPan tp = new TaiPan();

        Assert.assertEquals(expectedResult,
            JSONProcessor.createJSONGame(tp));
    }
}
