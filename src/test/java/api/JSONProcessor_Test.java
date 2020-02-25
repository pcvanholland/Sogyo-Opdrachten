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
/*
    @Test
    public void test_createJSONGameState()
    {
        String expectedResult =
            "{\"players\":\"" +
                "{\"cards\":\"" +
                    "[]" +
                "\"}," +
                "{\"inTurn\":\"" +
                    "\"false\"" +
                "\"}," +
            "\"}";
        taipan.domain.TaiPan tp = new taipan.domain.TaiPan();

        String result = JSONProcessor.createJSONGameState(tp);

        Assert.assertEquals(expectedResult, result);
    }*/
}
