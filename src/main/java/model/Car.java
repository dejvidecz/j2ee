package model;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Dejv on 10.01.17.
 */
@Entity
@XmlType
@Data
public class Car extends Vehicle {

    public enum CarType {MINI_CAR, SMALL_CAR, MEDIUM_CAR, LARGE_CAR}

    @Enumerated(EnumType.STRING)
    private CarType carType;



}
