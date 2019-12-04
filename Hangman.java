import java.util.ArrayList;
import java.util.Random;

public class Hangman
{
	public static void main (String[] args)
	{
		PlayGame();
	}

	/**
	 * This inits a game.
	 */
	private static void PlayGame()
	{
		// Get a random "word". And initialise the game.
		String word = GetSequence();
		Game game = new Game(word, 10);
		game.ShowStatus();

		while (!(game.HasWon() || game.HasLost()))
		{
			char character = game.AskCharacter("Please type a character: ");
			if (game.TestCharacterInWord(character))
				game.RegisterRightCharacter(character, "Nice one, this is in!\n");
			else
				game.RegisterWrongCharacter(character, "Too bad, this character is not present in the string ;'(\n");

			game.ShowStatus();
		}
		game.HandleEndGame();
	}

	private static String GetSequence()
	{
		// It would be nice to get these from a file.
		String[] listOfWords = {
			"Something is afaul.",
			"Sogyo rocks?",
			"computerbeeldscherm",
			"Uier?",
			"Hottentottententententoonstellingstoiletgebouwschoonmaakjuffrouw",
			"Of niet,,,",
			"Java? What's that!?",
			"The quick brown fox jumps over the lazy dog",
			"LoremIpsum",
			"Whoe to the defeated",
			"Carthago delenda est",
			"John 14 verse 6."
		};
		int randInt = new Random().nextInt(listOfWords.length);
		return listOfWords[randInt];
	}
}

public class Game
{
	private String wordToGuess;
	private ArrayList<Character> wordToGuessArray = new ArrayList<Character>();
	private ArrayList<Character> wordToGuessObscured = new ArrayList<Character>();
	private static ArrayList<Character> usedCharacters = new ArrayList<Character>();
	private static ArrayList<Character> wrongCharacters = new ArrayList<Character>();
	private int triesLeft;

	/**
	 * This constructs the game.
	 *
	 * @param {String} givenWord - The word that needs to be guessed.
	 * @param {int} maxTries - The amount of tries allowed.
	 *
	 */
	Game(String givenWord, int maxTries)
	{
		System.out.println("Welcome to Hangman! =D");
		wordToGuess = givenWord;
		triesLeft = maxTries;
		GivenWordToArray();
		ObscureWord();
	}

	/**
	 * This converts the givenWord to an array.
	 *
	 */
	private void GivenWordToArray()
	{
		String[] splitString = wordToGuess.split("");
		for (int i = 0; i < splitString.length; ++i)
			wordToGuessArray.add(splitString[i].charAt(0));
	}

	/**
	 * This obscures the word to be guessed to "*"'s.
	 *
	 */
	private void ObscureWord()
	{
		for (int i = 0; i < wordToGuessArray.size(); ++i)
			wordToGuessObscured.add('*');
	}

	/**
	 * This tests a given character against the wordToGuess.
	 *
	 * @param {char} character - The character to test.
	 * @return {boolean} - Whether the character is present in the wordToGuess.
	 */
	public boolean TestCharacterInWord(char character)
	{
		for (int i = 0; i < wordToGuessArray.size(); ++i)
			if (Character.toLowerCase(wordToGuessArray.get(i)) == character)
				return true;

		return false;
	}

	/**
	 * This replaces all occurences of the given character in the wordToGuess
	 * in the obscuredWord, respectively.
	 *
	 * @param {char} character - The character to test.
	 */
	private void ReplaceCharacterInWord(char character)
	{
		for (int i = 0; i < wordToGuessArray.size(); ++i)
			if (Character.toLowerCase(wordToGuessArray.get(i)) == character)
				wordToGuessObscured.set(i, wordToGuessArray.get(i));
	}

	/**
	 * This tests a given character against the already used characters.
	 *
	 * @param {char} character - The character to test.
	 * @return {boolean} - Whether the character is present in the list of used characters.
	 */
	private static boolean TestCharacterUsed(char character)
	{
		return usedCharacters.contains(character);
	}

	/**
	 * This handles a right character.
	 *
	 * @param {char} character - The character that was right.
	 * @param {String} notifyRightCharacterText - The text shown to the user.
	 */
	public void RegisterRightCharacter(char character, String notifyRightCharacterText)
	{
		ReplaceCharacterInWord(character);
		System.out.println(notifyRightCharacterText);
	}

	/**
	 * This handles a wrong character.
	 *
	 * @param {char} character - The character that was wrong.
	 * @param {String} notifyWrongCharacterText - The text shown to the user.
	 */
	public void RegisterWrongCharacter(char character, String notifyWrongCharacterText)
	{
		--triesLeft;
		wrongCharacters.add(character);
		System.out.println(notifyWrongCharacterText);
	}

	/**
	 * This prints the current state of the game.
	 *
	 */
	public void ShowStatus()
	{
		System.out.print("The sequence:\t");
		for (int i = 0; i < wordToGuessObscured.size(); ++i)
			System.out.print(wordToGuessObscured.get(i));
		System.out.println();

		System.out.println("Tries left:\t\t" + triesLeft);
		System.out.println("Wrong characters:\t" + wrongCharacters);
	}

	/**
	 * This test whether the game is won.
	 *
	 */
	public boolean HasWon()
	{
		return wordToGuessObscured.equals(wordToGuessArray);
	}

	/**
	 * This test whether the game is lost.
	 *
	 */
	public boolean HasLost()
	{
		return triesLeft < 1;
	}

	/**
	 * This handles the end of a game.
	 * Prints a message whether the player has either won or lost.
	 *
	 */
	public void HandleEndGame()
	{
		if (HasWon())
			System.out.println("Congratz.");
		else if (HasLost())
			System.out.println("Congratz you lost.");
	}

	/**
	 * This asks a character.
	 *
	 * @param {String} textToAsk - The accompanying text.
	 * @return {character} - The character the player has entered (in lower case).
	 */
	public static char AskCharacter(String textToAsk)
	{
		char input;
		while (true) {
			try {
				input = Character.toLowerCase(System.console().readLine(textToAsk).charAt(0));
				if (TestCharacterUsed(input))
				{
					System.out.println("This character has already been used, please try another.");
					continue;
				}
				break;
			} catch (StringIndexOutOfBoundsException ex) {
			}
		}
		usedCharacters.add(input);
		return input;
	}
}