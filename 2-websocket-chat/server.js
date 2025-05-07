// server.js
const WebSocket = require('ws');
const PORT = 8080;

const wss = new WebSocket.Server({ port: PORT });

const clients = new Set();

wss.on('connection', (ws) => {
  clients.add(ws);
  console.log(`[${new Date().toISOString()}] New client connected`);

  ws.send(JSON.stringify({ type: "welcome", payload: "Welcome to the WebSocket server!" }));

  ws.on('message', (message) => {
    try {
      const decoded = JSON.parse(message.toString());
      console.log(`[${new Date().toISOString()}] Received:`, decoded);

      // Broadcast to all other clients
      for (let client of clients) {
        if (client !== ws && client.readyState === WebSocket.OPEN) {
          client.send(JSON.stringify({
            type: "chat",
            payload: decoded.payload,
            timestamp: new Date().toISOString()
          }));
        }
      }
    } catch (err) {
      console.error("Error parsing message:", err);
      ws.send(JSON.stringify({ type: "error", payload: "Invalid message format" }));
    }
  });

  ws.on('close', () => {
    clients.delete(ws);
    console.log(`[${new Date().toISOString()}] Client disconnected`);
  });

  ws.on('error', (err) => {
    console.error(`[${new Date().toISOString()}] WebSocket error:`, err);
  });
});

console.log(` WebSocket server is running on ws://localhost:${PORT}`);
