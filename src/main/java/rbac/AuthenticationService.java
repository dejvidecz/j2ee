package rbac;

/**
 * Created by Dejv on 12.01.17.
 */

import model.User;
import org.picketlink.Identity;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.basic.Grant;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.query.RelationshipQuery;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * Simple JAX-RS Authentication Service that supports username/password credential.
 * </p>
 */
@Singleton
public class AuthenticationService {

    @Inject
    private Identity identity;

    @Inject
    private IdentityManager identityManager;

    @Inject
    private RelationshipManager relationshipManager;

    @Inject
    private DefaultLoginCredentials credentials;


    public boolean authenticate(User user) {
        if (!this.identity.isLoggedIn()) {
            this.credentials.setUserId(user.getUsername());
            this.credentials.setPassword(user.getPassword());
            this.identity.login();
        }


        if (this.identity.isLoggedIn()) {
            Account account = this.identity.getAccount();
            List<Role> roles = getUserRoles(account);

            AuthenticationResponse authenticationResponse = new AuthenticationResponse(account, roles);

            return true;
            //return Response.ok().entity(authenticationResponse).type(MediaType.APPLICATION_JSON_TYPE).build();
        }
        return false;
        //return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credential").type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    public List<Role> getUserRoles(Account account) {
        RelationshipQuery<Grant> query = this.relationshipManager.createRelationshipQuery(Grant.class);

        query.setParameter(Grant.ASSIGNEE, account);

        List<Role> roles = new ArrayList<Role>();

        for (Grant grant : query.getResultList()) {
            roles.add(grant.getRole());
        }

        return roles;
    }

    private class AuthenticationResponse implements Serializable {

        private static final long serialVersionUID = 1297387771821869087L;

        private Account account;
        private List<Role> roles;

        public AuthenticationResponse(Account account, List<Role> roles) {
            this.account = account;
            this.roles = roles;
        }

        public Account getAccount() {
            return this.account;
        }

        public List<Role> getRoles() {
            return this.roles;
        }
    }

}
