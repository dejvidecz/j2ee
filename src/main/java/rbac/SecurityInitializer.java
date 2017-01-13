package rbac;

/**
 * Created by Dejv on 12.01.17.
 */
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import static org.picketlink.idm.model.basic.BasicModel.grantRole;
import static org.picketlink.idm.model.basic.BasicModel.hasRole;
import static rbac.ApplicationRole.*;

@Singleton
@Startup
public class SecurityInitializer {

    @Inject
    private PartitionManager partitionManager;

    @PostConstruct
    public void createUsers() {
        createUser("admin", ADMINISTRATOR);
        createUser("agent", AGENT);
        createUser("seller", SELLER);
        createUser("buyer", BUYER);

    }



    public void createUser(String loginName, String roleName) {
        User user = new User(loginName);

        IdentityManager identityManager = this.partitionManager.createIdentityManager();

        identityManager.add(user);

        Password password = new Password(loginName + "123");

        identityManager.updateCredential(user, password);

        Role role = new Role(roleName);

        identityManager.add(role);

        RelationshipManager relationshipManager = this.partitionManager.createRelationshipManager();

        grantRole(relationshipManager, user, role);
    }
}