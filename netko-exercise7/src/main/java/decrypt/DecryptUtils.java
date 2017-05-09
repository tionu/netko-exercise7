package decrypt;

public class DecryptUtils {
	public static char[] getAlphabet() {
		char[] alphabet = new char[26];
		int asciiA = 65;
		for (int i = 0; i < 26; i++) {
			alphabet[i] = (char) (asciiA + i);
		}
		return alphabet;
	}
}
