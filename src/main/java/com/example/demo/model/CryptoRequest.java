package com.example.demo.model;

public class CryptoRequest {
    private String message;
    private String key; //secret key for crypt/decrypt

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
}
