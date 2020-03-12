package taipan.domain;

abstract class TaiPan_Test_Helper
{
    /**
     * Creates a seeded TaiPan-game.
     *
     * @param seed {int} - The seed to use for the Dealer.
     * @return {TaiPan} - The TaiPan-game created.
     */
    TaiPan createSeededGame(final int seed)
    {
        try
        {
            TaiPan taipan = new TaiPan(seed);
            for (int i = 0; i < Player.NUM_PLAYERS; ++i)
            {
                taipan.letPlayerDrawCards(i);
                taipan.letPlayerDrawCards(i);
            }
            return taipan;
        }
        catch (TaiPanException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
