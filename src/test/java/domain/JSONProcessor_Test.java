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
        expectedResult.put("mayPass", false);
        expectedResult.put("canDraw", true);
        expectedResult.put("id", 0);

        Player player = new Player(new Table());

        Assert.assertEquals(expectedResult,
            JSONProcessor.createJSONPlayer(player));
    }

    @Test
    public void test_playerToJSONWithCards() throws
        CantDrawTooManyTimesException
    {
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("cards", new JSONArray());
        expectedResult.put("inTurn", false);
        expectedResult.put("mayPass", false);
        expectedResult.put("canDraw", true);
        expectedResult.put("id", 0);

        Player player = new Player(new Table());
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
            player.put("mayPass", false);
            player.put("canDraw", true);
            player.put("id", i);
            players.add(player);
        }
        expectedResult.put("players", players);

        JSONObject table = new JSONObject();
        table.put("trick", new JSONArray());
        expectedResult.put("table", table);

        TaiPan tp = new TaiPan();

        Assert.assertEquals(expectedResult,
            JSONProcessor.createJSONGame(tp));
    }

    @Test
    public void test_emptyTableToJSON()
    {
        JSONObject expectedResult = new JSONObject();
        expectedResult.put("trick", new JSONArray());

        Assert.assertEquals(expectedResult,
            JSONProcessor.createJSONTable(new Table())
        );
    }

    @Test
    public void test_nonEmptyTableToJSON() throws CantPlayTableException
    {
        Table table = new Table();
        JSONObject notExpectedResult = new JSONObject();
        notExpectedResult.put("trick", new JSONArray());

        table.play(Play_Test_Helper.createSingle(2));

        Assert.assertNotEquals(notExpectedResult,
            JSONProcessor.createJSONTable(table)
        );
    }

    @Test
    public void test_createCardsFromJSON()
    {
        CardCollection expectedResult = new CardCollection();
        expectedResult.add(
            new PlayingCard(StandardSuit.JADE, StandardRank.TWO)
        );
        expectedResult.add(
            new PlayingCard(StandardSuit.SWORD, StandardRank.TWO)
        );

        CardCollection result =
            JSONProcessor.createCardsFromJSON("[\"JADE,TWO\",\"SWORD,TWO\"]");

        for (int i = 0; i < result.size(); ++i)
        {
            Assert.assertTrue(
                expectedResult.get(i).equals(result.get(i))
            );
        }
    }

    @Test
    public void test_createCardsFromJSONEmptySet()
    {
        Assert.assertEquals(
            new CardCollection().getCards(),
            JSONProcessor.createCardsFromJSON("[]").getCards()
        );
    }
}
