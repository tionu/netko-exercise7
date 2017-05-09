package decrypt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CaesarDecryptor {

	char alphabet[];

	public CaesarDecryptor() {
		alphabet = getAlphabet();
	}

	public static void main(String[] args) {
		CaesarDecryptor decryptor = new CaesarDecryptor();
		String inputText = decryptor.readFile("Caesar.txt");
		for (int i = 0; i < 26; i++) {
			System.out.println("Caesar-Key: " + i + " ==> " + decryptor.decrypt(inputText, i));
		}
	}

	public String readFile(String filePath) {
		StringBuilder stringBuilder = null;
		try (BufferedReader cryptoFile = new BufferedReader(new FileReader(filePath))) {
			stringBuilder = new StringBuilder();
			String line = cryptoFile.readLine();
			while (line != null) {
				stringBuilder.append(line);
				stringBuilder.append(System.lineSeparator());
				line = cryptoFile.readLine();
			}
		} catch (FileNotFoundException ignored) {
		} catch (IOException e) {
		}
		return stringBuilder.toString();
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

	private char[] getAlphabet() {
		char[] alphabet = new char[26];
		int asciiA = 65;
		for (int i = 0; i < 26; i++) {
			alphabet[i] = (char) (asciiA + i);
		}
		return alphabet;
	}

}
