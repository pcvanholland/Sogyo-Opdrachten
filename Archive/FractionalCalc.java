public class Tester
{
	public static void main(String[] args)
	{
		Fraction frac = new Fraction(-14, 90);
		Print(frac.toString());
		frac.add(1);
		Print(frac.toString());
		frac.subtract(1);
		Print(frac.toString());
		frac.multiply(-1);
		Print(frac.toString());
		frac.divide(7);
		Print(frac.toString());
		frac.divide(new Fraction(1, 3));
		Print(frac.toString());
		frac.multiply(new Fraction(1, 2));
		Print(frac.toString());
		frac.add(new Fraction(1, 30));
		Print(frac.toString());
		frac.subtract(new Fraction(1, 6));
		Print(frac.toString());
		System.out.println(frac.toDecimalNotation());
	}

	// Debug Prints, can be removed when done.
	private static void Print(String string)
	{
		System.out.println(string);
	}
	private static void Print(int integer)
	{
		System.out.println(integer);
	}
}

class Fraction
{
	int numerator;
	int denominator;

	// Useful self-made function(s).
	Utilities util = new Utilities();

	/**
	 * Constructs the fraction with default settings.
	 */
	Fraction()
	{
		numerator = 1;
		denominator = 1;
	}

	/**
	 * Constructs the fraction with given numbers.
	 */
	Fraction(int num, int denom)
	{
		numerator = num;
		denominator = denom;
		simplify();
	}

	/**
	 * Returns the fraction to a string.
	 *
	 * @return {String} - The Fraction in the form "num/denom".
	 */
	public String toString()
	{
		return numerator + "/" + denominator;
	}

	/**
	 * Returns the faction to a float.
	 */
	public float toDecimalNotation()
	{
		float num = numerator;
		float denom = denominator;

		return num / denom;
	}

	/**
	 * Simplifies the fractional.
	 * It is very nice to call this after every operation.
	 */
	private void simplify()
	{
		int cf = util.getGreatestCommonFactor(numerator, denominator);
		numerator /= cf;
		denominator /= cf;
	}

	/**
	 * Adds an integer to the fractional.
	 *
	 * @param {integer} value - The integer to add to this fractional. (Can be negative.)
	 */
	public void add(int value)
	{
		numerator += denominator * value;

		simplify();
	}

	/**
	 * Subtracts an integer from the fractional.
	 *
	 * @param {integer} value - The integer to subtract from this fractional. (Can be negative.)
	 */
	public void subtract(int value)
	{
		add(-value);

		simplify();
	}

	/**
	 * Adds a fractional to the fractional.
	 *
	 * @param {Fraction} value - The Fraction to add to this fractional. (Can be negative.)
	 */
	public void add(Fraction value)
	{
		numerator = (numerator * value.denominator) + (denominator * value.numerator);
		denominator = denominator * value.denominator;
		
		simplify();
	}

	/**
	 * Subtract a fractional from the fractional.
	 *
	 * @param {Fraction} value - The Fraction to subtract from this fractional. (Can be negative.)
	 */
	public void subtract(Fraction value)
	{
		numerator = (numerator * value.denominator) - (denominator * value.numerator);
		denominator = denominator * value.denominator;
		
		simplify();
	}

	/**
	 * Multiply the fractional with an integer.
	 *
	 * @param {int} value - The integer value to multiply this fractional with. (Can be negative.)
	 */
	public void multiply(int value)
	{
		numerator *= value;

		simplify();
	}

	/**
	 * Multiplies the fractional with a fractional.
	 *
	 * @param {Fraction} value - The Fraction to multiply this fractional with. (Can be negative.)
	 */
	public void multiply(Fraction value)
	{
		numerator *= value.numerator;
		denominator *= value.denominator;

		simplify();
	}

	/**
	 * Divide the fractional by an integer.
	 *
	 * @param {int} value - The integer value to divide this fractional by. (Can be negative.)
	 */
	public void divide(int value)
	{
		denominator *= value;

		simplify();
	}

	/**
	 * Divides the fractional by a fractional.
	 *
	 * @param {Fraction} value - The Fraction to divide this fractional by. (Can be negative.)
	 */
	public void divide(Fraction value)
	{
		numerator *= value.denominator;
		denominator *= value.numerator;

		simplify();
	}
}

/**
 * A class containing a/some useful function(s).
 */
class Utilities
{
	/**
	 * Calculates the greates common factor of two integers.
	 * (https://stackoverflow.com/questions/4009198/java-get-greatest-common-divisor#4009247)
	 */
	public int getGreatestCommonFactor(int a, int b)
	{
		if (b == 0)
		{
			return Math.abs(a);
		}
		return getGreatestCommonFactor(b, a % b);
	}
}
