package taipan.domain;

final class Phoenix extends SpecialCard
{
    private int value;

    /**
     * Constructor for a Phoenix.
     */
    Phoenix()
    {
        super(SpecialRank.PHOENIX);
        this.value = -1;
    }

    /**
     * @return {int} - The value of this Phoenix.
     */
    @Override
    int getValue()
    {
        return this.value;
    }

    /**
     * Sets the value of this Phoenix.
     *
     * @param newValue {int} - The new value this Phoenix ought to have.
     */
    void setValue(final int newValue)
    {
        this.value = newValue;
    }
}
