package resteasy;


import model.CarOffer;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.UriBuilder;
import java.util.List;

/**
 * Created by Dejv on 13.01.17.
 */
public class Client {

    private static String BASE_URL = "http://adminer.local";

    public List<CarOffer> get() {

        try{

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(UriBuilder.fromPath(BASE_URL));
        CarServiceClientRESTInterface.ServicesInterface proxy = target.proxy(CarServiceClientRESTInterface.ServicesInterface.class);

        List<CarOffer> list = proxy.getCars();
            return list;

        }catch (Exception e){
            return null;
        }



    }


}
