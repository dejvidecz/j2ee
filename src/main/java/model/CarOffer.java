package model;

import lombok.Data;
import visitor.VehicleViewVisitorInterface;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlType;








/**
 *
 * Created by Dejv on 10.01.17.
 */
@Entity
@XmlType
@Data
public class CarOffer extends VehicleOffer {


    @Enumerated(EnumType.STRING)
    private CarType carType;

    private int doors;


    public CarType[] getCarTypes(){
        return CarType.values();
    }

    @Override
    public String accept(VehicleViewVisitorInterface vehicleViewVisitorInterface) {
        return "_car.xhtml";
    }




}
