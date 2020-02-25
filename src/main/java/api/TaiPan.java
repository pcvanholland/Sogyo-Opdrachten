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
    final private static int SUCCESS = 200;
    final private static int FAILURE = 500;

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
        HttpSession session = request.getSession(false);
        String output = JSONProcessor.createJSONResponse("Played.");
		return Response.status(SUCCESS).entity(output).build();
    }

    /**
     * This handles a login request by a Player.
     *
     * @param request {HttpServletRequest} - A Request from the server.
     * @param player {Player} - A Player-instance.
     *
     * @return {Response} - Whether the login was successful.
     *                  If it was successful, it contains the Player's name.
     */
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @Path("login")
    public Response login(
			final @Context HttpServletRequest request,
            final Player player
    )
    {
System.out.println("Post on login.");
        HttpSession session = request.getSession(true);
        DataBase db = new DataBase();

        if (db.verifyPassword(player.getName(), player.getPassword()))
        {
            //session.setAttribute("taipan", taipan);
            String output = JSONProcessor.createJSONResponse(player.getName());
    		return Response.status(SUCCESS).entity(output).build();
        }
        return Response.status(FAILURE).build();
    }

    /**
     * This handles a register request by a Player.
     *
     * @param request {HttpServletRequest} - A Request from the server.
     * @param player {Player} - A Player-instance.
     *
     * @return {Response} - Whether the registering was successful.
     *                  If it was successful, it contains the Player's name.
     */
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @Path("register")
    public Response register(
			final @Context HttpServletRequest request,
            final Player player
    )
    {
System.out.println("Post on register.");
        HttpSession session = request.getSession(true);
        DataBase db = new DataBase();

        if (db.addPlayer(player.getName(), player.getPassword()))
        {
            //session.setAttribute("taipan", taipan);
            String output = JSONProcessor.createJSONResponse(player.getName());
    		return Response.status(SUCCESS).entity(output).build();
        }
        return Response.status(FAILURE).build();
    }

    /**
     * This handles a unregister request by a Player.
     *
     * @param request {HttpServletRequest} - A Request from the server.
     * @param player {Player} - A Player-instance.
     *
     * @return {Response} - Whether the unregistering was successful.
     *                  If it was successful, it contains the Player's name.
     */
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @Path("unregister")
    public Response unregister(
			final @Context HttpServletRequest request,
            final Player player
    )
    {
System.out.println("Post on unregister.");
        HttpSession session = request.getSession(true);
        DataBase db = new DataBase();

        if (db.removePlayer(player.getName(), player.getPassword()))
        {
            //session.setAttribute("taipan", taipan);
            String output = JSONProcessor.createJSONResponse(player.getName());
    		return Response.status(SUCCESS).entity(output).build();
        }
        return Response.status(FAILURE).build();
    }

    /**
     * This handles a join request by a Player.
     * It *should* contain a game to join.
     *
     * @param request {HttpServletRequest} - A Request from the server.
     *
     * @return {Response} - Whether the join was successful.
     *                  If it was successful, it contains the Game's name.
     */
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @Path("join")
    public Response joinGame(
			final @Context HttpServletRequest request
    )
    {
System.out.println("Post on join.");
        HttpSession session = request.getSession(true);

		//session.setAttribute("taipan", taipan);
        String output = JSONProcessor.createJSONResponse("PlayerID.");

		return Response.status(SUCCESS).entity(output).build();
    }
}
