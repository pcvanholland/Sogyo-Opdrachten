package taipan.api;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONProcessor_Test
{
    @Test
    public void test_createJSONResponse() throws taipan.domain.TaiPanException
    {
        String text = "sampleText";
        String expectedResult = "{\"result\":\"" + text + "\"}";

        String result = JSONProcessor.createJSONResponse(text);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void test_createEmptyJSONGameState() throws
        taipan.domain.TaiPanException
    {
        String expectedResult = "{";
        expectedResult += "\"score\":[0,0]";
        expectedResult += ",\"players\":[";
        for (int i = 0; i < PlayerData.MAX_NUMBER_OF_PLAYERS; ++i)
        {
            expectedResult +=
                    "{" +
                        "\"inTurn\":" +
                            "false" +
                        "," +
                        "\"cards\":" +
                            "[]" +
                        "," +
                        "\"canDraw\":" +
                            "true" +
                        "," +
                        "\"mayPass\":" +
                            "false" +
                        "," +
                        "\"id\":" +
                            i +
                    "}";
            expectedResult += i < 3 ? "," : "]";
        }
        expectedResult += ",\"table\":{\"trick\":[]}";
        expectedResult += "}";
        taipan.domain.TaiPan tp = new taipan.domain.TaiPan();

        String result = JSONProcessor.createJSONGameState(tp).toJSONString();

        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Difficult to properly test with random Cards,,,
     */
    @Test
    public void test_createNonEmptyJSONGameState() throws
        taipan.domain.TaiPanException
    {
        String notExpectedResult = "{}";
        notExpectedResult += "\"score\":[0,0]";
        notExpectedResult += ",\"players\":[";
        for (int i = 0; i < PlayerData.MAX_NUMBER_OF_PLAYERS; ++i)
        {
            notExpectedResult +=
                    "{" +
                        "\"inTurn\":" +
                            "false" +
                        "," +
                        "\"cards\":" +
                            "[]" +
                        "\"canDraw\":" +
                            "true" +
                        "," +
                        "\"mayPass\":" +
                            "false" +
                        "," +
                        "\"id\":" +
                            i +
                    "}";
            notExpectedResult += i < 3 ? "," : "]";
        }
        notExpectedResult += ",\"table\":{\"trick\":[]}";
        notExpectedResult += "}";
        taipan.domain.TaiPan tp = new taipan.domain.TaiPan();
        tp.letPlayerDrawCards(1);

        String result = JSONProcessor.createJSONGameState(tp).toJSONString();

        Assert.assertNotEquals(notExpectedResult, result);
    }

    @Test
    public void test_createSingleJSONPlayTypes() throws
        taipan.domain.TaiPanException
    {
        String play = "[\"PAGODA,FIVE\"]";
        String expectedResult = "{\"sets\":[" +
            "\"SINGLE\"" +
        "]}";

        taipan.domain.TaiPan tp = new taipan.domain.TaiPan();

        Assert.assertEquals(expectedResult,
            JSONProcessor.createJSONPlayTypes(tp, play));
    }

    @Test
    public void test_createPairJSONPlayTypes() throws
        taipan.domain.TaiPanException
    {
        String play = "[\"PAGODA,FIVE\",\"JADE,FIVE\"]";
        String expectedResult = "{\"sets\":[" +
            "\"PAIR\"" +
        "]}";

        taipan.domain.TaiPan tp = new taipan.domain.TaiPan();

        Assert.assertEquals(expectedResult,
            JSONProcessor.createJSONPlayTypes(tp, play));
    }

    @Test
    public void test_dontCrashOnEmptyString() throws
        taipan.domain.TaiPanException
    {
        String play = "[]";
        String expectedResult = "{\"sets\":[" +
        "]}";

        taipan.domain.TaiPan tp = new taipan.domain.TaiPan();

        Assert.assertEquals(expectedResult,
            JSONProcessor.createJSONPlayTypes(tp, play));
    }

    @Test
    public void test_createGameListWithSingleGame() throws
        taipan.domain.TaiPanException
    {
        TaiPan game = new TaiPan("Hostname");
        String expectedResult = "[{" +
            "\"host\":\"Hostname\"," +
            "\"id\":" + game.getID() + "," +
            "\"full\":false" +
        "}]";
        ArrayList<TaiPan> games = new ArrayList<TaiPan>();
        games.add(game);

        Assert.assertEquals(expectedResult,
            JSONProcessor.createJSONGameList(games));
    }

    @Test
    public void test_createGameListWithMultipleGames() throws
        taipan.domain.TaiPanException
    {
        String hostname = "Hostname";
        TaiPan game1 = new TaiPan(hostname);
        TaiPan game2 = new TaiPan(hostname);
        JSONArray expectedResult = new JSONArray();
        JSONObject game1result = new JSONObject();
        game1result.put("host", hostname);
        game1result.put("id", game1.getID());
        game1result.put("full", false);
        expectedResult.add(game1result);

        JSONObject game2result = new JSONObject();
        game2result.put("host", hostname);
        game2result.put("id", game2.getID());
        game2result.put("full", false);
        expectedResult.add(game2result);

        ArrayList<TaiPan> games = new ArrayList<TaiPan>();
        games.add(game1);
        games.add(game2);

        Assert.assertEquals(
            expectedResult.toJSONString(),
            JSONProcessor.createJSONGameList(games)
        );
    }

    @Test
    public void test_cardToJSON()
    {
        taipan.domain.StandardSuit suit = taipan.domain.StandardSuit.JADE;
        taipan.domain.StandardRank rank = taipan.domain.StandardRank.FOUR;
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("suit", suit.toString());
        expectedResult.put("rank", rank.toString());

        taipan.domain.Card card = new taipan.domain.PlayingCard(suit, rank);

        Assert.assertEquals(expectedResult, JSONProcessor.createJSONCard(card));
    }

    @Test
    public void test_gameToJSON() throws taipan.domain.TaiPanException
    {
        JSONObject expectedResult = new JSONObject();
        JSONArray players = new JSONArray();
        for (int i = 0; i < PlayerData.MAX_NUMBER_OF_PLAYERS; ++i)
        {
            JSONObject player = new JSONObject();
            player.put("cards", new JSONArray());
            player.put("inTurn", false);
            player.put("mayPass", false);
            player.put("canDraw", true);
            player.put("id", i);
            players.add(player);
        }
        expectedResult.put("players", players);

        JSONObject table = new JSONObject();
        table.put("trick", new JSONArray());
        expectedResult.put("table", table);;

        JSONArray score = new JSONArray();
        score.add(0);
        score.add(0);
        expectedResult.put("score", score);

        taipan.domain.TaiPan tp = new taipan.domain.TaiPan();

        Assert.assertEquals(
            expectedResult,
            JSONProcessor.createJSONGameState(tp)
        );
    }

    @Test
    public void test_createCardsFromJSON() throws taipan.domain.TaiPanException
    {
        ArrayList<taipan.domain.Card> expectedResult =
            new ArrayList<taipan.domain.Card>();
        expectedResult.add(
            new taipan.domain.PlayingCard(
                taipan.domain.StandardSuit.JADE,
                taipan.domain.StandardRank.TWO
            )
        );
        expectedResult.add(
            new taipan.domain.PlayingCard(
                taipan.domain.StandardSuit.SWORD,
                taipan.domain.StandardRank.TWO
            )
        );

        JSONArray cards = new JSONArray();
        cards.add("JADE,TWO");
        cards.add("SWORD,TWO");

        ArrayList<taipan.domain.Card> result =
            JSONProcessor.createCardsFromJSON(cards.toJSONString());

        for (int i = 0; i < result.size(); ++i)
        {
            Assert.assertTrue(
                expectedResult.get(i).equals(result.get(i))
            );
        }
    }

    @Test
    public void test_createCardsFromJSONEmptySet() throws
        taipan.domain.TaiPanException
    {
        Assert.assertEquals(
            new ArrayList<taipan.domain.Card>(),
            JSONProcessor.createCardsFromJSON("[]")
        );
    }
}
