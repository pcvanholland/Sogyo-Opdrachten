package taipan.domain;

import java.util.ArrayList;

public interface IPlay
{
    /**
     * Gets the cards belonging to this Play.
     *
     * @return {Card[]} - An ArrayList of Cards in this Play.
     */
    ArrayList<Card> getCards();
}
