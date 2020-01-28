package webserver;

import org.junit.Assert;
import org.junit.Test;

public class Response_Test
{
    @Test
    public void test_getStatusOK()
    {
        HttpStatusCode expectedResult = null;
        Response response = new Response();

        Assert.assertEquals(expectedResult.OK, response.getStatus());
    }
/*
    @Test
    public void test_getStatusNotFound()
    {
        HttpStatusCode expectedResult = null;
        Response response = new Response();

        Assert.assertEquals(expectedResult.NotFound, response.getStatus());
    }

    @Test
    public void test_getStatusSE()
    {
        HttpStatusCode expectedResult = null;
        Response response = new Response();

        Assert.assertEquals(expectedResult.ServerError, response.getStatus());
    }*/
}
