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
        expectedResult.put("suit", suit);
        expectedResult.put("rank", rank);

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

        Player player = new Player();
        player.drawCards();

        Assert.assertNotEquals(expectedResult,
            JSONProcessor.createJSONPlayer(player));
    }
}
