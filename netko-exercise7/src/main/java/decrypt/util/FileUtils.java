package decrypt.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

	public static String readFile(String filePath) {

		StringBuilder stringBuilder = null;
		try (BufferedReader cryptoFile = new BufferedReader(new FileReader(filePath))) {
			stringBuilder = new StringBuilder();
			String line = cryptoFile.readLine();
			while (line != null) {
				stringBuilder.append(line);
				stringBuilder.append(System.lineSeparator());
				line = cryptoFile.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stringBuilder.toString();
	}
}
