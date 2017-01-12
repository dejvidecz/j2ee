package jms;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Added to wildfly new user test//test
 * added queue and defaultjmsconnectionfactory
 * Created by Dejv on 12.01.17.
 */

public class EmailJsmSender {


    private ConnectionFactory cf;
    private Destination d;


    public EmailJsmSender() throws NamingException, JMSException {
        this.cf = InitialContext.doLookup("java:/comp/DefaultJmsConnectionFactory");
        this.d = InitialContext.doLookup("java:/Queue01");
    }


    public void send(Email email) {

        try ( JMSContext jcontext = cf.createContext("test","test")) {
            JMSProducer mp = jcontext.createProducer();
            mp.send(d, email);
        } catch (JMSRuntimeException re) {
            re.printStackTrace();
        }
    }

}
