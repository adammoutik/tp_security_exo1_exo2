package com.example.demo.controller;

import com.example.demo.model.CryptoRequest;
import com.example.demo.model.CryptoResponse;
import com.example.demo.service.CryptoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CryptoController {

    private final CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    
    @PostMapping("/crypt")
    public ResponseEntity<CryptoResponse> encrypt(@RequestBody CryptoRequest request) {
        try {
            String encrypted = cryptoService.encrypt(request.getMessage(), request.getKey());
            return ResponseEntity.ok(new CryptoResponse(encrypted, "encrypt"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new CryptoResponse("error", e.getMessage(), "encrypt"));
        }
    }

   
    @GetMapping("/decrypt")
    public ResponseEntity<CryptoResponse> decrypt(
            @RequestParam String message,
            @RequestParam(required = false) String key) {
        try {
            String decrypted = cryptoService.decrypt(message, key);
            return ResponseEntity.ok(new CryptoResponse(decrypted, "decrypt"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new CryptoResponse("error", e.getMessage(), "decrypt"));
        }
    }

    /**
     * POST /hash
     * Body: { "message": "hello" }
     * Returns: { "result": "<sha256 hex>", "operation": "hash", "status": "success" }
     */
    @PostMapping("/hash")
    public ResponseEntity<CryptoResponse> hash(@RequestBody CryptoRequest request) {
        try {
            String hashed = cryptoService.hash(request.getMessage());
            return ResponseEntity.ok(new CryptoResponse(hashed, "hash"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new CryptoResponse("error", e.getMessage(), "hash"));
        }
    }
}
