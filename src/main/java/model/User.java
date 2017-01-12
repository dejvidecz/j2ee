package model;

import lombok.Data;
import org.picketlink.idm.model.basic.Role;

/**
 * Created by Dejv on 12.01.17.
 */
@Data
public class User {

    private String username;

    private String password;

    private String role;


}
