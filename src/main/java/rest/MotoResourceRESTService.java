package rest;

import model.MotoOffer;
import repository.MotoRepository;
import service.MotoService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class produces a RESTful service to read/write
 * the contents of the moto table.
 */
@Path("/moto")
@RequestScoped
public class MotoResourceRESTService {


    @Inject
    private Validator validator;

    @Inject
    private MotoRepository repository;

    @Inject
    MotoService motoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MotoOffer> carList() {
        return repository.findAll();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public MotoOffer lookupCarById(@PathParam("id") long id) {
        MotoOffer motoOffer = repository.findById(id);
        if (motoOffer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return motoOffer;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMoto(MotoOffer motoOffer) {

        Response.ResponseBuilder builder = null;
        try {
            motoService.create(motoOffer);
            // Create an "ok" response
            builder = Response.ok();
        } catch (ConstraintViolationException ce) {
            // Handle bean validation issues
            builder = createViolationResponse(ce.getConstraintViolations());
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

        return builder.build();
    }


    /**
     * Creates a JAX-RS "Bad Request" response including a map of all violation fields, and their message. This can then be used
     * by clients to show violations.
     *
     * @param violations A set of violations that needs to be reported
     * @return JAX-RS response containing all violations
     */
    private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
        Map<String, String> responseObj = new HashMap<>();
        for (ConstraintViolation<?> violation : violations) {
            responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }


}