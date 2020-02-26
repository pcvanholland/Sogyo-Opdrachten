package taipan.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import javax.ws.rs.PUT;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/lobby/")
public class Lobby
{
    private static final int SUCCESS = 200;
    private static final int FAILURE = 500;

    /**
     * This handles a join request by a Player.
     * It *should* contain a game to join.
     *
     * @param request {HttpServletRequest} - A Request from the server.
     * @param player {Player} - A Player-instance.
     *
     * @return {Response} - Whether the join was successful.
     *                  If it was successful, it contains the Game's name.
     */
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @Path("join")
    public Response joinGame(
			final @Context HttpServletRequest request,
            final Player player
    )
    {
System.out.println("Post on join.");
        //HttpSession session = request.getSession(true);
/*
		//session.setAttribute("taipan", taipan);
        if (game.isFull())
        {
            return Response.status(FAILURE).build();
        }
        game.addPlayer(player.getName());*/
        String output = JSONProcessor.createJSONResponse("PlayerID.");
		return Response.status(SUCCESS).entity(output).build();
    }

    /**
     * This handles a startGame request by a Player.
     * It *should* contain a game to start.
     *
     * @param request {HttpServletRequest} - A Request from the server.
     *
     * @return {Response} - Whether the start was successful.
     */
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @Path("startgame")
    public Response startGame(
			final @Context HttpServletRequest request
    )
    {
System.out.println("Post on start.");

        HttpSession session = request.getSession(true);
        //if (game.isFull())
        if (session != null)
        {
            session.setAttribute("taipan", new taipan.domain.TaiPan());
            String output = JSONProcessor.createJSONResponse("GameID.");

    		return Response.status(SUCCESS).entity(output).build();
        }
        return Response.status(FAILURE).build();

    }
}
