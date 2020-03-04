package taipan.api;

/**
 * This class represents a TaiPan game.
 */
public class Game
{
    private taipan.domain.TaiPan implementation;
    private PlayerData players;

    Game(final String host)
    {
        this.players = new PlayerData();
        this.implementation = new taipan.domain.TaiPan(28774);
        this.players.addPlayer(host);
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
     */
    protected void joinGame(final String newPlayer)
    {
        this.players.addPlayer(newPlayer);
    }

    /**
     * @return {boolean} - Whether this Game is full.
     */
    protected boolean isFull()
    {
        return this.players.isGameFull();
    }
}
