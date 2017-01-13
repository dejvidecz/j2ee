package service;

import model.User;
import org.picketlink.Identity;
import org.picketlink.authentication.AuthenticationException;
import rbac.AuthenticationService;
import rbac.SecurityInitializer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by Dejv on 10.01.17.
 */
@Stateless //always transactions
public class UserService implements Serializable {

    @Inject
    SecurityInitializer securityInitializer;

    @Inject
    AuthenticationService authenticationService;


    public boolean login(User user) {
        if(!authenticationService.authenticate(user)){
            throw new AuthenticationException("Cannot login");
        }
        return true;

    }

    @Inject
    private Identity identity;

    public void logout() {
        if (this.identity.isLoggedIn()) {
            this.identity.logout();
        }
    }

    public void register(User user) {
        securityInitializer.createUser(user.getUsername(),user.getRole());
    }
}
