package webserver;

import org.junit.Assert;
import java.util.HashMap;
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

    @Test
    public void test_getHeaderOK()
    {
        HttpStatusCode status = null;
        String expectedResult = "HTTP/1.0 200 OK";

        Response response = new Response("HTTP/1.0", status.OK);

        Assert.assertEquals(expectedResult, response.getPrimaryHeader());
    }

    @Test
    public void test_getHeaderOKHTML11()
    {
        HttpStatusCode status = null;
        String expectedResult = "HTTP/1.1 200 OK";

        Response response = new Response("HTTP/1.1", status.OK);

        Assert.assertEquals(expectedResult, response.getPrimaryHeader());
    }

    @Test
    public void test_getHeaderNotFound()
    {
        HttpStatusCode status = null;
        String expectedResult = "HTTP/1.0 404 Not Found";

        Response response = new Response("HTTP/1.0", status.NotFound);


        Assert.assertEquals(expectedResult, response.getPrimaryHeader());
    }

    @Test
    public void test_getResponse()
    {
        HttpStatusCode status = null;
        Response response = new Response("HTTP/1.0", status.OK);
        String expectedResult = "HTTP/1.0 200 OK\r\n" +
            response.getDateString() +
            "\r\n\r\nThank you for connecting!";

        Assert.assertEquals(expectedResult, response.getResponse());
    }

    @Test
    public void test_getCustomHeader()
    {
        HttpStatusCode status = null;
        HashMap<String, String> expectedResult = new HashMap<String, String>();
        expectedResult.put("name1", "value1");

        Response response = new Response("", status.OK);
        response.addCustomHeader("name1", "value1");

        Assert.assertEquals(expectedResult, response.getCustomHeaders());
    }

    @Test
    public void test_getCustomHeaders()
    {
        HttpStatusCode status = null;
        HashMap<String, String> expectedResult = new HashMap<String, String>();
        expectedResult.put("name1", "value1");
        expectedResult.put("name2", "value2");

        Response response = new Response("", status.OK);
        response.addCustomHeader("name1", "value1");
        response.addCustomHeader("name2", "value2");

        Assert.assertEquals(expectedResult, response.getCustomHeaders());
    }

    @Test
    public void test_getResponseWithCustomHeaders()
    {
        HttpStatusCode status = null;
        Response response = new Response("HTTP/1.0", status.OK);
        String expectedResult = "HTTP/1.0 200 OK\r\n" +
            response.getDateString() +
            "\r\nname1: value1\r\n\r\nThank you for connecting!";
        response.addCustomHeader("name1", "value1");

        Assert.assertEquals(expectedResult, response.getResponse());
    }
}
