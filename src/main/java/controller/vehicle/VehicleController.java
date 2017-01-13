package controller.vehicle;


import model.VehicleOffer;
import model.VehicleOffer;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import java.io.Serializable;

/**
 *
 * Created by Dejv on 10.01.17.
 */
@Model
@SessionScoped
public class VehicleController implements Serializable {


    public String detail(VehicleOffer vehicleOffer){
        return "detail/moto";
    }

}
