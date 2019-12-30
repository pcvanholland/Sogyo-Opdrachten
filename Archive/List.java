public class List {
	public static void main (String[] args)
	{
		int[] array = InitArray(0, 100, 10);
		PrintArray(array, "Array: ");

		int highest = GetHighest(array);
		System.out.println("Highest: " + highest);

		int lowest = GetLowest(array);
		System.out.println("Lowest: " + lowest);

		int secondLowest = GetSecondLowest(array);
		System.out.println("Second lowest: " + secondLowest);

		System.out.println("Adding the two lowest values gives: " + (lowest + secondLowest));

		int[] evenValues = GetDividableValues(array, 2);
		PrintArray(evenValues, "Even values: ");

		int[] dividableByThreeValues = GetDividableValues(array, 3);
		PrintArray(dividableByThreeValues, "Dividable by 3: ");

		int[] dividableByFiveValues = GetDividableValues(array, 5);
		PrintArray(dividableByFiveValues, "Dividable by 5: ");

		int[] nonDividableArray = {2, 3, 5};
		int[] nonDividableValues = GetNonDividableValues(array, nonDividableArray);
		PrintArray(nonDividableValues, "Neither dividable by 2, 3 nor 5: ");

		int[] sortedArray = SortArray(array, true);
		PrintArray(sortedArray, "Sorted array: ");
	}

	/**
	 * This creates and returns an array with the given parameters.
	 *
	 * @param {integer} minValue - The minimal value to be allowed in the array.
	 * @param {integer} maxValue - The maximum value to be allowed in the array.
	 * @param {integer} maxIte - The length of the array.
	 *
	 * @return {integer[]} - An array containing integers.
	 */
	private static int[] InitArray(int minValue, int maxValue, int maxIte)
	{
		int[] randomIntArray = new int[maxIte];
		for (int i = 0; i < randomIntArray.length; i++)
		{
			randomIntArray[i] = (int) (Math.random() * maxValue + minValue + 1);
		}
		return randomIntArray;
	}

	/**
	 * This prints an array by starting with brackets,
	 * iterating over the array and closing with brackets.
	 *
	 * @param {integer[]} array - The array to print.
	 * @param {String} string - The string to print in front of the array.
	 */
	private static void PrintArray(int[] array, String string)
	{
		System.out.print(string + "[");
		for (int i = 0; i < array.length; ++i)
		{
			// When printing the last value we want to ommit the extra ", ".
			if (i == array.length - 1)
				System.out.print(array[i]);
			else
				System.out.print(array[i] + ", ");
		}
		System.out.println("]");
	}

	/**
	 * This gets the highest value present in the array.
	 *
	 * @param {integer[]} array - The array to check.
	 * @return {integer} - The highest value present in this array.
	 */
	private static int GetHighest(int[] array)
	{
		int highest = -1;
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] > highest)
				highest = array[i];
		}
		return highest;
	}

	/**
	 * This gets the lowest value present in the array.
	 *
	 * @param {integer[]} array - The array to check.
	 * @return {integer} - The lowest value present in this array.
	 */
	private static int GetLowest(int[] array)
	{
		int lowest = 100;
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] < lowest)
				lowest = array[i];
		}
		return lowest;
	}

	/**
	 * This gets the second lowest value present in the array.
	 *
	 * @param {integer[]} array - The array to check.
	 * @return {integer} - The second lowest value present in this array.
	 */
	private static int GetSecondLowest(int[] array)
	{
		int lowest = 100;
		int secondLowest = 100;
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] < lowest)
				lowest = array[i];
			else if (array[i] >= lowest && array[i] < secondLowest)
				secondLowest = array[i];
		}
		return secondLowest;
	}

	/**
	 * This gets the values dividable by the divider, present in the array.
	 *
	 * @param {integer[]} array - The array to check.
	 * @param {integer} divider - The division to use.
	 *
	 * @return {integer[]} - An array containing the matching values.
	 */
	private static int[] GetDividableValues(int[] array, int divider)
	{
		// Double work done here, it would be nicer to use ArrayList
		// and related functions,,,
		int numberOfMatches = 0;
		for (int i = 0; i < array.length; ++i)
			if (array[i] % divider == 0)
				++numberOfMatches;

		int[] matches = new int[numberOfMatches];
		for (int i = 0; i < array.length; i++)
			if (array[i] % divider == 0)
			{
				// Slightly hacky: we use the number of matching numbers as the location
				// to assign a new even value to (decrease it by one every time used
				// since an array starts at location "0").
				numberOfMatches--;
				matches[numberOfMatches] = array[i];
			}

		return matches;
	}

	/**
	 * This gets the values *NOT* dividable by any of the dividers.
	 *
	 * @param {integer[]} array - The array to check.
	 * @param {integer[]} dividers - The divisions to use.
	 *
	 * @return {integer[]} - An array containing the matching values.
	 */
	private static int[] GetNonDividableValues(int[] array, int[] dividers)
	{
		// Double work done here, it would be nicer to use ArrayList
		// and related functions,,,
		int numberOfMatches = 0;
		for (int i = 0; i < array.length; ++i)
		{
			boolean match = true;
			for (int j = 0; j < dividers.length; ++j)
			{
				if (array[i] % dividers[j] == 0)
				{
					match = false;
					break;
				}
			}
			if (match)
				++numberOfMatches;
		}

		int[] matches = new int[numberOfMatches];
		for (int i = 0; i < array.length; i++)
		{
			boolean match = true;
			for (int j = 0; j < dividers.length; ++j)
			{
				if (array[i] % dividers[j] == 0)
				{
					match = false;
					break;
				}
			}
			if (match)
			{
				// Slightly hacky: we use the number of matching numbers as the location
				// to assign a new even value to (decrease it by one every time used
				// since an array starts at location "0").
				numberOfMatches--;
				matches[numberOfMatches] = array[i];
			}
		}

		return matches;
	}

	/**
	 * This sorts an array.
	 *
	 * @param {integer[]} array - The array to sort.
	 * @param {boolean} ascending - Whether to sort ascending.
	 *
	 * @return {integer[]} - The array but then sorted.
	 */
	private static int[] SortArray(int[] array, boolean ascending)
	{
		// Store it for we need it often.
		int length = array.length;

		// We create a copy due to referencing.
		// (Would be easier with just using System.arraycopy,,,)
		int[] arraySorted = new int[length];
		for (int i = 0; i < length; ++i)
			arraySorted[i] = array[i];

		while (true)
		{
			boolean changed = false;
			for (int i = 0; i < length - 1; i++)
			{
				if ((arraySorted[i] > arraySorted[i+1]) == ascending)
				{
					int temporarySave = arraySorted[i];
					arraySorted[i] = arraySorted[i+1];
					arraySorted[i+1] = temporarySave;
					changed = true;
				}
			}
			if (!changed)
				return arraySorted;
		}
	}
}
