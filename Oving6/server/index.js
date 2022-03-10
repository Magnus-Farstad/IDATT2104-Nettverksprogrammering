const WebSocket = require("ws")

const wss = new WebSocket.Server({ port: 8082 });

wss.on("connection", ws => {
    console.log("New client connected!");

    ws.on("message", data => {
        console.log("Message from client: " + data.toString());

        wss.clients.forEach(client => client.send(data.toString()));
    })

    ws.on("close", () => {
        console.log("Client disconnected");
        ws.send("Til alle klienter");
    });
});