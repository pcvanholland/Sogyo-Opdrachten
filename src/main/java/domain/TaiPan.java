package taipan.domain;

import java.util.ArrayList;

public class TaiPan
{
    private Player firstPlayer;

    public TaiPan()
    {
        firstPlayer = new Player();
    }

    /**
     * Gets the cards of a specific Player.
     */
    public ArrayList<ArrayList<Card>> getGameState()
    {
        ArrayList<ArrayList<Card>> result = new ArrayList<ArrayList<Card>>();
        for (int i = 0; i < Player.NUM_PLAYERS; ++i)
        {
            Player player = this.firstPlayer.getPlayerAtPositionCCW(i);
            result.add(player.getCards());
        }
        return result;
    }

    /**
     * Lets the specified Player draw cards.
     *
     * @param player {int} - The Player that wants to draw cards.
     */
    public void letPlayerDrawCards(final int playerNumber)
    {
        Player player = this.firstPlayer.getPlayerAtPositionCCW(playerNumber);
        player.drawCards();
    }
}
