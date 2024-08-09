package utility;

import java.util.Random;

public class RandomDataGenerator {

	public static String generateThreeDigitUniqueValue() {
		Random random = new Random();
		int uniqueValue = 100 + random.nextInt(900); // Generate 3 digit random number
		return String.valueOf(uniqueValue);
	}
}
