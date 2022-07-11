const express = require("express");
const { createServer } = require("http");
const { Server } = require("socket.io");
const port = process.env.PORT || 4001;
const index = require("./routes/index");

const app = express();
app.use(index);
const httpServer = createServer(app);
const io = new Server(httpServer, {
  cors: {
    origin: "*"
  }
});

let interval;

io.on("connection", (socket) => {
  console.log("New client connected");
  if (interval) {
    clearInterval(interval);
  }
  interval = setInterval(() => getApiAndEmit(socket), 1000);
  socket.on("disconnect", () => {
    console.log("Client disconnected");
    clearInterval(interval);
  });
});

function getRandomInt(max) {
  return Math.floor(Math.random() * max);
}

const getApiAndEmit = socket => {
  let code = 100;
  let value = getRandomInt(98) + 1;
  socket.emit("FromAPI", code, value);
};

httpServer.listen(port, () => console.log(`Listening on port ${port}`));
