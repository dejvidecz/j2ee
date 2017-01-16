package rest;

import model.VehicleOffer;
import repository.VehicleRepository;
import service.CarService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * This class produces a RESTful service to read/write
 * the contents of the vehicle table.
 */
@Path("/vehicle")
@RequestScoped
public class VehicleResourceRESTService {


    @Inject
    private Validator validator;

    @Inject
    private VehicleRepository repository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VehicleOffer> vehicleList() {
        return repository.findAll();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public VehicleOffer lookuVehicleById(@PathParam("id") long id) {
        VehicleOffer vehicleOffer = repository.findById(id);
        if (vehicleOffer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return vehicleOffer;
    }


}