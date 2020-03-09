package taipan.domain;

class Phoenix extends SpecialCard
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
    protected int getValue()
    {
        return this.value;
    }

    /**
     * Sets the value of this Phoenix.
     *
     * @param newValue {int} - The new value this Phoenix ought to have.
     */
    protected void setValue(final int newValue)
    {
        this.value = newValue;
    }
}
