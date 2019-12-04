public class LeapYear {
	public static void main (String[] args) {
		int year = AskInteger("Enter a year to test for leapness: ");
		TestLeapyear(year);
	}
	private static void TestLeapyear(int maxIte) {
		if (maxIte % 4 != 0)
		{
			System.out.println("No leap.");
			return;
		}

		if (maxIte % 100 == 0 && maxIte % 400 != 0)
		{
			System.out.println("No leap.");
			return;
		}
		System.out.println("Leap.");
	}
	private static void DoTheTestDifferently(int maxIte) {
		if (maxIte % 4 == 0)
			if (maxIte % 100 != 0 || maxIte % 400 == 0)
			{
				System.out.println("Leap.");
				return;
			}
		System.out.println("No leap.");
	}
	private static int AskInteger(String textToAsk) {
		int year;
		while (true) {
			try {
				year = Integer.parseInt(System.console().readLine(textToAsk));
				break;
			} catch (NumberFormatException ex) {
				System.out.println("Please give an integer year.");
			}
		}
		return year;
	}
}
