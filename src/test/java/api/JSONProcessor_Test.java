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
}
