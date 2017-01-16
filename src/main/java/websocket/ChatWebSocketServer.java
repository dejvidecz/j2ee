package websocket;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.StringReader;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dejv on 12.01.17.
 */
@ApplicationScoped
@ServerEndpoint("/chat")
public class ChatWebSocketServer {

    @Inject
    private ChatSessionHandler sessionHandler;

    @OnOpen
    public void open(Session session) {
        sessionHandler.addSession(session);
    }

    @OnClose
    public void close(Session session) { sessionHandler.removeSession(session); }

    @OnMessage
    public void handleMessage(String message, Session session) {
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();

            System.out.println(message);
            System.out.println(jsonMessage.toString());

            if ("username".equals(jsonMessage.getString("action"))) {
                sessionHandler.setUsername(jsonMessage.getString("username"), session);
            }

            if ("add".equals(jsonMessage.getString("action"))) {
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setMessage(jsonMessage.getString("body"));
                chatMessage.setCreated_at(new Date());
                sessionHandler.messageAccepted(chatMessage, session);

            }

        }


    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(ChatWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
    }

}
