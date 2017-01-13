package websocket;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dejv on 12.01.17.
 */
@ApplicationScoped
public class ChatSessionHandler {

    private final Set<Session> sessions = new HashSet<>();

    private final Map<Session,String> usernameMap = new HashMap<>();

    private final List<ChatMessage> chatMessages = new ArrayList<>();


    public void addSession(Session session) {

        sessions.add(session);
        //new session connected
        for(ChatMessage chatMessage : chatMessages){
            sendToSession(session,createMessageAdded(chatMessage));
        }
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }


    /**
     *  Send an event message to all connected clients.
     * @param message
     */
    private void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    /**
     * Send an event message to a client.
     * @param session
     * @param message
     */
    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(ChatSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUsername(String username, Session session) {
        usernameMap.put(session,username);
    }

    public void messageAccepted(ChatMessage chatMessage, Session session) {
        chatMessage.setUsername(usernameMap.get(session));
        chatMessages.add(chatMessage);
        checkFullSet();
        JsonObject addMessage = createMessageAdded(chatMessage);
        sendToAllConnectedSessions(addMessage);

    }

    private void checkFullSet(){
        if(chatMessages.size()>10){
            chatMessages.remove(0);
        }
    }

    private JsonObject createMessageAdded(ChatMessage chatMessage) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("message", chatMessage.getMessage())
                .add("username", chatMessage.getUsername())
                .add("created_at",chatMessage.getCreated_at().toString())
                .build();
        return addMessage;
    }
}
