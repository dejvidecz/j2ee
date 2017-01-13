package model;

import lombok.Data;
import org.picketlink.Identity;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.internal.DefaultIdentity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Dejv on 12.01.17.
 */
@Data
@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String role;

    @OneToMany(fetch=FetchType.LAZY)
    private List<VehicleOffer> vehicleOfferList;



}
