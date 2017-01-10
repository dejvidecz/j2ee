package model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * Created by Dejv on 10.01.17.
 */
@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @NotEmpty
    private String color;

    /**
     * Date of accepted to bazar
     */
    @NotNull
    private Date date_accepted;

    @Length(max = 10)
    private String spz;



}
