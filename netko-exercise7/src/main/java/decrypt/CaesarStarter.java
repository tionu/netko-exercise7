package decrypt;

public class CaesarStarter {

	public static void main(String[] args) {
		CaesarDecryptor decryptor = new CaesarDecryptor();
		String inputText = FileUtils.readFile("Caesar.txt");
		for (int i = 0; i < 26; i++) {
			System.out.println("Caesar-Key: " + i + " ==> " + decryptor.decrypt(inputText, i));
		}
	}

}
