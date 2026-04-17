package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {
	

    public static String hashToHex(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // using Simple Caesar Ciphe
    // shift characters by 3 positions
    public static String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            result.append((char) (character + 3));
        }
        return result.toString();
    }

    public static String decrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            result.append((char) (character - 3));
        }
        return result.toString();
    }

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SpringApplication.run(DemoApplication.class, args);
		Scanner sc = new Scanner(System.in);
        String lastEncryptedText = "";

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 : Saisir un texte puis le chiffrer");
            System.out.println("2 : Déchiffrer le texte");
            System.out.println("3 : Calculer le SHA-256 d'un texte");
            System.out.println("4 : Comparer deux hashes");
            System.out.println("0 : Quitter");
            System.out.print("Choix : ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1 -> {
                    System.out.print("Texte à chiffrer : ");
                    String plain = sc.nextLine();
                    lastEncryptedText = encrypt(plain);
                    System.out.println("Texte chiffré : " + lastEncryptedText);
                }
                case 2 -> {
                    if (lastEncryptedText.isEmpty()) {
                        System.out.println("Erreur: Rien à déchiffrer.");
                    } else {
                        System.out.println("Texte déchiffré : " + decrypt(lastEncryptedText));
                    }
                }
                case 3 -> {
                    System.out.print("Texte pour SHA-256 : ");
                    String input = sc.nextLine();
                    System.out.println("Hash SHA-256 : " + hashToHex(input));
                }
                case 4 -> {
                    System.out.print("Entrez le 1er hash : ");
                    String h1 = sc.nextLine();
                    System.out.print("Entrez le 2ème hash : ");
                    String h2 = sc.nextLine();
                    if (h1.equalsIgnoreCase(h2)) {
                        System.out.println("Hashes identiques.");
                    } else {
                        System.out.println("Hashes différents ");
                    }
                }
                case 0 -> System.exit(0);
                default -> System.out.println("Choix invalide.");
            }
        }
			
		
	}

}
