# Question 2 - WebSocket Server and Client

##  Problem Statement
> Develop a WebSocket server and client to facilitate real-time bidirectional communication.

---

##  Assumptions
- Used **Node.js** with the **`ws`** library for WebSocket communication.
- Implemented a **terminal-based** client for simplicity.
- Messages follow a **structured JSON format**:
  ```json
  { "type": "chat", "payload": "Hello" }
