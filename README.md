

## Endpoints

### POST /crypt — Encrypt a message (AES-128)
```bash
curl -X POST http://localhost:8080/crypt \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello World", "key": "MySecret123!4567"}'
```
Response:
```json
{ "result": "abc123...base64...", "operation": "encrypt", "status": "success" }
```

---

### GET /decrypt — Decrypt a message
```bash
curl "http://localhost:8080/decrypt?message=abc123...base64...&key=MySecret123!4567"
```
Response:
```json
{ "result": "Hello World", "operation": "decrypt", "status": "success" }
```

---

### POST /hash — Hash a message (SHA-256)
```bash
curl -X POST http://localhost:8080/hash \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello World"}'
```
Response:
```json
{ "result": "a591a6d40bf420404a011733cfb7b190...", "operation": "hash", "status": "success" }
```

---

## Run the App

- Run the **DemoApplication.java** as Java Applicatiob for exerice 1 and run the project as a spring boot app to run the API.

The server starts on **http://localhost:8080**

