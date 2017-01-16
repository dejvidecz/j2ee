package jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Toto je jms server prijimajici zpravy a odesila emaily
 * \
 * Created by Dejv on 12.01.17.
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "Queue01"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
}, mappedName = "Queue01")
public class EmailJmsReceiver implements MessageListener {

    @Inject
    JMSContext jmsContext;

    @Override
    public void onMessage(Message message) {
        try {
            Email email = message.getBody(Email.class);
            Logger.getLogger("EMAIL JMS").log(Level.INFO,email.toString());

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
