package com.mr486.tdc.serveur.model;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.stereotype.Component;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.Map;


@Log4j2
@Component
@NoArgsConstructor
public class ChatServer extends WebSocketServer
{
    public ChatServer(int port) {
        super(new InetSocketAddress(port));
    }

    public ChatServer(InetSocketAddress address) {
        super(address);
    }

    public ChatServer(int port, Draft_6455 draft) {
        super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        log.info("connexion");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        log.info("déconnexion");
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        log.info("Message reçu");
    }

    private String sendMessage(Integer code, Integer msg){
        Ping pingToSend = new Ping(code, msg);
        return pingToSend.toJson();
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            log.fatal("Le serveur websocket à planté !!!");
        }
    }

    @Override
    public void onStart() {
        log.info("Serveur websocket lancé!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }

    public void sendToAll(Integer code, Integer content){
        broadcast(sendMessage(code,content));
    }
}
