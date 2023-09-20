/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author tubinh
 */
public class PasswordEncryption {

    // Generate a random salt value:
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    // Convert a hexadecimal string to a byte array:
    public static byte[] hexToBytes(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
        }
        return bytes;
    }

    // Convert a byte array to a hexadecimal string:
    public static String byteToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // Encrypt a password with SHA-256 and salt:
    public static String encryptPassword(String password, byte[] salt) {
        try {
            // Create a message digest instance for SHA-256:
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Update the message digest with the salt:
            md.update(salt);

            // Hash the password:
            byte[] hashedPassword = md.digest(password.getBytes());

            // Convert the hashed password and salt to hexadecimal strings:
            String hashedPasswordHex = byteToHex(hashedPassword);
            String saltHex = byteToHex(salt);

            // Return the concatenated string of hashed password and salt:
            return hashedPasswordHex + saltHex;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean comparePasswords(String plainTextPassword, String storedEncryptedPassword) {
        // Extract the salt from the stored encrypted password
        String saltHex = storedEncryptedPassword.substring(storedEncryptedPassword.length() - 32);
        byte[] salt = hexToBytes(saltHex);

        // Encrypt the plain text password with the extracted salt
        String encryptedPassword = encryptPassword(plainTextPassword, salt);

        // Compare the encrypted password with the stored encrypted password
        return encryptedPassword.equals(storedEncryptedPassword);
    }

    public static void main(String[] args) {
        String password = "123456";
        byte[] salt = PasswordEncryption.generateSalt();
        String encryptPassword = encryptPassword(password, salt);
        System.out.println("encrypt password: " + encryptPassword);
        System.out.println("Compare password: " + comparePasswords(password, encryptPassword));
    }

}
