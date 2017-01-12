package rbac;

/**
 *
 * Created by Dejv on 12.01.17.
 */
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.deltaspike.security.api.authorization.AccessDeniedException;

/**
 * Pokud je pristup odmitnut nezobrazi error message s vypisem stack
 * @author Dejv
 */
@Provider
public class MyExceptionMapper implements ExceptionMapper<AccessDeniedException> {

    @Override
    public Response toResponse(AccessDeniedException exception) {
        return Response.status(Response.Status.FORBIDDEN).build();
    }

}
