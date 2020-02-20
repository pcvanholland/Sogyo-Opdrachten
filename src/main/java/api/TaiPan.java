package taipan.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.ws.rs.PUT;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class TaiPan
{
    @PUT
    @Path("/play/{player}")
    public Response play(
			@PathParam("player") String playerID,
			@Context HttpServletRequest request
    )
    {
System.out.println("Put on player " + playerID);
        HttpSession session= request.getSession(false);
        String output = JSONProcessor.createJSONResponse("Played " + playerID + ".");
		return Response.status(200).entity(output).build();
    }

    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @Path("login")
    public Response login(
			@Context HttpServletRequest request
    )
    {
System.out.println("Post on login.");
        HttpSession session= request.getSession(true);

		//session.setAttribute("taipan", taipan);
        String output = JSONProcessor.createJSONResponse("PlayerID.");

		return Response.status(200).entity(output).build();
    }
}
