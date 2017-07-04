package aesdes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.spec.SecretKeySpec;

import decrypt.util.FileUtils;

public class CryptPerformance {

	private static byte[] key;

	public static void main(String[] args) {

		String aesTransformation = "AES/CBC/PKCS5Padding";
		String desTransformation = "DES/CBC/PKCS5Padding";

		String testKey = "owzk3z!1Z^68W5G#";

		SecretKeySpec aesKey = getKey(testKey, 16, "AES");
		SecretKeySpec desKey = getKey(testKey, 8, "DES");

		String inputText = FileUtils.readFile("AES.txt");
		System.out.println("Eingabe: " + inputText);

		String aesEncypted = Crypt.encrypt(inputText, aesKey, aesTransformation);
		System.out.println("Verschlüsselt mit " + aesTransformation + ": " + aesEncypted + "\n");

		System.out.println("Geschwindigkeitsvergleich AES vs. DES:");

		String largeText = FileUtils.readFile("GrosseDatei.dat");

		encryptPerformance(aesTransformation, aesKey, largeText);
		encryptPerformance(desTransformation, desKey, largeText);

	}

	private static void encryptPerformance(String transformation, SecretKeySpec key, String largeText) {
		long startEncryptTime;
		long endEncryptTime;
		startEncryptTime = System.nanoTime();
		Crypt.encrypt(largeText, key, transformation);
		endEncryptTime = System.nanoTime();
		System.out.println("Dauer mit " + transformation + ": " + (endEncryptTime - startEncryptTime) / 1e6 + "ms");
	}

	private static SecretKeySpec getKey(String myKey, int bytes, String algorithm) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, bytes);
			return new SecretKeySpec(key, algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
