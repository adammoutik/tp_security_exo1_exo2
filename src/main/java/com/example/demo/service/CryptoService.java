package com.example.demo.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

@Service
public class CryptoService {

    private static final String DEFAULT_KEY = "MyDefaultSecret!"; // 16-char = 128-bit AES

   
    public String encrypt(String message, String key) throws Exception {
        SecretKey secretKey = buildKey(key);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    
    public String decrypt(String cipherText, String key) throws Exception {
        SecretKey secretKey = buildKey(key);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decoded = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    
    public String hash(String message) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(message.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    // builds a 16-byte AES key from any string 
    private SecretKey buildKey(String key) {
        String rawKey = (key != null && !key.isBlank()) ? key : DEFAULT_KEY;
        byte[] keyBytes = Arrays.copyOf(rawKey.getBytes(StandardCharsets.UTF_8), 16);
        return new SecretKeySpec(keyBytes, "AES");
    }
}
