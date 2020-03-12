package taipan.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import javax.ws.rs.PUT;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/lobby/")
public class Lobby
{
    private static final int SUCCESS = 200;
    private static final int FAILURE = 500;

    // The currently active game(s).
    private static ArrayList<TaiPan> activeGames = new ArrayList<TaiPan>();

    /**
     * This handles a startGame request by a Player.
     * It *should* contain a game to start.
     *
     * @param request {HttpServletRequest} - A Request from the server.
     * @param hostName {String} - The ID of the host.
     *
     * @return {Response} - Whether the start was successful.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("startgame")
    public Response startGame(
        final @Context HttpServletRequest request,
        final String hostName
    )
    {
System.out.println("Post on start.");

        HttpSession session = request.getSession(true);
        //if (!game.isFull())
        if (session != null)
        {
            TaiPan newGame = new TaiPan(hostName);
            activeGames.add(newGame);
            session.setAttribute("game", newGame);
            String output = JSONProcessor.createJSONResponse(
                Integer.toString(0)
            );

            return Response.status(SUCCESS).entity(output).build();
        }
        return Response.status(FAILURE).build();
    }

    /**
     * This handles a join request by a Player.
     *
     * @param request {HttpServletRequest} - A Request from the server.
     * @param joinRequest {JoinRequest} - A Player's name and the GameID
     *                                  of the Game to join.
     *
     * @return {Response} - Whether the join was successful.
     *                  If it was successful, it contains the Player's ID.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("join")
    public Response joinGame(
        final @Context HttpServletRequest request,
        final JoinRequest joinRequest
    )
    {
System.out.println("Post on join game " +
    joinRequest.getGameID() + " by " + joinRequest.getPlayerName()
);
        HttpSession session = request.getSession(true);
        if (session != null)
        {
            TaiPan game = activeGames.get(joinRequest.getGameID());
            session.setAttribute("game", game);
            String output = JSONProcessor.createJSONResponse(
                Integer.toString(game.joinGame(joinRequest.getPlayerName()))
            );
            return Response.status(SUCCESS).entity(output).build();
        }
        return Response.status(FAILURE).build();
    }

    /**
     * This creates a list of currently active Games.
     *
     * @param request {HttpServletRequest} - A Request from the server.
     *
     * @return {Response} - A list of currently active Games.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listgames")
    public Response listGames(
        final @Context HttpServletRequest request
    )
    {
System.out.println("Get on list.");

        HttpSession session = request.getSession();
        if (session != null)
        {
            String output = JSONProcessor.createJSONGameList(activeGames);

            return Response.status(SUCCESS).entity(output).build();
        }
        return Response.status(FAILURE).build();
    }
}
