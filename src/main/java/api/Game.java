package taipan.api;

/**
 * This class represents a TaiPan game.
 */
public class Game
{
    private static int counter = 0;

    private taipan.domain.TaiPan implementation;
    private PlayerData players;
    private int gameID;

    Game(final String host)
    {
        this.players = new PlayerData();
        this.implementation = new taipan.domain.TaiPan();
        this.players.addPlayer(host);
        this.gameID = counter++;
    }

    /**
     * @return {TaiPan} - The TaiPan instance.
     */
    protected taipan.domain.TaiPan getImplementation()
    {
        return this.implementation;
    }

    /**
     * Adds a Player to this Game.
     *
     * @param newPlayer {String} - The Player to add.
     * @return {int} - The playerID of the added Player.
     */
    protected int joinGame(final String newPlayer)
    {
        return this.players.addPlayer(newPlayer);
    }

    /**
     * @return {boolean} - Whether this Game is full.
     */
    protected boolean isFull()
    {
        return this.players.isGameFull();
    }

    /**
     * @return {int} - The ID of this Game.
     */
    protected int getID()
    {
        return this.gameID;
    }

    /**
     * @return {int} - The ID of this Game.
     */
    protected String getHostName()
    {
        return this.players.getPlayerName(0);
    }
}
