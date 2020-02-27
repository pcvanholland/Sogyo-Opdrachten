package taipan.domain;

import java.util.ArrayList;

public class TaiPan
{
    private Player firstPlayer;

    /**
     * Default constructor of a Tai-Pan game.
     */
    public TaiPan()
    {
        firstPlayer = new Player();
    }

    /**
     * Gets the cards of a specific Player.
     *
     * @return {Card[][]} - An ArrayList of ArrayLists of Cards.
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
     * Queries whether the specified Player is in turn.
     *
     * @param playerNumber {int} - The Player to query.
     * @return {boolean} - Whether the specified Player is in turn.
     */
    public boolean isPlayerInTurn(final int playerNumber)
    {
        Player player = this.firstPlayer.getPlayerAtPositionCCW(playerNumber);
        return player.isInTurn();
    }

    /**
     * Returns the Cards of the specified Player.
     *
     * @param playerNumber {int} - The Player to return the Cards of.
     * @return {String[][]} - An ArrayList of the Cards of the specified Player.
     */
    public ArrayList<ArrayList<String>> getCardsOfPlayer(final int playerNumber)
    {
        ArrayList<ArrayList<String>> result =
            new ArrayList<ArrayList<String>>();
        Player player = this.firstPlayer.getPlayerAtPositionCCW(playerNumber);
        for (Card card : player.getCards())
        {
            ArrayList<String> cardString = new ArrayList<String>();
            cardString.add(card.getSuit().toString());
            cardString.add(card.getRank().toString());
            result.add(cardString);
        }
        return result;
    }

    /**
     * Returns the types of Play this set of Cards can have.
     *
     * @param cards {String[]} - An ArrayList of Cards to check.
     * @return {String[]} - An ArrayList of the types of Plays.
     */
    public ArrayList<String> getTypesOfPlay(final String[] cards)
    {
        ArrayList<Card> realCards = new ArrayList<Card>();
        for (String card : cards)
        {
            Card realCard;
            String[] cardProps = card.split(",");
            if (cardProps[0].equals("SPECIAL"))
            {
                realCard = new SpecialCard(SpecialRank.valueOf(cardProps[1]));
            }
            else
            {
                realCard = new PlayingCard(
                    StandardSuit.valueOf(cardProps[0]),
                    StandardRank.valueOf(cardProps[1])
                );
            }
            realCards.add(realCard);
        }

        ArrayList<String> result = new ArrayList<String>();
        for (Set set : Play.determineTypesOfSet(realCards))
        {
            result.add(set.toString());
        }
        return result;
    }

    /**
     * Lets the specified Player draw Cards.
     *
     * @param playerNumber {int} - The Player that wants to draw Cards.
     */
    public void letPlayerDrawCards(final int playerNumber)
    {
        Player player = this.firstPlayer.getPlayerAtPositionCCW(playerNumber);
        player.drawCards();
    }
}
