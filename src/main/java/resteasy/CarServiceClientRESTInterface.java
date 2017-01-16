package resteasy;

import model.CarOffer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Dejv on 13.01.17.
 */

public interface CarServiceClientRESTInterface {

        @GET
        @Path("/car/get/{id}")
        @Produces({ MediaType.APPLICATION_JSON})
        CarOffer carById(@QueryParam("id") String id);

        @GET
        @Path("/cars/")
        List<CarOffer> getCars();

}
