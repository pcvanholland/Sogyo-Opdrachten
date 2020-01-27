package webserver;

import java.util.List;

public class RequestHandler implements IRequest
{
    /**
     * Defines the HTTP method that was used by the
     * client in the incoming request.
     */
    public HttpMethod getHTTPMethod()
    {
        return HttpMethod GET;
    }

    /**
     * Defines the resource path that was requested by
     * the client. The resource path excludes url parameters.
     */
    public String getResourcePath()
    {
        return "ResourcePath.";
    }

    /**
     * Defines the names of the header parameters that
     * were supplied in the request.
     */
    public List<String> getHeaderParameterNames()
    {
        return new List<String>();
    }

    /**
     * Retrieves the supplied header parameter value
     * correspronding to the name. If no header exists
     * with the name, it returns null.
     */
    public String getHeaderParameterValue(String name)
    {
        return "HeaderPMvalue";
    }

    /**
     * Retrieves the URL parameters that were present in
     * the requested URL.
     */
    public List<String> getParameterNames()
    {
        return new List<String>();
    }

    /**
     * Retreives the URL parameter value corresponding to
     * the name. If no parameter exists with the name,
     * it returns null.
     */
    public String getParameterValue(String name)
    {
        return "PMvalue";
    }
}
