package decrypt;

public class CaesarDecryptor {

	char alphabet[];

	public CaesarDecryptor() {
		alphabet = DecryptUtils.getAlphabet();
	}

	public String decrypt(String input, int offset) {

		StringBuilder output = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {

			if (input.charAt(i) == ' ') {
				output.append(' ');
				continue;
			}
			int asciiNum = ((int) input.charAt(i)) + offset;
			if (asciiNum > 90) {
				asciiNum -= 26;
			}
			output.append((char) asciiNum);
		}
		return output.toString();
	}

}
