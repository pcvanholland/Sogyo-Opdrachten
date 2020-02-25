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

@Path("/")
public class TaiPan
{
    private static final int SUCCESS = 200;
    private static final int FAILURE = 500;

    // The currently active game(s).
//    private Game game;

    /**
     * This handles a play request by a Player.
     * It *should* receive a "Play" - a set of cards - that was played.
     *
     * @param request {HttpServletRequest} - A Request from the server.
     * @param player {Player} - A Player-instance.
     *
     * @return {Response} - Whether the Play was successful.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("play")
    public Response play(
            final @Context HttpServletRequest request,
            final Player player
    )
    {
System.out.println("Post on play: " + player.getName());

        String output = JSONProcessor.createJSONResponse("Played.");
		return Response.status(SUCCESS).entity(output).build();
    }

    /**
     * This handles a play request by a Player.
     * It *should* receive a "Play" - a set of cards - that was played.
     *
     * @param request {HttpServletRequest} - A Request from the server.
     * @param player {Player} - A Player-instance.
     *
     * @return {Response} - Whether the Play was successful.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getgamestate")
    public Response getGameState(
            final @Context HttpServletRequest request
    )
    {
System.out.println("Post on GGS.");
		HttpSession session = request.getSession(false);
        if (session != null)
        {
    		taipan.domain.TaiPan taipan =
                (taipan.domain.TaiPan) session.getAttribute("taipan");
            taipan.getGameState();
        }

        String output = JSONProcessor.createJSONResponse("Got GameState.");
		return Response.status(SUCCESS).entity(output).build();
    }
}
