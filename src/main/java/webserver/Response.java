package webserver;

import java.time.ZonedDateTime;
import java.util.HashMap;

public class Response implements IResponse
{
    private String protocolVersion;
    private HttpStatusCode status;

    /**
     * Default constructor for a response.
     *
     * @param {String} version - The version of the response, e.g.
            "HTTP/1.0", "HTTP/1.1" ,,,.
     * @param status - The status of the response.
     */
    Response(String version, HttpStatusCode status)
    {
        protocolVersion = version;
        this.status = status;
    }

    /**
     * HTTP status code that informs the client of the
     * processing of the request by the server.
     */
    @Override
    public HttpStatusCode getStatus()
    {
        return status;
    }

    /**
     * The header parameters and values that are unique for
     * this response. A response defines a set of headers, some
     * are unique, others are always present. The date header
     * is always present and, if the response has content,
     * so are the Content-Type and Content-Length.
     */
    @Override
    public HashMap<String, String> getCustomHeaders()
    {
        return new HashMap<String, String>();
    }

    /**
     * The exact date and time at which this response was
     * generated. This is used for the date header that is
     * always added.
     */
    @Override
    public ZonedDateTime getDate()
    {
        return ZonedDateTime.now();
    }

    /**
     * Optionally, a response contains content. If we want
     * to transfer for example a web page, we add the HTML contents
     * in the body of the response.
     */
    @Override
    public String getContent()
    {
        return "";
    }
}
