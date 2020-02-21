package taipan.domain;

import java.util.ArrayList;

class FullHouse extends Play
{
    private Triple triple;
    private Pair pair;

    FullHouse(final Triple newTriple, final Pair newPair)
    {
        super(getAllCards(newTriple, newPair));
        this.triple = newTriple;
        this.pair = newPair;
    }

    FullHouse(final Pair newPair, final Triple newTriple)
    {
        this(newTriple, newPair);
    }

    /**
     * Calculates the value of this Play. I.e. the rank of the triple.
     *
     * @return {int} - The value of this Play.
     */
    @Override
    protected int getValue()
    {
        return this.triple.getValue();
    }

    /**
     *
     */
    final private static ArrayList<Card> getAllCards(
        final Triple newTriple, final Pair newPair
    )
    {
        ArrayList<Card> result = new ArrayList<Card>();
        result.addAll(newTriple.getCards());
        result.addAll(newPair.getCards());
        return result;
    }
}
