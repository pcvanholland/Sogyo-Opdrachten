package taipan.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import javax.ws.rs.PUT;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/game/")
public class GameConnection
{
    private static final int SUCCESS = 200;
    private static final int FAILURE = 500;

    /**
     * This handles a play request by a Player.
     *
     * @param request {HttpServletRequest} - A Request from the server.
     * @param play {Play} - A Play-instance.
     *
     * @return {Response} - Whether the Play was successful.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("playcards")
    public Response playCards(
        final @Context HttpServletRequest request,
        final Play play
    )
    {
System.out.println("Post on play: " +
    play.getCards() + " as " + play.getType() + " by " + play.getPlayerID()
);
        HttpSession session = request.getSession(false);
        if (session != null)
        {
            TaiPan taipan = (TaiPan) session.getAttribute("game");
            try
            {
                taipan.getImplementation().play(
                    Integer.parseInt(play.getPlayerID()),
                    JSONProcessor.createCardsFromJSON(play.getCards()),
                    JSONProcessor.createSetFromJSON(play.getType())
                );
                return this.returnGameState(taipan);
            }
            catch (taipan.domain.TaiPanException e)
            {
                e.printStackTrace();
            }
        }
        return Response.status(FAILURE).build();
    }

    /**
     * This handles a pass request by a Player.
     *
     * @param request {HttpServletRequest} - A Request from the server.
     * @param player {int} - The Player who wants to pass.
     *
     * @return {Response} - Whether the Play was successful.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("passturn")
    public Response passTurn(
        final @Context HttpServletRequest request,
        final int player
    )
    {
System.out.println("Post on pass turn: " + player);
        HttpSession session = request.getSession(false);
        if (session != null)
        {
            try
            {
                TaiPan taipan = (TaiPan) session.getAttribute("game");
                taipan.getImplementation().pass(player);
                return this.returnGameState(taipan);
            }
            catch (taipan.domain.TaiPanException e)
            {
                e.printStackTrace();
            }
        }
        return Response.status(FAILURE).build();
    }

    /**
     * This handles the computing of possible Plays for a given set of Cards.
     *
     * @param request {HttpServletRequest} - A Request from the server.
     * @param play {String} - A set of Cards.
     *
     * @return {Response} - The possible sets.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getplaytypes")
    public Response getPlayTypes(
        final @Context HttpServletRequest request,
        final String play
    )
    {
System.out.println("Post on getplaytypes: " + play);
        HttpSession session = request.getSession(false);
        if (session != null)
        {
            try
            {
                TaiPan taipan = (TaiPan) session.getAttribute("game");
                String output =
                JSONProcessor.createJSONPlayTypes(
                taipan.getImplementation(), play
                );
                return Response.status(SUCCESS).entity(output).build();
            }
            catch (taipan.domain.TaiPanException e)
            {
                e.printStackTrace();
            }
        }

        return Response.status(FAILURE).build();
    }

    /**
     * This handles a get GameState request by a Player.
     *
     * @param request {HttpServletRequest} - A Request from the server.
     *
     * @return {Response} - The current GameState.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getgamestate")
    public Response getGameState(
        final @Context HttpServletRequest request
    )
    {
System.out.println("Get on GGS.");
        HttpSession session = request.getSession(false);
        if (session != null)
        {
            try
            {
                TaiPan taipan = (TaiPan) session.getAttribute("game");
                return this.returnGameState(taipan);
            }
            catch (taipan.domain.TaiPanException e)
            {
                e.printStackTrace();
            }
        }

        return Response.status(FAILURE).build();
    }

    /**
     * This handles a Card drawing request by a Player.
     *
     * @param request {HttpServletRequest} - A Request from the server.
     * @param player {int} - Which Player asks for Cards.
     *
     * @return {Response} - Whether the drawingOfCards was successful.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("drawcards")
    public Response drawCards(
        final @Context HttpServletRequest request,
        final int player
    )
    {
System.out.println("Post on drawCards: " + player);
        HttpSession session = request.getSession(false);
        if (session != null)
        {
            try
            {
                TaiPan taipan = (TaiPan) session.getAttribute("game");
                taipan.getImplementation().letPlayerDrawCards(player);
                return this.returnGameState(taipan);
            }
            catch (taipan.domain.TaiPanException e)
            {
                e.printStackTrace();
            }
        }

        return Response.status(FAILURE).build();
    }

    /**
     * Returns the current GameState as success.
     *
     * @param game {Game} - The coupled instance of a TaiPan-game.
     * @return {Response} - The GameState of the provided game.
     */
    private Response returnGameState(final TaiPan game) throws
        taipan.domain.TaiPanException
    {
        String output = JSONProcessor.createJSONGameState(
            game.getImplementation()
        ).toJSONString();
        return Response.status(SUCCESS).entity(output).build();
    }
}
