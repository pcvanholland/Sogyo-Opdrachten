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
    public void test_createJSONGameState()
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
}
