// client.js
const WebSocket = require('ws');
const readline = require('readline');

const ws = new WebSocket('ws://localhost:8080');

ws.on('open', () => {
  console.log(' Connected to server');

  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
  });

  rl.setPrompt('You: ');
  rl.prompt();

  rl.on('line', (input) => {
    // THIS wraps your raw input into JSON string format
    const message = {
      type: 'chat',
      payload: input
    };
    ws.send(JSON.stringify(message));
    rl.prompt();
  });
});

ws.on('message', (data) => {
  try {
    const message = JSON.parse(data.toString());

    if (message.type === 'chat') {
      console.log(`\n📨 Message from other client: ${message.payload}`);
    } else if (message.type === 'welcome') {
      console.log(` ${message.payload}`);
    } else if (message.type === 'error') {
      console.error(` Error: ${message.payload}`);
    }
  } catch (err) {
    console.error(' Failed to parse message:', err);
  }
});
