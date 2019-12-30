import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class QuoteOfTheDay
{
	// Define some semi-random quotes.
	static String[][] quotes = {
        {"galileo", "eppur si muove"},
        {"archimedes", "eureka!"},
        {"erasmus", "in regione caecorum rex est luscus"},
        {"socrates", "I know nothing except the fact of my ignorance"},
        {"rené descartes", "cogito, ergo sum"},
        {"sir isaac newton", "if I have seen further it is by standing on the shoulders of giants"},
		{"astrid lindgren, pippi longstocking", "I have never tried that before, so I think I should definitely be able to do that"},
		{"cato the censor", "ceterum autem censeo Carthaginem esse delendam"}
    };

	// List of character that are considered line endings.
	static ArrayList<Character> properLineEndings = new ArrayList<Character>(Arrays.asList(
		'!',
		'.',
		'?'
	));

	// List of words that are not capitalised when converting to Title Case.
	static ArrayList<String> wordsNotToCapitaliseInTitleCase = new ArrayList<String>(Arrays.asList(
		"de",
		"van",
		"der",
		"of",
		"the"
	));

    public static void main(String... args)
	{
		Calendar calendar = Calendar.getInstance();
		String[] quote = GetQuote(calendar.get(Calendar.DAY_OF_YEAR));

		// Print a welcome message, in the specified format.
		PrintWelcome(calendar, "EEEEE, dd-MM-yyyy");
        PrintQuote(quote);
    }

	/**
	 * Gets the quote to show.
	 *
	 * @param {int} dayOfTheYear - The day of the year to show the quote for.
			Counted from January the first as 1 onwards.
	 * @return {String[]} - The quote and name of today.
	 */
	private static String[] GetQuote(int dayOfTheYear)
	{
		int index = GetIndexToShow(dayOfTheYear);

		return quotes[index];
	}

	/**
	 * Which index of the quotes ought to be displayed.
	 *
	 * @param {int} dayOfTheYear - The day of the year to use.
	 * @return {int} - The index to use.
	 */
	private static int GetIndexToShow(int dayOfTheYear)
	{
		// "DOTH - 1" Because the January the first is day 1 but needs index 0.
		return (dayOfTheYear - 1) % quotes.length;
	}

	/**
	 * Prints a welcome message.
	 *
	 * @param {Object} calendar - An instance of a calendar.
	 * @param {String} format - How the date that will be showed should be displayed.
	 */
	private static void PrintWelcome(Calendar calendar, String format)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

		System.out.println("Quote for today, " + simpleDateFormat.format(calendar.getTime()) + ":");
	}

	/**
	 * Prints a quote.
	 *
	 * @param {String[]} quote - The unformatted quote to display.
	 */
	private static void PrintQuote(String[] quote)
	{
		String textToPrint = FormatQuote(quote);
		System.out.println(textToPrint);
	}

	/**
	 * This formats a raw quote.
	 * It ensures proper interpunction such as Captital begin letter,
	 * period at end of sentence if applicable.
	 *
	 * @param {String[]} rawQuote - The string-array formatted as { "name", "quote" }.
	 * @return {String} - The quote in the format: "Quote." -- Name .
	 */
	private static String FormatQuote(String[] rawQuote)
	{
		String result;
		String name = rawQuote[0];
		String quote = rawQuote[1];

		// Add full stop when no sentence ender is present.
		if (!properLineEndings.contains(quote.charAt(quote.length() - 1)))
			quote += ".";

		// Capitalise the name and quote.
		quote = CapitaliseFirstLetter(quote);
		name = ConvertToTitleCase(name);

		result = "\"" + quote + "\" -- " + name;
		return result;
	}

	/**
	 * This converts a string to title cased.
	 *
	 * @param {String[]} string - The string to capitalise.
	 * @return {String} - The string in title case.
	 */
	private static String ConvertToTitleCase(String string)
	{
		String[] splitString = string.split(" ");
		String result = "";
		for (int i = 0; i < splitString.length; ++i)
		{
			// Title Case escapes some words.
			if (wordsNotToCapitaliseInTitleCase.contains(splitString[i]))
			{
				// That last " " won't be noticed on a CLI :)
				result += splitString[i] + " ";
				continue;
			}
			// That last " " won't be noticed on a CLI :)
			result += CapitaliseFirstLetter(splitString[i]) + " ";
		}
		return result;
	}

	/**
	 * This converts a string to starting with upper case.
	 *
	 * @param {String[]} string - The string to capitalise.
	 * @return {String} - The string with a captital first letter.
	 */
	private static String CapitaliseFirstLetter(String string)
	{
		return string.replaceFirst(Character.toString(string.charAt(0)),
			Character.toString(string.charAt(0)).toUpperCase());
	}
}
