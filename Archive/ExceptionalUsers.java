public class Tester
{
	public static void main(String[] args)
	{
		String[] temp = { "Abc1", "abc1", "Abc", "abc" };
		for (String pw : temp)
		{
			UserIO.print(Util.verifyPassphraseConstraints(pw, 3));
		}
		UserManagementSystem usersystem = new UserManagementSystem();
		usersystem.newUser();
	}
}

class UserManagementSystem
{
	final private int USERNAME_MINIMAL_LENGTH = 3;
	final private int PASSPRHASE_MINIMAL_LENGTH = 3;

	/**
	 * Constuctor for a new UMS.
	 */
	UserManagementSystem()
	{
		// ToDo?
	}

	/**
	 * Register a new user.
	 */
	public void newUser()
	{
		UserIO.print("Hi! Welcome to this UMS.");
		String userName = askNewUserName();
		String password = askNewPassphrase();

		User user = new User(userName, password);
		UserIO.print("Welcome " + userName + "! Registering has succeeded.");
	}

	/**
	 * Ask for a new username.
	 *
	 * @return {String} - The username chosen by the user.
	 */
	private String askNewUserName()
	{
		while (true)
		{
			String userName = UserIO.askString("Please specify a username:\t");
			if (Util.verifyUserNameConstraints(userName, USERNAME_MINIMAL_LENGTH))
			{
				return userName;
			}
			UserIO.print("The username ought to be at least " + USERNAME_MINIMAL_LENGTH +" characters long.");
		}
	}

	/**
	 * Ask for a new password.
	 *
	 * @return {String} - The passphrase chosen.
	 */
	private String askNewPassphrase()
	{
		while (true)
		{
			String password = UserIO.askString("Please give a passphrase:\t");
			if (Util.verifyPassphraseConstraints(password, PASSPRHASE_MINIMAL_LENGTH))
			{
				return password;
			}
			UserIO.print("The password ought to contain upper- and lowercase letters and at least one number.");
		}
	}
}

class User
{
	private String userName;
	private String passphrase;

	/**
	 * Constuctor for a new user.
	 *
	 * @param {String} givenUserName - The sequence of characters to use as a username.
	 * @param {String} givenPassPhrase - The sequence of characters to use as a passphrase.
	 */
	User(String givenUserName, String givenPassPhrase)
	{
		userName = givenUserName;
		passphrase = givenPassPhrase;
	}
}

class Util
{
	/**
	 * Checks whether the given username meets the quality constraints.
	 *
	 * @param {String} username - The sequence of characters to verify.
	 * @return {boolean} - Whether the constraints are met.
	 */
	public static boolean verifyUserNameConstraints(String username, int minLength)
	{
		return verifyPhraseIsOfSufficientLength(username, minLength);
	}

	/**
	 * Checks whether the given password meets the quality constraints.
	 *
	 * @param {String} passphrase - The sequence of characters to verify.
	 * @return {boolean} - Whether the constraints are met.
	 */
	public static boolean verifyPassphraseConstraints(String passphrase, int minLength)
	{
		return verifyPhraseContainsUpperAndLowerCase(passphrase) &&
				verifyPhraseContainsNumbers(passphrase) &&
				verifyPhraseIsOfSufficientLength(passphrase, minLength);
	}

	/**
	 * Checks whether the given string contains both upper and lower case characters.
	 *
	 * @param {String} string - The sequence of characters to verify.
	 * @return {boolean} - Whether the constraints are met.
	 */
	private static boolean verifyPhraseContainsUpperAndLowerCase(String string)
	{
		return !string.equals(string.toLowerCase()) && !string.equals(string.toUpperCase());
	}

	/**
	 * Checks whether the given string contains any number.
	 * https://stackoverflow.com/questions/18590901/check-if-a-string-contains-numbers-java#18590949
	 *
	 * @param {String} string - The sequence of characters to verify.
	 * @return {boolean} - Whether the constraints are met.
	 */
	private static boolean verifyPhraseContainsNumbers(String string)
	{
		return string.matches(".*\\d.*");
	}

	/**
	 * Checks whether the given string is of sufficient length.
	 *
	 * @param {String} string - The sequence of characters to verify.
	 * @param {int} length - The length to check against.
	 *
	 * @return {boolean} - Whether the length is appropriate.
	 */
	private static boolean verifyPhraseIsOfSufficientLength(String string, int length)
	{
		return string.length() >= length;
	}
}

class UserIO
{
	/**
	 * Print the text to the console.
	 *
	 * @param {String} textToPrint - The string to show to the user.
	 */
	public static void print(String textToPrint)
	{
			System.out.println(textToPrint);
	}

	/**
	 * Print the text to the console.
	 *
	 * @param {boolean} textToPrint - The boolean to show to the user.
	 */
	public static void print(boolean textToPrint)
	{
			System.out.println(textToPrint);
	}

	/**
	 * This asks a string.
	 *
	 * @param {String} textToAsk - The accompanying text.
	 * @return {String} - The string the player has entered.
	 */
	public static String askString(String textToAsk)
	{
		String input;
		while (true)
		{
			try
			{
				input = System.console().readLine(textToAsk + "\t");
				break;
			} catch (IllegalArgumentException ex)
			{
				// Just ask again nicely.
			}
		}
		return input;
	}
}
