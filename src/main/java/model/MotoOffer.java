package model;

import visitor.VehicleViewVisitorInterface;

import javax.persistence.Entity;

/**
 *
 * Created by Dejv on 12.01.17.
 */
@Entity
public class MotoOffer extends VehicleOffer {

    @Override
    public String accept(VehicleViewVisitorInterface vehicleViewVisitorInterface) {
        return "_moto.xhtml";
    }



}
