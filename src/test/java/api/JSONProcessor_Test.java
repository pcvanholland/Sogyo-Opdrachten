package taipan.api;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class JSONProcessor_Test
{
    @Test
    public void test_createJSONResponse()
    {
        String text = "sampleText";
        String expectedResult = "{\"result\":\"" + text + "\"}";

        String result = JSONProcessor.createJSONResponse(text);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void test_createEmptyJSONGameState()
    {
        String expectedResult = "{\"players\":[";
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

        String result = JSONProcessor.createJSONGameState(tp);

        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Difficult to properly test with random Cards,,,
     */
    @Test
    public void test_createNonEmptyJSONGameState()
    {
        String notExpectedResult = "{\"players\":[";
        for (int i = 0; i < PlayerData.MAX_NUMBER_OF_PLAYERS; ++i)
        {
            notExpectedResult +=
                    "{" +
                        "\"inTurn\":" +
                            "false" +
                        "," +
                        "\"cards\":" +
                            "[]" +
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

        String result = JSONProcessor.createJSONGameState(tp);

        Assert.assertNotEquals(notExpectedResult, result);
    }

    @Test
    public void test_createSingleJSONPlayTypes()
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
    public void test_createPairJSONPlayTypes()
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
    public void test_dontCrashOnEmptyString()
    {
        String play = "[]";
        String expectedResult = "{\"sets\":[" +
        "]}";

        taipan.domain.TaiPan tp = new taipan.domain.TaiPan();

        Assert.assertEquals(expectedResult,
            JSONProcessor.createJSONPlayTypes(tp, play));
    }

    @Test
    public void test_createGameListWithSingleGame()
    {
        String expectedResult = "[{" +
            "\"host\":\"Hostname\"," +
            "\"id\":0," +
            "\"full\":false" +
        "}]";
        ArrayList<Game> games = new ArrayList<Game>();
        games.add(new Game("Hostname"));

        Assert.assertEquals(expectedResult,
            JSONProcessor.createJSONGameList(games));
    }

    @Test
    public void test_createGameListWithMultipleGames()
    {
        String expectedResult = "[" +
            "{\"host\":\"Hostname\"," +
            "\"id\":1," +
            "\"full\":false}" +
                "," +
            "{\"host\":\"Hostname\"," +
            "\"id\":2," +
            "\"full\":false}" +
        "]";
        ArrayList<Game> games = new ArrayList<Game>();
        games.add(new Game("Hostname"));
        games.add(new Game("Hostname"));

        Assert.assertEquals(expectedResult,
            JSONProcessor.createJSONGameList(games));
    }
}
