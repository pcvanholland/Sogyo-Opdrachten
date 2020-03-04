package taipan.api;

/**
 * This class represents a TaiPan game.
 */
public class Game
{
    private taipan.domain.TaiPan implementation;

    Game()
    {
        this.implementation = new taipan.domain.TaiPan(28774);
    }

    /**
     * @return {TaiPan} - The TaiPan instance.
     */
    protected taipan.domain.TaiPan getImplementation()
    {
        return this.implementation;
    }
}
