import React, { useState, useEffect } from "react";
import socketIOClient from "socket.io-client";
const ENDPOINT = "http://127.0.0.1:4001";

function App() {
  const [response, setResponse] = useState({code: 0, value: null});

  useEffect(() => {
    const socket = socketIOClient(ENDPOINT);
    socket.on("FromAPI", (code, value) => {
      setResponse({code: code , value: value});
    });
  }, []);

  return (
    <div>
      <h4>Result</h4>
      <p>Code :{response.code} Value: {response.value}.</p>
    </div>
  );
}

export default App;
