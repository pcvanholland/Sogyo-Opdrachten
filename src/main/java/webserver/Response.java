package webserver;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.time.ZonedDateTime;

public class Response implements IResponse
{
    private String protocolVersion;
    private HttpStatusCode status;

    private HashMap<String, String> customHeaders = new HashMap<String, String>();

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
     * The primary header including the protocolVersion and HTTP status code.
     *
     * @return {String} - A string of the primary header, e.g. "HTTP/1.0 200 OK".
     */
    protected String getPrimaryHeader()
    {
        return String.join(" ",
            protocolVersion,
            Integer.toString(status.getCode()),
            status.getDescription()
        );
    }

    /**
     * A combination of the primary header, custom headers and the content.
     *
     * @return {String} - A string of all data combined.
     */
    protected String getResponse()
    {
        return String.join("\r\n",
            getPrimaryHeader(),
            getCustomHeadersString(),
            "", getContent()
        );
    }

    /**
     * Add a custom header to this response.
     *
     * @param {String} key - A string of the key.
     * @param {String} value - A string of the value corresponding to the key.
     */
    protected void addCustomHeader(String key, String value)
    {
        customHeaders.put(key, value);
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
        return customHeaders;
    }

    /**
     * The header parameters and values that are unique for
     * this response. A response defines a set of headers, some
     * are unique, others are always present. The date header
     * is always present and, if the response has content,
     * so are the Content-Type and Content-Length.
     */
    protected String getCustomHeadersString()
    {
        ArrayList<String> result = new ArrayList<String>();

        // We always include the date.
        result.add(getDateString());
        customHeaders.forEach((key, value) -> result.add(key + ": " + value));

        return String.join("\r\n", result);
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
     * Converts the date to a processable string.
     *
     * @return {String} - The current date in RFC1123-format.
     */
    protected String getDateString()
    {
        return getDate().format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }

    /**
     * Optionally, a response contains content. If we want
     * to transfer for example a web page, we add the HTML contents
     * in the body of the response.
     */
    @Override
    public String getContent()
    {
        return "Thank you for connecting!";
    }
}
