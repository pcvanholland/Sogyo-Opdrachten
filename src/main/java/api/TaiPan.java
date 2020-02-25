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
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @Path("play")
    public Response play(
			@Context HttpServletRequest request
    )
    {
System.out.println("Post on play.");
        HttpSession session= request.getSession(false);
        String output = JSONProcessor.createJSONResponse("Played.");
		return Response.status(200).entity(output).build();
    }

    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @Path("login")
    public Response login(
			@Context HttpServletRequest request,
            Player player
    )
    {
System.out.println("Post on login.");
        HttpSession session = request.getSession(true);
        DataBase db = new DataBase();

        if (db.verifyPassword(player.getName(), player.getPassword()))
        {
            //session.setAttribute("taipan", taipan);
            String output = JSONProcessor.createJSONResponse(player.getName());
    		return Response.status(200).entity(output).build();
        }
        return Response.status(500).build();
    }

    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @Path("register")
    public Response register(
			@Context HttpServletRequest request,
            Player player
    )
    {
System.out.println("Post on register.");
        HttpSession session = request.getSession(true);
        DataBase db = new DataBase();

        if (db.addPlayer(player.getName(), player.getPassword()))
        {
            //session.setAttribute("taipan", taipan);
            String output = JSONProcessor.createJSONResponse(player.getName());
    		return Response.status(200).entity(output).build();
        }
        return Response.status(500).build();
    }

    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @Path("join")
    public Response joinGame(
			@Context HttpServletRequest request
    )
    {
System.out.println("Post on join.");
        HttpSession session= request.getSession(true);

		//session.setAttribute("taipan", taipan);
        String output = JSONProcessor.createJSONResponse("PlayerID.");

		return Response.status(200).entity(output).build();
    }
}
