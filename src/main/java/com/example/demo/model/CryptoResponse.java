package com.example.demo.model;

public class CryptoResponse {
    private String result;
    private String operation;
    private String status;

    public CryptoResponse(String result, String operation) {
        this.result = result;
        this.operation = operation;
        this.status = "success";
    }

    public CryptoResponse(String status, String result, String operation) {
        this.status = status;
        this.result = result;
        this.operation = operation;
    }

    public String getResult() { return result; }
    public String getOperation() { return operation; }
    public String getStatus() { return status; }
}
