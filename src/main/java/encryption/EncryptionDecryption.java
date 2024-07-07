package encryption;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;

public class EncryptionDecryption {
    /**
     * This is a small program that uses Java native library to do encryption and description,
     * credit: <a href="https://stackoverflow.com/a/28624160/4117496">StackOverflow</a>
     */

    private static String salt;
    private static int iterations = 65536;
    private static int keySize = 256;
    private static byte[] ivBytes;

    private static SecretKey secretKey;

    public static void main(String[] args) throws Exception {
        System.out.println("Program started.");
        salt = getSalt();
        System.out.println("salt is: " + salt);
        char[] message = "PasswordToEncrypt".toCharArray();
        System.out.println("Message: " + String.valueOf(message));
        System.out.println("Encrypted: " + encrypt(message));
        System.out.println("Decrypted: " + decrypt(encrypt(message).toCharArray()));
        System.out.println("Program ended.");
    }

    public static String encrypt(char[] plaintext) throws Exception {
        byte[] saltBytes = salt.getBytes();

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec spec = new PBEKeySpec(plaintext, saltBytes, iterations, keySize);
        secretKey = secretKeyFactory.generateSecret(spec);
        SecretKeySpec secretSpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretSpec);
        AlgorithmParameters algorithmParameters = cipher.getParameters();
        ivBytes = algorithmParameters.getParameterSpec(IvParameterSpec.class).getIV();
        byte[] encryptedTextBytes = cipher.doFinal(String.valueOf(plaintext).getBytes("UTF-8"));

        return DatatypeConverter.printBase64Binary(encryptedTextBytes);
    }

    public static String decrypt(char[] encryptedText) throws Exception {
        byte[] encryptedTextBytes = DatatypeConverter.parseBase64Binary(new String(encryptedText));
        SecretKeySpec secretSpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretSpec, new IvParameterSpec(ivBytes));

        byte[] decryptedTextBytes = null;

        try {
            decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return new String(decryptedTextBytes);

    }

    public static String getSalt() throws Exception {
        //https://docs.oracle.com/en/java/javase/22/docs/specs/security/standard-names.html
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");//this is the default algorithm, so can be omitted
        byte[] salt = new byte[20];
        secureRandom.nextBytes(salt);
        return new String(salt);
    }
}
