package websocket;

import model.Car;

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
@ServerEndpoint("/actions")
public class CarWebSocketServer {

    @Inject
    private CarSessionHandler sessionHandler;

    @OnOpen
    public void open(Session session) {
        sessionHandler.addSession(session);
    }

    @OnClose
    public void close(Session session) {
        sessionHandler.removeSession(session);

    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(CarWebSocketServer.class.getName()).log(Level.SEVERE, null, error);

    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();

            if ("add".equals(jsonMessage.getString("action"))) {
                Car car = new Car();
                car.setDate_accepted(new Date());
                car.setColor(jsonMessage.getString("color"));

                sessionHandler.addCar(car);
            }

            if ("remove".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                sessionHandler.removeCar(id);
            }

        }


    }

}
