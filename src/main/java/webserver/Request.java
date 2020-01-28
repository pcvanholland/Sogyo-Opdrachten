package webserver;

import java.util.ArrayList;

public class Request implements IRequest
{
    private final int mrvPosition = 0;
    private final int methodPosition = 0;
    private final int resourcePosition = 1;
    private final int versionPosition = 2;

    private String[] methodResourceVersion;
    private ArrayList<String> headers = new ArrayList<String>();
    private ArrayList<String> URLparameters = new ArrayList<String>();

    Request(ArrayList<String> incomingRequest) throws IllegalArgumentException
    {
        methodResourceVersion = incomingRequest.remove(mrvPosition).split(" ");
        setHeaders(incomingRequest);
        setURLparameters();
    }

    /**
     * Set the headers from a request.
     *
     * @param {String[]} incomingRequest - An ArrayList containing the lines of a request
     *                                      minus the first line.
     */
    private void setHeaders(ArrayList<String> incomingRequest)
    {
        for (String header : incomingRequest)
        {
            if (!header.trim().isEmpty())
            {
                headers.add(header);
            }
        }
    }

    /**
     * Set the URL parameters from a request.
     */
    private void setURLparameters()
    {
        String resource = this.getResourcePath();
        for (String pm : getURLparametersFromURL(resource))
        {
            this.URLparameters.add(pm);
        }
    }

    /**
     * Gets valid URL parameters from an URL.
     *
     * @param {String} url - The string to verify.
     * @return {String[]} - An ArrayList containing valid URL parameters.
     */
    private ArrayList<String> getURLparametersFromURL(String url)
    {
        ArrayList<String> result = new ArrayList<String>();
        int index = url.indexOf('?');
        if (index == -1)
        {
            return result;
        }
        String URLparameters = url.substring(index + 1);
        for (String str : URLparameters.split("[&;]"))
        {
            if (isValidURLparameter(str))
            {
                result.add(str);
            }
        }
        return result;
    }

    /**
     * Verify whether a string is a valid URL parameter.
     *
     * @param {String} string - The string to verify.
     * @return {boolean} - Whether the string is a valid URL parameter.
     */
    private boolean isValidURLparameter(String string)
    {
        return string.contains("=");
    }

    /**
     * Defines the HTTP method that was used by the
     * client in the incoming request.
     */
    @Override
    public HttpMethod getHTTPMethod()
    {
        HttpMethod result = null;

        if (methodResourceVersion[methodPosition].equals("GET"))
        {
            result = result.GET;
        }
        else if (methodResourceVersion[methodPosition].equals("POST"))
        {
            result = result.POST;
        }
        else if (methodResourceVersion[methodPosition].equals("PUT"))
        {
            result = result.PUT;
        }
        else if (methodResourceVersion[methodPosition].equals("DELETE"))
        {
            result = result.DELETE;
        }

        return result;
    }

    /**
     * Defines the resource path that was requested by
     * the client. The resource path excludes url parameters.
     */
    @Override
    public String getResourcePath()
    {
        return methodResourceVersion[resourcePosition];
    }

    /**
     * Defines the names of the header parameters that
     * were supplied in the request.
     */
    @Override
    public ArrayList<String> getHeaderParameterNames()
    {
        ArrayList<String> result = new ArrayList<String>();
        for (String header : headers)
        {
            result.add(header.substring(0, header.indexOf(':')));
        }
        return result;
    }

    /**
     * Retrieves the supplied header parameter value
     * correspronding to the name. If no header exists
     * with the name, it returns null.
     */
    @Override
    public String getHeaderParameterValue(String name)
    {
        for (String header : headers)
        {
            if (header.startsWith(name))
            {
                // We use +2 for we want to ignore both the ':' and the space thereafter.
                return header.substring(header.indexOf(':') + 2);
            }
        }
        return null;
    }

    /**
     * Retrieves the URL parameters that were present in
     * the requested URL.
     */
    @Override
    public ArrayList<String> getURLParameterNames()
    {
        ArrayList<String> result = new ArrayList<String>();
        for (String str : URLparameters)
        {
            result.add(str.split("=")[0]);
        }
        return result;
    }

    /**
     * Retreives the URL parameter value corresponding to
     * the name. If no parameter exists with the name,
     * it returns null.
     */
    @Override
    public String getURLParameterValue(String name)
    {
        for (String parameter : URLparameters)
        {
            if (parameter.startsWith(name))
            {
                return parameter.substring(parameter.indexOf("=") + 1);
            }
        }
        return null;
    }
}
