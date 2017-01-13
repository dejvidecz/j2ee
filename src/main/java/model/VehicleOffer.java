package model;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import visitor.VehicleViewVisitorInterface;


import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;


/**
 *
 * Created by Dejv on 10.01.17.
 */
@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract public class VehicleOffer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int year;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Brand brand;

    @NotNull
    @NotEmpty
    private String color;

    @NotNull
    private double sellPrice;

    @ManyToOne(fetch=FetchType.LAZY)
    private User owner;

    /**
     * Date of accepted to bazar
     */
    @NotNull
    private Date date_added;


    @Length(max = 50)
    @NotNull
    private String title;

    @Lob
    @NotNull
    private String description;

    @Length(min = 5, max = 5)
    @NotNull
    private String zip;

    @Email
    private String email;

    @NotNull
    @Size(min = 10, max = 12)
    @Digits(fraction = 0, integer = 12)
    private String phoneNumber;

    abstract public String accept(VehicleViewVisitorInterface vehicleViewVisitorInterface);

    public Brand[] getGetBrandValues() {
        return Brand.values();
    }

}
