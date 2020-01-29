package webserver;

import org.junit.Assert;
import org.junit.Test;

public class Response_Test
{
    @Test
    public void test_createResponse()
    {
        HttpStatusCode status = null;
        new Response("HTTP/1.0", status.OK);
    }

    @Test
    public void test_getStatusOK()
    {
        HttpStatusCode expectedResult = null;
        expectedResult = expectedResult.OK;
        Response response = new Response("", expectedResult);

        Assert.assertEquals(expectedResult, response.getStatus());
    }

    @Test
    public void test_getStatusNotFound()
    {
        HttpStatusCode expectedResult = null;
        expectedResult = expectedResult.NotFound;
        Response response = new Response("", expectedResult);

        Assert.assertEquals(expectedResult, response.getStatus());
    }

    @Test
    public void test_getStatusSE()
    {
        HttpStatusCode expectedResult = null;
        expectedResult = expectedResult.ServerError;
        Response response = new Response("", expectedResult);

        Assert.assertEquals(expectedResult, response.getStatus());
    }
}
