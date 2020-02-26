package taipan.api;

import org.junit.Assert;
import org.junit.Test;

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
                    "}";
            expectedResult += i < 3 ? "," : "]}";
        }
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
                    "}";
            notExpectedResult += i < 3 ? "," : "]}";
        }
        taipan.domain.TaiPan tp = new taipan.domain.TaiPan();
        tp.letPlayerDrawCards(1);

        String result = JSONProcessor.createJSONGameState(tp);

        Assert.assertNotEquals(notExpectedResult, result);
    }
}
