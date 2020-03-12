package taipan.domain;

abstract class Player_Test_Helper
{
    // Seed of 3 ensures the first Player to draw Cards gets the turn.
    // Seed of 10 ensures a FOAK Bomb in the first hand.
    // Seed of 100813 is a Straight on the first two hands combined.
    // Seed of 1661 is a Straight Bomb on the first hand.
    // Seed of 28774 is a Straight Bomb on the first hand with MJ.
    static final int START_SEED = 3;
    static final int START_BOMB_SEED = 28774;
    static final int START_DOG_SEED = 3;
    static final int START_STREET_SEED = 100813;

    /**
     * Helper function to create a Player who starts in turn.
     *
     * @return {Player} - A Player who is in turn.
     */
    Player createPlayerInTurn()
    {
        return this.createSeededPlayer(START_SEED);
    }

    /**
     * Helper function to create a Player who starts in turn.
     *
     * @param seed {int} - The seed to use for the Dealer.
     * @return {Player} - A Player who is in turn.
     */
    Player createSeededPlayer(final int seed)
    {
        // Draw Cards to get in turn.
        try
        {
            Player player = new Player(new Table(), seed);
            player.drawCards();
            player.drawCards();
            return player;
        }
        catch (TaiPanException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Helper function to create a full game.
     *
     * @param seed {int} - The seed to use for the Dealer.
     * @return {Player} - The first Player.
     */
    Player createSeededGame(final int seed)
    {
        Player player = this.createSeededPlayer(seed);
        try
        {
            player.getPlayerAtPositionCCW(1).drawCards();
            player.getPlayerAtPositionCCW(1).drawCards();
            player.getPlayerAtPositionCCW(2).drawCards();
            player.getPlayerAtPositionCCW(2).drawCards();
            player.getPlayerAtPositionCCW(3).drawCards();
            player.getPlayerAtPositionCCW(3).drawCards();
        }
        catch (TaiPanException e)
        {
            e.printStackTrace();
        }

        return player;
    }
}
