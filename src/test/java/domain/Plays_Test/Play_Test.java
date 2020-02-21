package taipan.domain;

public abstract class Play_Test
{
    private final static java.util.Random rng = new java.util.Random(0);

    /**
     * Creates a Single Card with a random Suit.
     *
     * @param value {int} - The value the card ought to have.
     * @return {Card} - The Card created.
     */
    final static Card createRandomCard(final int value)
    {
        return new PlayingCard(
            Suit.values()[rng.nextInt(3)],
            StandardRank.values()[value - 2]
        );
    }
}
