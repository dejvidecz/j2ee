package websocket;

import model.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dejv on 12.01.17.
 */
@ApplicationScoped
public class CarSessionHandler {

    private int carId = 0;

    private final Set<Session> sessions = new HashSet<>();
    private final Set<Car> cars = new HashSet<>();

    public void addSession(Session session) {
        sessions.add(session);
        for (Car device : cars) {
            JsonObject addMessage = createAddMessage(device);
            sendToSession(session, addMessage);
        }
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }

    public void addCar(Car car){
        this.cars.add(car);
        JsonObject addMessage = createAddMessage(car);
        sendToAllConnectedSessions(addMessage);
    }




    /**
     * Remove a car from the application.
     * @param id
     */
    public void removeCar(int id) {
        Car device = getCarById(id);
        if (device != null) {
            cars.remove(device);
            JsonProvider provider = JsonProvider.provider();
            JsonObject removeMessage = provider.createObjectBuilder()
                    .add("action", "remove")
                    .add("id", id)
                    .build();
            sendToAllConnectedSessions(removeMessage);
        }
    }

    /**
     * Retrieve a car with a specific identifier.
     * @param id
     * @return
     */
    private Car getCarById(int id) {
        for (Car device : cars) {
            if (device.getId() == id) {
                return device;
            }
        }
        return null;
    }

    /**
     *  Build a JSON message for adding a car to the application.
     * @param device
     * @return
     */
    private JsonObject createAddMessage(Car device) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("color", device.getColor())
                .build();
        return addMessage;
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
            Logger.getLogger(CarSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
