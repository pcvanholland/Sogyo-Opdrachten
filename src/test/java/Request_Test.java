package webserver;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class Request_Test
{
    private ArrayList<String> createStandardRequest(String HTTPmethod)
    {
        ArrayList<String> result = new ArrayList<String>();
        result.add(HTTPmethod + " / HTTP/1.0");
        result.add("Header1: Header 1 content");
        result.add("Header2: Header 2 content with _some_ \"special\" characters (+-=`<>\\|[]{}~:,;'!@#$%^?&*) in it.");
        result.add("");

        return result;
    }

    @Test
    public void test_requestCreation()
    {
        Request request = new Request(createStandardRequest("GET"));
    }

    @Test
    public void test_getHTTPMethodGET()
    {
        Request request = new Request(createStandardRequest("GET"));
        HttpMethod expectedResult = null;

        Assert.assertEquals(expectedResult.GET, request.getHTTPMethod());
    }

    @Test
    public void test_getHTTPMethodPOST()
    {
        Request request = new Request(createStandardRequest("POST"));
        HttpMethod expectedResult = null;

        Assert.assertEquals(expectedResult.POST, request.getHTTPMethod());
    }

    @Test
    public void test_getHTTPMethodPUT()
    {
        Request request = new Request(createStandardRequest("PUT"));
        HttpMethod expectedResult = null;

        Assert.assertEquals(expectedResult.PUT, request.getHTTPMethod());
    }

    @Test
    public void test_getHTTPMethodDELETE()
    {
        Request request = new Request(createStandardRequest("DELETE"));
        HttpMethod expectedResult = null;

        Assert.assertEquals(expectedResult.DELETE, request.getHTTPMethod());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getHTTPMethodUnknown()
    {
        Request request = new Request(createStandardRequest("bogus"));
    }

    @Test
    public void test_getResourcePath()
    {
        Request request = new Request(createStandardRequest("GET"));
        String expectedResult = "/";

        Assert.assertEquals(expectedResult, request.getResourcePath());
    }

    @Test
    public void test_getLongerResourcePath()
    {
        String expectedResult = "/authors/books/somebook";
        ArrayList<String> requestHeader = new ArrayList<String>();
        requestHeader.add("GET " + expectedResult + " HTTP/1.0");
        requestHeader.add(" ");

        Request request = new Request(requestHeader);

        Assert.assertEquals(expectedResult, request.getResourcePath());
    }

    @Test
    public void test_getHeaderParameterNames()
    {
        Request request = new Request(createStandardRequest("GET"));
        ArrayList<String> expectedResult = new ArrayList<String>();
        expectedResult.add("Header1");
        expectedResult.add("Header2");

        Assert.assertEquals(expectedResult, request.getHeaderParameterNames());
    }

    @Test
    public void test_getHeaderValue()
    {
        String expectedResult = "Header 1 content";
        ArrayList<String> requestHeader = new ArrayList<String>();
        requestHeader.add("GET " + "/" + " HTTP/1.0");
        requestHeader.add("Header1: " + expectedResult);
        requestHeader.add(" ");
        Request request = new Request(requestHeader);

        Assert.assertEquals(expectedResult, request.getHeaderParameterValue("Header1"));
    }

    @Test
    public void test_getHeaderValueWithSpecialCharacters()
    {
        String expectedResult = "Header 2 content with _some_ \"special\" characters (+-=`<>\\|[]{}~:,;'!@#$%^?&*) in it.";
        ArrayList<String> requestHeader = new ArrayList<String>();
        requestHeader.add("GET " + "/" + " HTTP/1.0");
        requestHeader.add("Header2: " + expectedResult);
        requestHeader.add(" ");
        Request request = new Request(requestHeader);

        Assert.assertEquals(expectedResult, request.getHeaderParameterValue("Header2"));
    }

    @Test
    public void test_getHeaderValueWhichDoesNotExist()
    {
        ArrayList<String> requestHeader = new ArrayList<String>();
        requestHeader.add("GET " + "/" + " HTTP/1.0");
        requestHeader.add(" ");
        Request request = new Request(requestHeader);

        Assert.assertNull(request.getHeaderParameterValue("Header1"));
    }

    @Test
    public void test_getURLParameterNames()
    {
        Request request = new Request(createStandardRequest("GET"));

        Assert.assertEquals(new ArrayList<String>(), request.getURLParameterNames());
    }

    @Test
    public void test_getURLParameterName()
    {
        ArrayList<String> requestHeader = new ArrayList<String>();
        requestHeader.add("GET " + "/?" + "fieldName1=value1" + " HTTP/1.0");
        requestHeader.add("");
        ArrayList<String> expectedResult = new ArrayList<String>();
        expectedResult.add("fieldName1");

        Request request = new Request(requestHeader);

        Assert.assertEquals(expectedResult, request.getURLParameterNames());
    }

    @Test
    public void test_getURLParameterNamesSeperatedByAmpersand()
    {
        ArrayList<String> requestHeader = new ArrayList<String>();
        requestHeader.add("GET " + "/?" + "fieldName1=value1&fieldName2=value2" + " HTTP/1.0");
        requestHeader.add("");
        ArrayList<String> expectedResult = new ArrayList<String>();
        expectedResult.add("fieldName1");
        expectedResult.add("fieldName2");

        Request request = new Request(requestHeader);

        Assert.assertEquals(expectedResult, request.getURLParameterNames());
    }

    @Test
    public void test_getURLParameterNamesSeperatedBySemicolumns()
    {
        ArrayList<String> requestHeader = new ArrayList<String>();
        requestHeader.add("GET " + "/?" + "fieldName1=value1;fieldName2=value2" + " HTTP/1.0");
        requestHeader.add("");
        ArrayList<String> expectedResult = new ArrayList<String>();
        expectedResult.add("fieldName1");
        expectedResult.add("fieldName2");

        Request request = new Request(requestHeader);

        Assert.assertEquals(expectedResult, request.getURLParameterNames());
    }

    @Test
    public void test_getURLParameterNamesSeperatedByMixedAmpersandsAndSemicolumns()
    {
        ArrayList<String> requestHeader = new ArrayList<String>();
        requestHeader.add("GET " + "/?" + "fieldName1=value1;fieldName2=value2&fieldName3=value3" + " HTTP/1.0");
        requestHeader.add("");
        ArrayList<String> expectedResult = new ArrayList<String>();
        expectedResult.add("fieldName1");
        expectedResult.add("fieldName2");
        expectedResult.add("fieldName3");

        Request request = new Request(requestHeader);

        Assert.assertEquals(expectedResult, request.getURLParameterNames());
    }

    @Test
    public void test_getURLParameterValueWhichDoesNotExist()
    {
        Request request = new Request(createStandardRequest("GET"));

        Assert.assertNull(request.getURLParameterValue("bogus"));
    }

    @Test
    public void test_getURLParameterValue()
    {
        ArrayList<String> requestHeader = new ArrayList<String>();
        requestHeader.add("GET " + "/?" + "fieldName1=value1" + " HTTP/1.0");
        requestHeader.add("");

        Request request = new Request(requestHeader);

        Assert.assertEquals("value1", request.getURLParameterValue("fieldName1"));
    }

    @Test
    public void test_getURLParameterValuesSeperatedByAmpersand()
    {
        ArrayList<String> requestHeader = new ArrayList<String>();
        requestHeader.add("GET " + "/?" + "fieldName1=value1&fieldName2=value2" + " HTTP/1.0");
        requestHeader.add("");

        Request request = new Request(requestHeader);

        Assert.assertEquals("value1", request.getURLParameterValue("fieldName1"));
        Assert.assertEquals("value2", request.getURLParameterValue("fieldName2"));
    }

    @Test
    public void test_getURLParameterValuesSeperatedBySemicolumns()
    {
        ArrayList<String> requestHeader = new ArrayList<String>();
        requestHeader.add("GET " + "/?" + "fieldName1=value1;fieldName2=value2" + " HTTP/1.0");
        requestHeader.add("");

        Request request = new Request(requestHeader);

        Assert.assertEquals("value1", request.getURLParameterValue("fieldName1"));
        Assert.assertEquals("value2", request.getURLParameterValue("fieldName2"));
    }

    @Test
    public void test_getURLParameterValuesSeperatedByMixedAmpersandsAndSemicolumns()
    {
        ArrayList<String> requestHeader = new ArrayList<String>();
        requestHeader.add("GET " + "/?" + "fieldName1=value1;fieldName2=value2&fieldName3=value3" + " HTTP/1.0");
        requestHeader.add("");
        ArrayList<String> expectedResult = new ArrayList<String>();
        expectedResult.add("fieldName1");
        expectedResult.add("fieldName2");
        expectedResult.add("fieldName3");

        Request request = new Request(requestHeader);

        Assert.assertEquals("value1", request.getURLParameterValue("fieldName1"));
        Assert.assertEquals("value2", request.getURLParameterValue("fieldName2"));
        Assert.assertEquals("value3", request.getURLParameterValue("fieldName3"));
    }
}
