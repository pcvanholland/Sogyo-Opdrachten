package taipan.api;

/**
 * This class is used to let a Player join a game.
 */
public class JoinRequest
{
    private int gameID;
    private String playerName;

    /**
     * @return {String} - The name of the Player.
     */
    public String getPlayerName()
    {
        return this.playerName;
    }

    /**
     * @return {int} - The ID of the game to join.
     */
    public int getGameID()
    {
        return this.gameID;
    }

    /**
     * Sets the Player's name.
     * @param newName {String} - The name of the Player performing the request.
     */
    public void setPlayerName(final String newName)
    {
        this.playerName = newName;
    }

    /**
     * Sets the gameID to join.
     * @param newID {int} - The game to join.
     */
    public void setGameID(final int newID)
    {
        this.gameID = newID;
    }
}
