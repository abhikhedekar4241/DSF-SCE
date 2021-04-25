import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import javax.crypto.Cipher;

public class SenderSide {

  public Scanner sc = new Scanner(System.in);
  Console cnsl = System.console();

  public static String getMd5(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageDigest = md.digest(input.getBytes());
      BigInteger no = new BigInteger(1, messageDigest);
      String hashtext = no.toString(16);
      while (hashtext.length() < 32) {
        hashtext = "0" + hashtext;
      }
      return hashtext;
    } // For specifying wrong message digest algorithms
    catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  public static boolean Login() {
    Console cnsl = System.console();
    System.out.println("Please Enter Your Password: ");
    char[] pwd = cnsl.readPassword();
    String hashedPassword = getMd5(String.valueOf(pwd));
    // char[] mp = cnsl.readPassword();
    String myPassword = getMd5("2812");
    if (myPassword.equals(hashedPassword)) {
      return true;
    } else {
      return false;
    }
  }

  public String encryptMsg(String message) throws Exception {
    Signature sign = Signature.getInstance("SHA256withRSA");

    // Creating KeyPair generator object
    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

    // Initializing the key pair generator
    keyPairGen.initialize(2048);

    // Generate the pair of keys
    KeyPair pair = keyPairGen.generateKeyPair();

    // Creating a Cipher object
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

    // Initializing a Cipher object
    cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());

    // Add data to the cipher
    byte[] input = message.getBytes();
    cipher.update(input);

    // encrypting the data
    byte[] cipherText = cipher.doFinal();
    System.out.println(new String(cipherText, "UTF8"));

    // Initializing the same cipher for decryption
    cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());

    // Decrypting the text
    byte[] decipheredText = cipher.doFinal(cipherText);
    System.out.println(new String(decipheredText));
    String retSt = new String(cipherText, "UTF8");
    return retSt;
  }

  // Driver code
  public static void main(String args[]) throws NoSuchAlgorithmException {
    if (Login()) {
      SenderSide ss = new SenderSide();
      System.out.println("Login Sucsessfully!");
      String encrypyted = null;
      try {
        encrypyted = ss.encryptMsg("hello");
      } catch (Exception e) {
        // TODO: handle exception
      }
      System.out.println(encrypyted);

    } else {
      System.out.println("Failed to Login, Please try again!");
    }
  }
}
